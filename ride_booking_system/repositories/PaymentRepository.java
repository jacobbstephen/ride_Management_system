package ride_booking_system.repositories;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


import ride_booking_system.entity.Payment;
import ride_booking_system.repositories.headers.PaymentCSVHeaders;

public class PaymentRepository implements RepositoryInterface<Payment>, PaymentCSVHeaders {
    List<Payment> payments;
    private final String path = "ride_booking_system/data/payments.csv";
    
    public PaymentRepository(){
        payments = new ArrayList<>();
        load();
    }
    
    public void add(Payment payment){
        payments.add(payment);
    }

    public List<Payment> getAll(){
        return payments;
    }

    public int size(){
        return payments.size();
    }

    public Payment findById(int id ){
        for(Payment payment: payments){
            if(payment.getId() == id) return payment;
        }
        return null;
    }

    public void load(){
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
			String line;
			while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length < 5) continue; 

                String paymentMethod = fields[PAYMENT_METHOD];
                double amount = Double.parseDouble(fields[PAYMENT_AMOUNT]);
                int id = Integer.parseInt(fields[PAYMENT_ID]);
                LocalDateTime paidAt = LocalDateTime.parse(fields[PAYMENT_TIME]);
                boolean isDone = Boolean.parseBoolean(fields[PAYMENT_DONE]);

                Payment payment = new Payment();
                payment.setFields(id, paymentMethod, amount, paidAt, isDone);
			}
		} catch(IOException e){
			e.printStackTrace();
		}
    }
    
    public void save() throws IOException{
        try (FileWriter writer = new FileWriter(path);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
        ) {
            bufferedWriter.write("ID, Amount, Method, paidAt, Complete");
            for(Payment payment: payments){
                String line = makeString(payment);
                bufferedWriter.write(line);
                bufferedWriter.newLine();
            }
            
        } catch (IOException e) {
            
        }
    }

private String makeString(Payment payment) {
    return String.join(",",
        String.valueOf(payment.getId()),
        String.valueOf(payment.getAmount()),
        payment.getPaymentMethod(),
        String.valueOf(payment.getPaidAt()),
        String.valueOf(payment.isDone())
    );
}

}
