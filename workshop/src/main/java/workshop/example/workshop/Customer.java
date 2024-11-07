package workshop.example.workshop;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public record Customer(Integer id,
                      Integer homeStore, 
                      String fName, 
                      String lName, 
                      String email,
                      String customerSince,
                      Integer  LCNumber,
                      String bDay,
                      String gender,
                      Integer bDayYear)
                      {
    public static List<Customer> customers = Arrays.asList(
    new Customer(1, 3, "Kelly", "Key", "email@gmail.co", "2017-01-04", 12345, "1950-05-29", "M", 1950),
    new Customer(2, 3, "Kelly", "Key", "email@gmail.co", "2017-01-04", 12345, "1950-05-29", "M", 1950),
    new Customer(3, 3, "Kelly", "Key", "email@gmail.co", "2017-01-04", 12345, "1950-05-29", "M", 1950)
    );

    public static Optional<Customer> getCustomerByID(Integer id) {
    return customers.stream()
                .filter(b -> b.id.equals(id))
                .findFirst();
    }
}
