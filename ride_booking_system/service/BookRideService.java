package ride_booking_system.service;

import java.util.Scanner;

import ride_booking_system.entity.Driver;
import ride_booking_system.entity.Ride;
import ride_booking_system.entity.User;
import ride_booking_system.exceptions.DriverNotFoundException;
import ride_booking_system.exceptions.RideException;
import ride_booking_system.exceptions.RideNotFoundException;

public class BookRideService implements RideService {
	
	private double BASE_FARE = 10;
	private double PRICE_PER_KM = 5;

	@Override
	public void bookRide(User currentUser, String pickup, String drop, String vehicleType) throws DriverNotFoundException {
		// TODO Auto-generated method stub
		
		Driver[] drivers = DriverRepository.getDrivers();
		int driverCount = DriverRepository.getDriverCount();
		Driver assignedDriver = null;
		
		
		for(int i = 0; i < driverCount; i++) {
			Driver driver =  drivers[i];
			if(driver.isAvailable() && driver.getVehicleType().getType().equals(vehicleType)) {
				assignedDriver = drivers[i];
				assignedDriver.setAvailable(false);
				break;
			}
		}
		if(assignedDriver == null) throw new DriverNotFoundException("All Drivers are busy. Couldn't assign driver for your ride. User: " + currentUser.getName());
		double distance = new LocationService().getDistanceFromGoogleMap(pickup, drop);
		double fare = calculateFare(distance);
		try {
			Ride ride = new Ride(pickup, drop, distance, fare, assignedDriver, currentUser);
			RideRepository.addRides(ride);
			System.out.println(ride.getDriver().getVehicleType().getType().toUpperCase() + " ride booked successfully  for " + currentUser.getName());
			completeRide(ride);
			
		}catch(RideException e) {
			System.err.println(e.getMessage());
		}
	}
	
	
	@Override
	public double calculateFare(double distance) {
		double totalFare = BASE_FARE + (PRICE_PER_KM * distance);
		return totalFare;
	}
	
	@Override
	public boolean cancelRide(Ride ride) throws RideNotFoundException{
		if(ride == null) throw new RideNotFoundException("The requested ride cannot be found");
		if(ride.getStatus() == RideStatus.BOOKED) {
			ride.setStatus(RideStatus.CANCELLED);
			ride.getDriver().setAvailable(true);
			System.out.println("Your ride is cancelled");
			return true;
		}
		System.out.println("Cannot cancel. Ride is either already cancelled or completed.");
		return false;
	}
	
	@Override
	public void rateRide(Ride ride, int rating) throws RideNotFoundException {
		if(ride == null) throw new RideNotFoundException("The requested ride cannot be found");
        if (ride.getStatus() == RideStatus.COMPLETED) {
            if (rating >= 1 && rating <= 5) {
                ride.setRating(rating);
                System.out.println("⭐ Thank you for rating the ride " + rating + " stars.");
            } else {
                System.out.println("❌ Rating must be between 1 and 5.");
            }
        } else {
            System.out.println("⚠️ Cannot rate. Ride is not completed.");
        }
    }
	
	@Override
	public void completeRide(Ride ride) throws RideNotFoundException {
		if(ride == null) throw new RideNotFoundException("The requested ride cannot be found");
		 if (ride.getStatus() == RideStatus.BOOKED) {
	            ride.setStatus(RideStatus.COMPLETED);
	            ride.getDriver().setAvailable(true);
	            System.out.println("Ride marked as completed.");
	            
	            try {
	            	Scanner sc = new Scanner(System.in);
		            System.out.print("Do You want to rate the ride: yes or no? :");
		            String wantToRate = sc.next();
		            if(wantToRate.equalsIgnoreCase("yes")) {
		            	System.out.print("On a Scale of 5 [ 1- 5], how much would u rate: ");
		            	int rating = sc.nextInt();
		            	rateRide(ride, rating);
		            }
		            
		            System.out.print("How Do You Like to pay? by Cash or UPI: ");
		            String paymentMethod = sc.next();
		            new PaymentProcess().makePayment(ride, paymentMethod);
		            ride.printRideSummary();
	            	
	            }catch(RideException e) {
	            	System.err.println(e.getMessage());
	            }
	            
	            
	             
	        }
	}
	
}
