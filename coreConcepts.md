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
## 1 way - Frontend - graphql - backend(rest) - database
## 2 way - frontend - graphql - database
## single endpoint
## must be the specified type and not null(!)
## Every graphQL needs a type Quesry whuich holds every query that you can make withall data that you can request. They not necesarely need a type. they can be a filter or code called input. 
## when you have a type which is not basic graphql type, you need to specify the fields that you want.
