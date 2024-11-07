package workshop.example.workshop;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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
    private static List<Customer> getCustomersCSVFile() {
            List<Customer> customers = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("/workspaces/esd-2024-graphql-in-enterprise-applications/workshop/src/main/resources/data/customer.csv"))) {
            String line;
            // Skip the header
            br.readLine();

            while ((line = br.readLine()) != null) {
                // Split the line by commas, considering possible commas within quotes
                String[] fields = line.split(",");
                // Parse each field
                int customerId = Integer.parseInt(fields[0]);
                int homeStore = Integer.parseInt(fields[1]);
                String name = fields[2];
                String email = fields[3];
                String customerSince = fields[4];
                String loyaltyCardNumber =fields[5];
                String birthdate = fields[6];
                String gender = fields[7];
                int birthYear = Integer.parseInt(fields[8]);

                // Create a new Customer object and add it to the list
                Customer customer = new Customer(customerId, homeStore,name , email, customerSince,
                                                 loyaltyCardNumber, birthdate, gender, birthYear);
                customers.add(customer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return customers;
        }
        public static List<Customer> customers = getCustomersCSVFile();

    public static Optional<Customer> getCustomerByID(Integer id) {
    return customers.stream()
                .filter(b -> b.id.equals(id))
                .findFirst();
    }
}
