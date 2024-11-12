package workshop.example.workshop.model;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import workshop.example.workshop.CsvUtils;

public record Customer(Integer id,
                      Integer homeStore, 
                      String name,
                      String email,
                      String customerSince,
                      String  LCNumber,
                      String bDay,
                      String gender,
                      Integer bDayYear)
                      {
    public static List<Customer> customers = CsvUtils.getCustomersCSVFile();

    public static Optional<Customer> getCustomerByID(Integer id) {
        return customers.stream()
                .filter(b -> b.id.equals(id))
                .findFirst();
    }

    public static void createCustomer(Customer customer){
        try (FileWriter writer = new FileWriter("/workspaces/esd-2024-graphql-in-enterprise-applications/workshop/src/main/resources/data/newCustomers.csv", true)) {
            String[] customerString = new String[9];
            customerString[0] = customer.id + "";
            customerString[1] = customer.homeStore + "";
            customerString[2] = customer.name;
            customerString[3] = customer.email;
            customerString[4] = customer.customerSince;
            customerString[5] = customer.LCNumber + "";
            customerString[6] = customer.bDay;
            customerString[7] = customer.gender;
            customerString[8] = customer.bDayYear + "";
            writer.append("\n");
        // Write the entire line as a single entry
        writer.append(String.join(",", customerString));

        System.out.println("Customer data appended to CSV file successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
