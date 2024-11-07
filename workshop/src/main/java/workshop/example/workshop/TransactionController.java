package workshop.example.workshop;

import org.springframework.graphql.data.method.annotation.Argument;
//import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class TransactionController {

    @QueryMapping
    public List<Transaction> transactions() {
        return Transaction.transactions;
    }

    @QueryMapping
    public Optional<Transaction> transactionById(@Argument Integer id){
        return Transaction.getTransactionById(id);
    }

    @SchemaMapping
    public Optional<Product> product(Transaction transaction){
        return Product.getProductById(transaction.product());
    }

    @SchemaMapping
    public Optional<Customer> customer(Transaction transaction){
        return Customer.getCustomerByID(transaction.customerId());
    }

}