## Queries a api not a database
GraphQL itself is an API query language, not a database. It doesn’t store or manage data directly. Instead, it’s a layer between your frontend (client) and your backend (data sources like databases, REST APIs, or third-party services).

Here's how it works in the stack:

1. Frontend sends a request to the GraphQL API.
2. GraphQL Server receives the request, validates it against the schema, and then uses resolvers (custom functions) to retrieve or modify data.
3. Resolvers fetch data from one or multiple data sources, such as:
    - Databases (e.g., MongoDB, PostgreSQL, MySQL)
    - Other APIs (REST or SOAP APIs)
    - External services (like cloud services)

## No need to specify what kind of http request is sent
When using GraphQL, you typically don’t need to specify different HTTP methods (like GET, POST, etc.) for different operations. Instead, GraphQL uses a single endpoint (e.g., /graphql) and often relies on POST requests for both queries and mutations.

Here’s a typical request structure in GraphQL:

1. Single Endpoint
All requests—whether they’re queries, mutations, or subscriptions—are sent to a single endpoint, commonly /graphql.
2. Request Body Structure
For both queries and mutations, the client sends the request as a JSON payload in the HTTP body (when using POST).
The JSON payload contains the query (or mutation) and optionally variables for dynamic values.

### Example of a GraphQL Request
Here’s how a GraphQL request might look for a query to fetch recipes:
```
POST /graphql
Content-Type: application/json

{
  "query": "query { recipes { id title description } }"
}
```
And for a mutation to add a new recipe:
```
POST /graphql
Content-Type: application/json

{
  "query": "mutation ($input: RecipeInput!) { createRecipe(input: $input) { id title } }",
  "variables": {
    "input": {
      "title": "New Recipe",
      "description": "A delicious new recipe",
      "ingredients": ["Ingredient1", "Ingredient2"]
    }
  }
}
```
### Why No Need for Specific HTTP Methods?
GraphQL’s single endpoint and query language make it more flexible than REST. You don’t need separate HTTP methods (GET, POST, PUT, DELETE) to indicate different types of operations because:

- The operation type (query, mutation, or subscription) is specified within the GraphQL query syntax, not by the HTTP method.
- GraphQL clients like Apollo or Relay handle these requests as POST by default, which simplifies things.\

This setup also enables clients to be more precise, fetching only the data they need in a single request and keeping the API flexible.

## get = query
## put,delete,post - mutation
In GraphQL, the operation types map conceptually to HTTP methods in this way:

Query: Similar to GET in REST, used to fetch data. Queries do not modify data; they only retrieve it.
Mutation: Equivalent to POST, PUT, or DELETE in REST, used to modify data. Mutations can create, update, or delete records.
Here's a quick overview of each:

1. Query (Similar to GET)
Used to read or fetch data.
Queries are idempotent, meaning they don’t change the state of the data on the server.
Example Query:
```
query {
  recipes {
    id
    title
    description
  }
}
```
2. Mutation (Similar to POST, PUT, DELETE)
- Used to perform actions that modify data, like adding, updating, or deleting records.
- Mutations can have side effects, meaning they change the state of data on the server.\

Example Mutation for Adding Data (similar to POST):
```
mutation {
  createRecipe(input: { title: "Spaghetti", description: "A tasty pasta dish" }) {
    id
    title
  }
}
```
Example Mutation for Updating Data (similar to PUT):
```
mutation {
  updateRecipe(id: "1", input: { title: "Updated Recipe" }) {
    id
    title
  }
}
```
Example Mutation for Deleting Data (similar to DELETE):
```
mutation {
  deleteRecipe(id: "1") {
    id
  }
}
```
## 1 way - Frontend - graphql - backend(rest) - database
In this setup, GraphQL acts as a unifying API layer that aggregates or orchestrates data from multiple REST APIs and possibly other services.

Use Case: This setup is useful when:

- You’re working with existing REST APIs and don’t want to replace them.
- Your application requires data from multiple services (e.g., microservices) and you want to provide a single API endpoint to the frontend.
- You want to shield the frontend from any complex API calls by combining them into a single GraphQL query.\
Workflow:

- Frontend sends a query to the GraphQL server.
- The GraphQL server then communicates with various REST APIs (potentially using resolvers that call these APIs).
- The REST APIs interact with the database as needed.
- The GraphQL server aggregates the data from the REST responses and sends it back to the frontend.\
Example: Suppose a Recipe Finder app requires data from a recipe API and a nutrition API. GraphQL queries can gather both recipes and nutritional info from different REST endpoints and deliver them as a unified response.
## 2 way - frontend - graphql - database
In this setup, GraphQL acts as a direct API layer to the database, bypassing the REST API altogether.

Use Case: This setup is effective when:

- You’re building a new project without existing REST APIs.
- You want to avoid an additional layer of REST calls for performance or simplicity.
- The GraphQL server is configured with direct database access (using an ORM like Prisma for SQL or Mongoose for MongoDB).
- The project can benefit from real-time data handling (GraphQL Subscriptions).\
Workflow:
1. Frontend sends a GraphQL query to the server.
2. The GraphQL server executes resolvers that directly query or update the database.
3. The server formats and sends the response back to the frontend.
- Example: For a Recipe Finder app, GraphQL resolvers could directly interact with a MongoDB or PostgreSQL database to fetch or update recipe data.

Choosing the Right Approach:\
-Approach 1 (Frontend → GraphQL → REST) is ideal for integrating with existing REST services or where multiple services need to be combined into a single API.\
-Approach 2 (Frontend → GraphQL → Database) works best for new projects or when streamlining architecture and reducing layers for direct data access.\
Both setups are flexible and can even coexist within the same project, where some GraphQL resolvers fetch from REST APIs and others interact directly with a database. This flexibility allows GraphQL to serve as a powerful tool in modern application architectures.
## must be the specified type and not null(!)
Type Declaration with !: When you specify a type with an exclamation mark, such as String!, it means:\

- The field cannot be null. It must always have a value.
- If a null value is attempted, the server will throw an error instead of processing the request.\
1. Examples of Non-Null Types:
```
type Recipe {
  id: ID!              # id is required and must be non-null
  title: String!       # title is required and must be non-null
  description: String  # description is optional and can be null
}

type Query {
  recipe(id: ID!): Recipe  # id is required as an argument in this query
}
```

2. Common Use Cases:
- Enforcing Required Fields: For fields like id, title, or createdAt, where values should always be present.
- Input Validation: For input types in mutations (e.g., required fields in a createRecipe mutation).
- Ensuring Consistency: In APIs where certain values should never be missing (like primary identifiers).\
### Example in a Mutation
Here’s a mutation with non-nullable inputs:
```
input RecipeInput {
  title: String!        # title is required
  description: String   # description is optional
  ingredients: [String!]!  # ingredients list is required and cannot contain null items
}

type Mutation {
  createRecipe(input: RecipeInput!): Recipe!
}
```
In this example:
- The *title* field inside *RecipeInput* is **required**.
- *ingredients* must be a list that cannot contain any **null** elements (each *ingredient* is String!).
- The *input* itself is **required** in *createRecipe*, meaning you can’t call this mutation without providing a *RecipeInput*.
- The *createRecipe* mutation is expected to always return a *Recipe* object and never **null**.\
By enforcing non-nullable fields, GraphQL improves data reliability and validation at the schema level.
## Every graphQL needs a type Quesry whuich holds every query that you can make withall data that you can request. They not necesarely need a type. they can be a filter or code called input. 
## when you have a type which is not basic graphql type, you need to specify the fields that you want.
