package workshop.example.workshop;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class CustomerController {

    @QueryMapping
    public List<Customer> customers() {
        return Customer.customers;
    }

    @QueryMapping
    public Optional<Customer> customerByID(@Argument Integer id) {
        return Customer.getCustomerByID(id);
    }
}