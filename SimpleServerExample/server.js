const { ApolloServer, gql } = require('apollo-server');
const cassandraClient = require('./cassandra');

// This part is where we define what kind of data our API works with.
// We're creating a "User" type and a query that can fetch a user based on their ID.
const typeDefs = gql`
  // The "User" type has three fields: id, name, and email.
  // We're using "Int" for the id because we want simple numbers for this example.
  type User {
    id: Int!        // The "!" means the id is required, so no user can exist without an id.
    name: String!   // Same for the name, it has to be there.
    email: String!  // And email too, must always be provided.
  }

  // This defines the query to get a user by their id. We'll ask for an id (an integer) and get a User back.
  type Query {
    getUser(id: Int!): User  // When we run "getUser" with an id, it returns the User object.
  }
`;

const resolvers = {
  Query: {
    getUser: async (_, { id }, __, info) => {
      // Get the fields the client is requesting
      const requestedFields = info.fieldNodes[0].selectionSet.selections.map(selection => selection.name.value);
      
      // Build the query dynamically based on what fields are requested
      const fieldsToFetch = requestedFields.join(', ');
      const query = `SELECT ${fieldsToFetch} FROM users WHERE id = ?`;

      // Execute the query
      const result = await cassandraClient.execute(query, [id], { prepare: true });

      // If we found a user, return the requested fields
      if (result.rowLength > 0) {
        return result.first();  // Return only the fields that the client requested
      }
      
      return null;  // Return null if no user is found
    }
  }
};


Mutation: {
  // This function runs when the client calls the createUser mutation.
  // It takes in an id, name, and email, then tries to insert those into the users table in Cassandra.
  createUser: async (_, { id, name, email }) => {
    try {
      // We're writing the CQL query to insert a new user into the database.
      // The placeholders (?, ?, ?) will be replaced with the actual values of id, name, and email.
      const query = 'INSERT INTO users (id, name, email) VALUES (?, ?, ?)';

      // This is where the query gets executed. The values we pass in (id, name, email) fill in the placeholders.
      // "prepare: true" helps optimize the query by pre-compiling it.
      await cassandraClient.execute(query, [id, name, email], { prepare: true });

      // If everything works out, we return the same data back to the client so they know the user was created.
      return { id, name, email };

    } catch (error) {
      // If something goes wrong, we catch the error and send back a message explaining what failed.
      // This is useful for debugging when things don't go as planned.
      throw new Error("Failed to create user: " + error.message);
    }
  }
}

const server = new ApolloServer({
  // These are the type definitions (schema) that describe what our GraphQL API can do.
  // It defines what queries and mutations are available and what data types they return.
  typeDefs,
  
  // These are the functions (resolvers) that get executed when a query or mutation is made.
  // They handle the logic of fetching or modifying data in the database.
  resolvers
});

// Start the server and listen on a port (by default, it's port 4000).
// Once it's running, it logs the URL where the API is available.
server.listen().then(({ url }) => {
  console.log(`🚀 Server ready at ${url}`); // This message just lets us know the server is up and running, ready for requests.
});

