package workshop.example.workshop.model;

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
}
