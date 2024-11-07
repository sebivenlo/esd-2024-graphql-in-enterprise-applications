package workshop.example.workshop;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public record Product(Integer id, String group, String category, String productType, String product) {
    public static List<Product> products = readProductsFromCSV();
        
    private static List<Product> readProductsFromCSV() {
        List<Product> productCSVList = new ArrayList<>();
        String fileName = "/workspaces/esd-2024-graphql-in-enterprise-applications/workshop/src/main/resources/data/product.csv";
        File file = new File(fileName);
        try{
            Scanner inputStream = new Scanner(file);
            while (inputStream.hasNext()) {
                String data = inputStream.next();
                System.out.println(data);
                String[] values = data.split(",");

                productCSVList.add(new Product(Integer.parseInt(values[0]), values[1], values[2], values[3], values[4]));
            }
            inputStream.close();
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }
        return productCSVList;
    }
    
}
