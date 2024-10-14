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



