const { ApolloServer, gql } = require('apollo-server');
const cassandraClient = require('./cassandra');


const typeDefs = gql`
  type User {
    id: ID!
    name: String!
    email: String!
  }

  type Query {
    getUser(id: ID!): User
  }

  type Mutation {
    createUser(id: ID!, name: String!, email: String!): User
  }
`;

const resolvers = {
  Query: {
    getUser: async (_, { id }) => {
      const query = 'SELECT id, name, email FROM users WHERE id = ?';
      const result = await cassandraClient.execute(query, [id], { prepare: true });
      if (result.rowLength > 0) {
        const row = result.first();
        return { id: row.id, name: row.name, email: row.email };
      }
      return null;
    }
  },
  Mutation: {
    createUser: async (_, { id, name, email }) => {
      const query = 'INSERT INTO users (id, name, email) VALUES (?, ?, ?)';
      await cassandraClient.execute(query, [id, name, email], { prepare: true });
      return { id, name, email };
    }
  }
  
};

const server = new ApolloServer({
  typeDefs,
  resolvers
});

server.listen().then(({ url }) => {
  console.log(`🚀 Server ready at ${url}`);
});
