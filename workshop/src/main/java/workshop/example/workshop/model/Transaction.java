package workshop.example.workshop.model;

import java.util.List;
import java.util.Optional;

import workshop.example.workshop.CsvUtils;

public record Transaction(Integer id,
                            String date,
                            String time,
                            Integer storeId,
                            Integer staffId,
                            Integer customerId,
                            Integer product,
                            Integer quantity,
                            Double price,
                            String promo
                            )
                      {
    public static List<Transaction> transactions =  CsvUtils.readTransactionsFromCSV();

    public static Optional<Transaction> getTransactionById(Integer id) {
        return transactions.stream().filter(b -> b.id.equals(id)).findFirst();
    }
}
