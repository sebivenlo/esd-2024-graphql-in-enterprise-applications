package workshop.example.workshop;

public record Customer(Integer ID,
                      String homeStore, 
                      String fName, 
                      String lName, 
                      String email,
                      String customerSince,
                      int  LCNumber,
                      String bDay,
                      String gender,
                      int bDayYear)
                      {
  // public static List<Book> books = Arrays.asList(
  //   new Book(1, "Quran", 604, 3),
  //   new Book(2, "Harry Potter", 700, 2),
  //   new Book(3, "Foobar", 100, 1),
  //   new Book(4, "Spring Boot", 344, 2)
  // );
}
