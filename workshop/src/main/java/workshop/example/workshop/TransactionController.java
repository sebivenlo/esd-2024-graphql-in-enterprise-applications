package workshop.example.workshop;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class TransactionController {

    @QueryMapping
    public List<Transaction> transactions() {
        return Transaction.transactions;
    }
}