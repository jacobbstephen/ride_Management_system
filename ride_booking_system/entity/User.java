package ride_booking_system.entity;

import java.util.Scanner;

import ride_booking_system.exceptions.InvalidRideTypeException;
import ride_booking_system.exceptions.RideException;
import ride_booking_system.exceptions.RideNotFoundException;
import ride_booking_system.service.AutoRide;
import ride_booking_system.service.BikeRide;
import ride_booking_system.service.CarRide;
import ride_booking_system.service.CashPaymentService;
import ride_booking_system.service.PayementService;
import ride_booking_system.service.RideRepository;
import ride_booking_system.service.RideServiceAbstract;
import ride_booking_system.service.UPIPaymentService;

public class User {
	private String name;
	private String phoneNumber;
	private Ride currentRide = null; 
	public User(String name, String phoneNumber) {
		this.name = name;
		this.phoneNumber = phoneNumber;
	}
	public String getName() {
		return name;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public Ride getCurrentRide() {
		return currentRide;
	}
	public void bookRide(String pickup, String drop, String rideType) throws InvalidRideTypeException{
		RideServiceAbstract ride = null;
		if(rideType.equalsIgnoreCase("bike")) ride = new BikeRide();
		else if(rideType.equalsIgnoreCase("Car")) ride = new CarRide();
		else if(rideType.equalsIgnoreCase("auto")) ride = new AutoRide();
		else throw new InvalidRideTypeException("The requested ride type is not valid");
		
		 try {
	            currentRide = ride.bookRide(this, pickup, drop); 
	            System.out.println(currentRide.getDriver().getVechileType() + " ride booked successfully  for " + name);
	            RideRepository.addRides(currentRide);
	        } catch (RideException e) {
	            System.out.println("Booking failed: " + e.getMessage());
	        }
	}
	
	private RideServiceAbstract getRideServiceFromCurrentRide() {
        String type = currentRide.getDriver().getVechileType().toLowerCase();

        switch (type) {
            case "bike":
                return new BikeRide();
            case "car":
                return new CarRide();
            case "auto":
                return new AutoRide();
            default:
                throw new IllegalArgumentException("Unknown vehicle type: " + type);
        }
    }
	
	 public void completeCurrentRide() {
	        if (currentRide == null) {
	            System.out.println("No ride to complete.");
	            return;
	        }

	        RideServiceAbstract rideService = getRideServiceFromCurrentRide();
	        try {
	            rideService.completeRide(currentRide);  
	            Scanner sc = new Scanner(System.in);
	            System.out.print("Do You want to rate the ride: yes or no? :");
	            String answer = sc.next();
	            if(answer.equalsIgnoreCase("yes")) {
	            	System.out.print("On a Scale of 5 [ 1- 5], how much would u rate: ");
	            	int rating = sc.nextInt();
	            	rateCurrentRide(rating);
	            }
	            System.out.print("How Do You Like to pay? by cash or UPI: ");
	            answer = sc.next();
	            if(answer.equalsIgnoreCase("UPI"))  new UPIPaymentService().makePayment(currentRide);
	            else if(answer.equalsIgnoreCase("Cash"))  new CashPaymentService().makePayment(currentRide);
	            else System.out.println("Invalid payment method");
	            currentRide.printRideSummary();
	        } catch (RideException e ) {
	            System.out.println("Completing ride failed: " + e.getMessage());
	        }
	   } 
	 public void rateCurrentRide(int rating) {
	        if (currentRide == null) {
	            System.out.println("No ride to rate.");
	            return;
	        }

	        RideServiceAbstract rideService = getRideServiceFromCurrentRide();
	        try {
	            rideService.rateRide(currentRide, rating);
	        } catch (RideNotFoundException e) {
	            System.out.println("Rating failed: " + e.getMessage());
	        }
	    }
	 
	 public void cancelCurrentRide() {
	        if (currentRide == null) {
	            System.out.println("You have no ride to cancel.");
	            return;
	        }

	        RideServiceAbstract rideService = getRideServiceFromCurrentRide();
	        try {
	            rideService.cancelRide(currentRide);
	            currentRide = null;
	        } catch (RideNotFoundException e) {
	            System.out.println("Cancel failed: " + e.getMessage());
	        }
	    }
	
}
