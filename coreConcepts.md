##Queries a api not a database
GraphQL itself is an API query language, not a database. It doesn’t store or manage data directly. Instead, it’s a layer between your frontend (client) and your backend (data sources like databases, REST APIs, or third-party services).

Here's how it works in the stack:

Frontend sends a request to the GraphQL API.
GraphQL Server receives the request, validates it against the schema, and then uses resolvers (custom functions) to retrieve or modify data.
Resolvers fetch data from one or multiple data sources, such as:
Databases (e.g., MongoDB, PostgreSQL, MySQL)
Other APIs (REST or SOAP APIs)
External services (like cloud services)
##No need to specify what kind of http request is sent
##get = query
##put,delete,post - mutation
##1 way - Frontend - graphql - backend(rest) - database
##2 way - frontend - graphql - database
##single endpoint
##must be the specified type and not null(!)
##Every graphQL needs a type Quesry whuich holds every query that you can make withall data that you can request. They not necesarely need a type. they can be a filter or code called input. 
##when you have a type which is not basic graphql type, you need to specify the fields that you want.
