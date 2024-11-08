package workshop.example.workshop.model;


import java.util.List;
import java.util.Optional;

import workshop.example.workshop.CsvUtils;

public record Product(Integer id, String group, String category, String productType, String product) {

    public static List<Product> products = CsvUtils.readProductsFromCSV();

    public static Optional<Product> getProductById(Integer id){
        return products.stream().filter(b -> b.id.equals(id)).findFirst();
    }
    
}
