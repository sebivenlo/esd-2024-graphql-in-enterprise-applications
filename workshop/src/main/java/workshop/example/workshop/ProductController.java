package workshop.example.workshop;

import java.util.List;

import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class ProductController {
    
    @QueryMapping
    public List<Product> products(){
        return Product.products;
    }
}
