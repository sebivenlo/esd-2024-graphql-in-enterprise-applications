package workshop.example.workshop;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public record Transaction(Integer id,
                            String date,
                            String time,
                            Integer storeId,
                            Integer staffId,
                            Integer customerId,
                            String inStore,
                            Integer order,
                            Integer lineItemId,
                            Integer productId,
                            Integer quantity,
                            Double lineItemAmount,
                            Double price,
                            String promo
                            )
                      {
    public static List<Transaction> transactions = readTransactionsFromCSV();

    public static List<Transaction> readTransactionsFromCSV(){
        List<Transaction> transactionsCSVList = new ArrayList<>();
        String fileName = "/workspaces/esd-2024-graphql-in-enterprise-applications/workshop/src/main/resources/data/transactions.csv";
        File file = new File(fileName);
        try{
            Scanner inputStream = new Scanner(file);
            while (inputStream.hasNext()) {
                String data = inputStream.next();
                String[] values = data.split(",");

                transactionsCSVList.add(new Transaction(
                    Integer.parseInt(values[0]), 
                    values[1], 
                    values[2], 
                    Integer.parseInt(values[3]), 
                    Integer.parseInt(values[4]), 
                    Integer.parseInt(values[5]), 
                    values[6], 
                    Integer.parseInt(values[7]), 
                    Integer.parseInt(values[8]), 
                    Integer.parseInt(values[9]), 
                    Integer.parseInt(values[10]), 
                    Double.parseDouble(values[11]), 
                    Double.parseDouble(values[12]), 
                    values[13]));
            }
            inputStream.close();
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }
        return transactionsCSVList;


    }

}
