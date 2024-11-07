package workshop.example.workshop;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public record Product(Integer id, String group, String category, String productType, String product) {

    public static List<Product> products = readProductsFromCSV();
        
    private static List<Product> readProductsFromCSV() {
        List<Product> productCSVList = new ArrayList<>();
        String fileName = "/workspaces/esd-2024-graphql-in-enterprise-applications/workshop/src/main/resources/data/product.csv";
        File file = new File(fileName);
    
        try (Scanner inputStream = new Scanner(file)) {
            StringBuilder currentLine = new StringBuilder();
            if (inputStream.hasNextLine()) {
                inputStream.nextLine();
            }
            while (inputStream.hasNextLine()) {
                String line = inputStream.nextLine();
    
                // Check if line starts a new record (e.g., if product_id is numeric)
                if (line.matches("^\\d+,.*")) {
                    // Add previous record if currentLine is not empty
                    if (currentLine.length() > 0) {
                        addProductFromLine(productCSVList, currentLine.toString());
                    }
                    // Start new record
                    currentLine = new StringBuilder(line);
                } else {
                    // Append line to current record (assuming it's a continuation)
                    currentLine.append(" ").append(line.trim());
                }
            }
            // Add the last record
            if (currentLine.length() > 0) {
                addProductFromLine(productCSVList, currentLine.toString());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    
        return productCSVList;
    }
    
    private static void addProductFromLine(List<Product> productCSVList, String line) {
        String[] values = line.split(",");
        // Ensure there are enough columns to avoid ArrayIndexOutOfBoundsException
        if (values.length >= 5) {
            productCSVList.add(new Product(
                Integer.parseInt(values[0]), // id
                values[1], // group
                values[2], // category
                values[3], // productType
                values[4]  // product
            ));
        } else {
            System.out.println("Skipping invalid line: " + line);
        }
    }

    public static Optional<Product> getProductById(Integer id){
        List<Product> productsforList = readProductsFromCSV();
        return productsforList.stream().filter(b -> b.id.equals(id)).findFirst();
    }
    
}
