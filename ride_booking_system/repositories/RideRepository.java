package ride_booking_system.repositories;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ride_booking_system.entity.Driver;
import ride_booking_system.entity.Payment;
import ride_booking_system.entity.Ride;
import ride_booking_system.entity.User;
import ride_booking_system.repositories.headers.RideCSVHeaders;
import ride_booking_system.service.RideStatus;

public class RideRepository implements RepositoryInterface<Ride>, RideCSVHeaders {
	private final List<Ride> rides;
	private final String filePath = "ride_booking_system/data/rides.csv";
	PaymentRepository paymentRepository;
	
	public RideRepository(){
		rides = new ArrayList<>();
		paymentRepository = new PaymentRepository();
		// load();
	}
	
    public void add(Ride ride){
		rides.add(ride);
	}
    public Ride findById(int id){
		for(Ride ride: rides){
			if(ride.getId() == id) return ride;
		}
		return null;
	}
    public void load() {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            reader.readLine();
			
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length < 12) continue;

               	int rideId = Integer.parseInt(fields[RIDE_ID]);
				int userId = Integer.parseInt(fields[USER_ID]);
				int driverId = Integer.parseInt(fields[DRIVER_ID]);
				int paymentId = Integer.parseInt(fields[PAYMENT_ID]);
				String pickUp = fields[PICK_UP];
				String dropOff = fields[DROP_OFF];
				double distance = Double.parseDouble(fields[DISTANCE]);
				double fare = Double.parseDouble(fields[FARE]);
				RideStatus status = RideStatus.valueOf(fields[STATUS]);
				int rating= Integer.parseInt(fields[RATING]);
				Boolean paymentDone = Boolean.parseBoolean(fields[PAYMENT_DONE]);
				Payment payment = paymentRepository.findById(paymentId);
				String methodOfPayment = payment.getPaymentMethod();
				String rideType = fields[RIDE_TYPE];
				
				Ride ride = new Ride(pickUp, dropOff, userId, rideType);
				ride.setDistance(distance);
				ride.setId(rideId);
				ride.setDriver_id(driverId);
				ride.setPaymentId(paymentId);
				ride.setFare(fare);
				ride.setStatus(status);
				ride.setPaymentDone(paymentDone);
				ride.setMethodOfPayment(methodOfPayment);
				ride.setRating(rating);


				rides.add(ride);
			
               
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public List<Ride> getAll(){
		return rides;
	}
    public int size(){
		return rides.size();
	}

	public List<Ride> getRides() {
		return rides;
	}

	public void addRide(Ride ride)  {
		rides.add(ride);
	}

	public void save()  throws IOException{
		try(FileWriter writer = new FileWriter(filePath);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
        ) {
			bufferedWriter.write("rideId, userId, driverId, paymentId, pickUp, dropOff, distance, fare, status,paymentDone, rideType, rating");
			bufferedWriter.newLine();

			for (Ride ride : rides) {
				String line = makeString(ride);
				bufferedWriter.write(line);
				bufferedWriter.newLine();
			}
		} catch (IOException e) {
			System.err.println("Error writing to CSV: " + e.getMessage());
		} 
	}

	private String makeString(Ride ride) {
		return String.join(",",
			String.valueOf(ride.getId()),
			String.valueOf(ride.getUser_id()),
			String.valueOf(ride.getDriver_id()),
			String.valueOf(ride.getPaymentId()),
			ride.getPickUpLocation(),
			ride.getDropOffLocation(),
			String.valueOf(ride.getDistance()),
			String.valueOf(ride.getFare()),
			String.valueOf(ride.getStatus()),
			ride.getRating() == 0 ? "" : String.valueOf(ride.getRating())
		);
}

}
