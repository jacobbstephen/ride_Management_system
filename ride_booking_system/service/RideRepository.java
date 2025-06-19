package ride_booking_system.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ride_booking_system.entity.Driver;
import ride_booking_system.entity.Ride;
import ride_booking_system.entity.User;

public class RideRepository {
	private final List<Ride> rides = new ArrayList<>();
	// public RideRepository(){
	// 	loadRidesFromCsv(); //future will be done .. jeev oreppe
	// }

	public List<Ride> getRides() {
		return rides;
	}

	public void addRide(String pickUpLocation,String dropOffLocation,double distance,double fare,Driver driver,User user)  {

		
		Ride ride = new Ride(pickUpLocation, dropOffLocation,
							distance, fare, driver, user);
		rides.add(ride);
		printRideSummary(ride);
	}

	public void printRideSummary(Ride ride) {
		System.out.println("====== Ride Summary ====");
		System.out.println("User: " + ride.getUser().getName() + " (" + ride.getUser().getPhoneNumber() + ")");
		System.out.println("From: " + ride.getPickUpLocation());
		System.out.println("To: " + ride.getDropOffLocation());
		System.out.println("Distance: " + ride.getDistance() + " km");
		System.out.println("Fare: ₹" + ride.getFare());
		System.out.println("Driver: " + ride.getDriver().getName() + " (" + ride.getDriver().getVechileType() + ")");
		System.out.println("Status: " + ride.getStatus());
		System.out.println("Rating: " + (ride.getRating() == 0 ? "Not Rated" : ride.getRating() + " ★"));
		System.out.println("Payment: " + (ride.getPaymentDone() ? "Payment done" : "Payment Not done"));
		System.out.println("Method of Payment: " + ride.getMethodOfPayment());
		System.out.println("======================");
	}


	public void save() {
		String filePath = "ride_booking_system/data/rides.csv";
		FileWriter fileWriter = null;
		BufferedWriter bufferedWriter = null;
		
		try {
			// Step 1: Create FileWriter
			fileWriter = new FileWriter(filePath);
			
			// Step 2: Create BufferedWriter
			bufferedWriter = new BufferedWriter(fileWriter);
			
			// Step 3: Write header
			bufferedWriter.write("User,UserPhone,PickUp,DropOff,Distance,Fare,Driver,VehicleType,Status,Rating,PaymentDone,MethodOfPayment");
			bufferedWriter.newLine();
			
			// Step 4: Write each ride
			for (Ride ride : rides) {
				String line = ride.getUser().getName() + "," +
							 ride.getUser().getPhoneNumber() + "," +
							 ride.getPickUpLocation() + "," +
							 ride.getDropOffLocation() + "," +
							 ride.getDistance() + "," +
							 ride.getFare() + "," +
							 ride.getDriver().getName() + "," +
							 ride.getDriver().getVechileType() + "," +
							 ride.getStatus() + "," +
							 (ride.getRating() == 0 ? "" : ride.getRating()) + "," +
							 ride.getPaymentDone() + "," +
							 ride.getMethodOfPayment();
				
				bufferedWriter.write(line);
				bufferedWriter.newLine();
			}
			
		} catch (IOException e) {
			System.err.println("Error writing to CSV: " + e.getMessage());
		} finally {
			// Step 5: Close resources
			try {
				if (bufferedWriter != null) bufferedWriter.close();
				if (fileWriter != null) fileWriter.close();
			} catch (IOException e) {
				System.err.println("Error closing writers: " + e.getMessage());
			}
		}
	}
}
