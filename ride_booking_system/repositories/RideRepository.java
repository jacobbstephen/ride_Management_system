package ride_booking_system.repositories;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ride_booking_system.entity.Ride;

public class RideRepository implements RepositoryInterface<Ride> {
	private final List<Ride> rides;
	private final String filePath = "ride_booking_system/data/rides.csv";

	public RideRepository(){
		rides = new ArrayList<>();
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
    public void load(){// TODO:  TO BE IMPLEMENTED BASED ON REQUIREMENT

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
			bufferedWriter.write("User,UserPhone,PickUp,DropOff,Distance,Fare,Driver,VehicleType,Status,Rating,PaymentDone,MethodOfPayment");
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
				ride.getUser().getName(),
				ride.getUser().getPhoneNumber(),
				ride.getPickUpLocation(),
				ride.getDropOffLocation(),
				String.valueOf(ride.getDistance()),
				String.valueOf(ride.getFare()),
				ride.getDriver().getName(),
				ride.getDriver().getVechileType().getName(),
				String.valueOf(ride.getStatus()),
				ride.getRating() == 0 ? "" : String.valueOf(ride.getRating()),
				String.valueOf(ride.getPaymentDone()),
				ride.getMethodOfPayment()
			);
	}

}
