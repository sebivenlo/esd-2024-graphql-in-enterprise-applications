Gives clients the power to ask for exactly what they need

Example: Building social media app, and you only need to display the `username` and `profile picture` for a user.
- With traditional API (like REST), if you ask for the user profile, you might get a lot of extra data you do not need, like their full name, email, address, etc.
- But with GaphQL, you can specifically ask for just the `username`, and `profilePicture`.
- Client chooses which fields to requests (`username` and `profilePicture`).

```
{
  user(id: 1) {
    username
    profilePicture
  }
}
```
Server response: The server returns exactly what was asked for.

```
{
  "data": {
    "user": {
      "username": "JohnDoe",
      "profilePicture": "profile.jpg"
    }
  }
}
```
With Rest example:

The endpoint for user data is `GET/user/1` (for user with `id:1`)

```
GET /users/1
```

Response of the server:

```
{
  "id": 1,
  "username": "JohnDoe",
  "fullName": "John Doe",
  "email": "john@example.com",
  "profilePicture": "profile.jpg",
  "address": "123 Main Street",
  "phoneNumber": "555-1234",
  "birthDate": "1990-01-01"
}

```



### Quick remainder REST
REST is a It is architectural style for distributed hypermedia systems, REST implies a series of constraints about how Server and Client should interact. 

- Resource: https://ics.uci.edu/~fielding/pubs/dissertation/rest_arch_style.htm

### Over-fetching in REST
<img width="617" alt="Screenshot 2024-10-10 at 15 13 01" src="https://github.com/user-attachments/assets/52070b52-e4be-48cb-bbac-5ce63d4e9690">

- In this example, the REST API request (GET https://sample.com/person/1) returns a JSON response with many details about the person: `firstName`, `email`, and  `relationshipStatus`. However, if the client only needed the `firstName` and `lastName`, the REST API is stil sending unnecessary fields like `middleName`, `email`, and `relationshipStatus`. This is called over-fetching, as the client get more data than required. 
- The GraphQL query (`person {firstName, lastName}`) explicitly requests only the fields that are needed - `firstName` and `lastName`. The server responds with exctly those fields without any extra data. This demonstrates precision in data fetching, which optimizes bandwidth and improves performance.

- Resource: https://medium.com/nerd-for-tech/graphql-vs-restful-e1a99fd14285
  
### Under-fetching in REST
  <img width="772" alt="Screenshot 2024-10-10 at 15 20 59" src="https://github.com/user-attachments/assets/f03f0a17-ff3a-4c54-adab-5b8592330bd2">
  
- Each REST endpoint performs a specific operation (e.g., fetching inventory or managing orders). To retrieve details about a specific order along with its related inventory data, separate calls must be made to /store/order/{orderId} for the order details and /store/inventory for inventory information. This approach requires multiple requests to gather all the necessary data, leading to increased latency and complexity on the client side.
  
- Resource: https://www.geeksforgeeks.org/what-are-over-fetching-and-under-fetching/

<img width="1399" alt="Screenshot 2024-10-10 at 15 41 16" src="https://github.com/user-attachments/assets/9f17ab54-c754-4029-bafa-d79832e06524">

### Example

If a client needs information about a specific member, the books they have rented, and the authors of those books, multiple API calls would be required:
1. First, the client fetches the member’s details using `/member/{id}`.
2. Then, to retrieve rental information, the client needs to make another call to `/member/{id}/rentals`.
3. To get details about the books included in the rental, further calls would need to be made to `/rental/{id}/book/{id}` for each book.
4. Finally, the client would need another set of calls to `/author/{id}` to fetch information about the authors of those books.

- Resource: https://medium.com/@suhas_chatekar/visualising-complex-apis-using-api-map-f09f617acb32

In an enterprise environment, these inefficiencies can result in slower application performance, higher infrastructure costs due to wasted resources, and a poor user experience. Addressing these issues becomes crucial to maintaining scalability, efficiency, and responsiveness, especially as systems grow and the volume of data requests increases. 

### Intro to GraphQL
That is why it was developed 
GraphQL is a query language for APIs that allows clients to request specific data in a single request. The major advantages over REST are:
* Client-driven queries: Clients request only what they need, preventing over-fetching.
* Single endpoint: Instead of multiple endpoints in REST, GraphQL operates on a single endpoint, making interactions simpler.
Restaurant analogy:
* Imagine you’re in a restaurant (API). With REST, you ask for a full meal (data), and the waiter brings everything—appetizer, main course, and dessert—even if you only wanted a salad.
* With GraphQL, you tell the waiter (API) exactly what you want, and they bring only that—just the salad, no extras.

Why Enterprises Adopt GraphQL:
Enterprises increasingly move from REST to GraphQL due to the flexibility and efficiency that large-scale systems need:
* Efficiency: By allowing clients to fetch only the needed data, it reduces server load and bandwidth use, making applications faster and more scalable.
* Improved Developer Experience: Teams can work independently on the client and server sides, reducing the need for constant communication between them.
* Flexibility Across Teams: As companies grow, GraphQL allows multiple teams to contribute to a unified API, without the rigid constraints of traditional APIs.


Why Query Language:
- Allows clients (like apps or websites) to query (ask for) specific data from server, just like a database query asks for specific information in the database.

The key in the GraphQl lets the client define the structure of the response, meaning the client has full control of what data it gets back. This is why GraphQL is called query language for APIs.

In GraphQL the client can ask for deeply nested fields or specific relationships between data. 

For example, if the client wants a user's posts along with the comments for each posts

```
{
  user(id: 1) {
    username
    posts {
      title
      comments {
        content
      }
    }
  }
}
```
Instead of having to hit different REST endpoints like `/user` `/posts` `/comments`

GraphQL query is written on the clien-side code, like app Swift or React:

```
// In a React app, this is a client-side GraphQL query
const GET_USER_DATA = gql`
  {
    user(id: 1) {
      name
      profilePicture
    }
  }
`;
```





