package workshop.example.workshop;

import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class GreetingResolver {

    @QueryMapping
    public String greeting() {
        return "Hello, GraphQL!";
    }
}
