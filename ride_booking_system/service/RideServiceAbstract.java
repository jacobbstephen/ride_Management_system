package ride_booking_system.service;

import ride_booking_system.entity.Ride;
import ride_booking_system.exceptions.RideNotFoundException;

public abstract class RideServiceAbstract implements RideService {
	private double BASE_FARE = 10;
	private double PRICE_PER_KM = 5;
	
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
	            System.out.println(" Ride marked as completed.");
	        }
	}
	
}
