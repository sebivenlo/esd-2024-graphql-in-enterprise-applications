1. `Receive requests` from a client (web app or mobile app).
2. `Process those requests`. For example, if a client asks for user data, the server retrieves the requested information from the database.
3.` Send back the data`. The server responds to the client with the specific data it asked for.

Waiter in a restaurant. The client(you, the customer) orders food(requests data). The waiter (server) gets the food from the kitchen (database or system) and brings it back to you (client).
GraphQL server, you(the client) specifically request exactly what data you want like "I only waht the user's name and email", and server will get it for you. 

How Does GraphQL server work?
1. `Schema:` the server has a schema, which is like a blue print or a menu. It defines what data is available and how it can be requested.
For example, the shcema might say that you can request a `user` and get fields like `name`, `email` and `age`.
2. `Resolvers`: These are functions that do the work of getting the data. When a client asks for the `name` and `email`, the resolver fetches that information from the database or another system.
3. `Handling Requests`:
   - The client sends a GraphQL query to the server.
   - The server reads the query, understands which data is being asked for, and users resolvers to get that data.
   - Finally, the server sends the requested data back to the client


Library analogy
The library (server) still holds all the books (data).
The app (client) goes to the GraphQL librarian and makes a request: "I only need these specific parts of the data."
The GraphQL librarian listens carefully and brings back exactly what you asked for, without giving you unnecessary extra books or chapters.

Source: https://medium.com/novvum/graphql-the-bakery-an-eli5-explain-like-im-5-for-graphql-de109e8a1f78

Three main components:

Schema: Defines the structure of the data (what types of data the client can query).
Resolvers: Functions that handle the queries and mutations (how the data is fetched or changed).
Server setup: The actual server code thaht connects everything and responds to client queries.

### Schema:
The schema defines what data is available to the client and what queries or mutations can be made. Blueprint for the API

```
const typeDefs = gql`
  type User {
    id: ID!
    name: String!
    email: String!
  }

  type Query {
    user(id: ID!): User
  }

  type Mutation {
    createUser(name: String!, email: String!): User
  }
`;
```
In this schema, there is a `User` type with fields like `id`, `name`, and `email`.
There is a `Query` type that allows to fetch user by their `id`
THere is also mutation type for creating a new user.

### Resolvers:

Resolvers are the function that are responsible for getting (or modifying) the data when a client sends a query or mutation.

```
const resolvers = {
  Query: {
    user: (parent, args, context, info) => {
      // Mock data for the user
      const users = [
        { id: "1", name: "John Doe", email: "john@example.com" },
        { id: "2", name: "Jane Smith", email: "jane@example.com" },
      ];
      // Return the user with the matching id
      return users.find(user => user.id === args.id);
    }
  },

  Mutation: {
    createUser: (parent, args, context, info) => {
      const newUser = {
        id: String(Date.now()),  // Generate a new ID
        name: args.name,
        email: args.email
      };
      // In a real-world app, we would save the new user to a database.
      // Here we're just returning the new user.
      return newUser;
    }
  }
};
```
Query Resolver:
The user query resolver takes an id argument and returns the user with that id. In this case, it is using simple array of mock data.

Mutation Resolver 
createUser mutation resolvers takes name and email as arguments and returns a new user object. In real world, it would save this user to the database. 

### GraphQL Server Setup 
Need to set up the GraphQL server to listen for the client requets. This ties everything together (schemas + resolvers) and allows the server to process incoming GraphQL queries or mutations.

```
const { ApolloServer, gql } = require('apollo-server');

const typeDefs = gql`
  type User {
    id: ID!
    name: String!
    email: String!
  }

  type Query {
    user(id: ID!): User
  }

  type Mutation {
    createUser(name: String!, email: String!): User
  }
`;

const resolvers = {
  Query: {
    user: (parent, args) => {
      const users = [
        { id: "1", name: "John Doe", email: "john@example.com" },
        { id: "2", name: "Jane Smith", email: "jane@example.com" },
      ];
      return users.find(user => user.id === args.id);
    }
  },
  Mutation: {
    createUser: (parent, args) => {
      const newUser = {
        id: String(Date.now()),
        name: args.name,
        email: args.email
      };
      return newUser;
    }
  }
};

// Create the Apollo Server instance
const server = new ApolloServer({ typeDefs, resolvers });

// Start the server
server.listen().then(({ url }) => {
  console.log(`🚀 Server ready at ${url}`);
});
```

ApolloServer: The server is set up using AS. Passing in the `typeDefs` (schema) and `resolvers` (functions that handle queries and mutations)

When the server starts, it will be available at the URL `http://localhost:4000`

Source: https://www.apollographql.com/docs/apollo-server/getting-started
