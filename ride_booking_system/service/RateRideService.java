package ride_booking_system.service;

import ride_booking_system.entity.Ride;
import ride_booking_system.exceptions.RideNotFoundException;

public class RateRideService {
    public void rateRide(Ride ride, int rating) throws RideNotFoundException {
		if (ride == null)
			throw new RideNotFoundException("The requested ride cannot be found");
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
}
