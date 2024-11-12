package workshop.example.workshop.controller;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import workshop.example.workshop.model.Customer;

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

    @MutationMapping
    public void createCustomer(@Argument("input")Customer customer){
        Customer.createCustomer(customer);
    }
}