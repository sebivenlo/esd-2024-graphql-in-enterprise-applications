package workshop.example.workshop;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// import workshop.example.workshop.model.Customer;
// import workshop.example.workshop.model.Product;
// import workshop.example.workshop.model.Transaction;

//class with helper methods to read csv files
public class CsvUtils {

    //HELPER METHODS FOR CUSTOMER

    // public static List<Customer> getCustomersCSVFile(){
    //     List<Customer> customers = new ArrayList<>();

    //     try (BufferedReader br = new BufferedReader(new FileReader("/workspaces/esd-2024-graphql-in-enterprise-applications/workshop/src/main/resources/data/customer.csv"))) {
    //         String line;
    //         // Skip the header
    //         br.readLine();

    //         while ((line = br.readLine()) != null) {
    //             // Split the line by commas, considering possible commas within quotes
    //             String[] fields = line.split(",");
    //             // Parse each field
    //             int customerId = Integer.parseInt(fields[0]);
    //             int homeStore = Integer.parseInt(fields[1]);
    //             String name = fields[2];
    //             String email = fields[3];
    //             String customerSince = fields[4];
    //             String loyaltyCardNumber =fields[5];
    //             String birthdate = fields[6];
    //             String gender = fields[7];
    //             int birthYear = Integer.parseInt(fields[8]);

    //             // Create a new Customer object and add it to the list
    //             Customer customer = new Customer(customerId, homeStore,name , email, customerSince,
    //                                              loyaltyCardNumber, birthdate, gender, birthYear);
    //             customers.add(customer);
    //         }
    //     } catch (IOException e) {
    //         e.printStackTrace();
    //     }

    //     return customers;
    // }

    //HELPER METHODS FOR PRODUCTS

    // public static List<Product> readProductsFromCSV() {
    //     List<Product> productCSVList = new ArrayList<>();
    //     String fileName = "/workspaces/esd-2024-graphql-in-enterprise-applications/workshop/src/main/resources/data/product.csv";
    //     File file = new File(fileName);
    
    //     try (Scanner inputStream = new Scanner(file)) {
    //         StringBuilder currentLine = new StringBuilder();
    //         if (inputStream.hasNextLine()) {
    //             inputStream.nextLine();
    //         }
    //         while (inputStream.hasNextLine()) {
    //             String line = inputStream.nextLine();
    
    //             // Check if line starts a new record (e.g., if product_id is numeric)
    //             if (line.matches("^\\d+,.*")) {
    //                 // Add previous record if currentLine is not empty
    //                 if (currentLine.length() > 0) {
    //                     addProductFromLine(productCSVList, currentLine.toString());
    //                 }
    //                 // Start new record
    //                 currentLine = new StringBuilder(line);
    //             } else {
    //                 // Append line to current record (assuming it's a continuation)
    //                 currentLine.append(" ").append(line.trim());
    //             }
    //         }
    //         // Add the last record
    //         if (currentLine.length() > 0) {
    //             addProductFromLine(productCSVList, currentLine.toString());
    //         }
    //     } catch (FileNotFoundException e) {
    //         e.printStackTrace();
    //     }
    
    //     return productCSVList;
    // }

    // private static void addProductFromLine(List<Product> productCSVList, String line) {
    //     String[] values = line.split(",");
    //     // Ensure there are enough columns to avoid ArrayIndexOutOfBoundsException
    //     if (values.length >= 5) {
    //         productCSVList.add(new Product(
    //             Integer.parseInt(values[0]), // id
    //             values[1], // group
    //             values[2], // category
    //             values[3], // productType
    //             values[4]  // product
    //         ));
    //     } else {
    //         System.out.println("Skipping invalid line: " + line);
    //     }
    // }

    

    //helper methods for TRANSACTIONS


    // public static List<Transaction> readTransactionsFromCSV(){
    //     List<Transaction> transactionsCSVList = new ArrayList<>();
    //     String fileName = "/workspaces/esd-2024-graphql-in-enterprise-applications/workshop/src/main/resources/data/transactions.csv";
    //     File file = new File(fileName);
    //     try{
    //         Scanner inputStream = new Scanner(file);
    //         if (inputStream.hasNextLine()) {
    //             inputStream.nextLine();
    //         }
    //         while (inputStream.hasNext()) {
    //             String data = inputStream.next();
    //             String[] values = data.split(",");

    //             transactionsCSVList.add(new Transaction(
    //                 Integer.parseInt(values[0]), 
    //                 values[1], 
    //                 values[2], 
    //                 Integer.parseInt(values[3]), 
    //                 Integer.parseInt(values[4]), 
    //                 Integer.parseInt(values[5]), 
    //                 values[6], 
    //                 Integer.parseInt(values[7]), 
    //                 Integer.parseInt(values[8]), 
    //                 Integer.parseInt(values[9]), 
    //                 Integer.parseInt(values[10]), 
    //                 Double.parseDouble(values[11]), 
    //                 Double.parseDouble(values[12]), 
    //                 values[13]));
    //         }
    //         inputStream.close();
    //     }catch(FileNotFoundException e){
    //         e.printStackTrace();
    //     }
    //     return transactionsCSVList;
    // }


    
}
