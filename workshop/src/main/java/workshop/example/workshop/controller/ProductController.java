package workshop.example.workshop.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import workshop.example.workshop.model.Product;

@Controller
public class ProductController {

    @QueryMapping
    public List<Product> products(){
        return Product.products;
    }

    @QueryMapping
    public Optional<Product> productById(@Argument Integer id){
        return Product.getProductById(id);
    }
}
