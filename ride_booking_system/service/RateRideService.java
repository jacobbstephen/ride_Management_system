package ride_booking_system.service;

import ride_booking_system.entity.Ride;
import ride_booking_system.entity.User;
import ride_booking_system.exceptions.RideNotFoundException;

public class RateRideService {
    public void rateRide(Ride ride, int rating, User user) throws RideNotFoundException {
        if (ride == null)
        	throw new RideNotFoundException("The requested ride cannot be found");
		if(!verifyUser(ride.getUser_id(), user.getId())){
			System.out.println("User does not have the option to rate this ride");
			return;
		}
		if (checkRideIsCompleted(ride.getStatus())) {
            if (rating >= 1 && rating <= 5) {
                ride.setRating(rating);
				System.out.println("Thank you for rating the ride " + rating + " stars.");
			} else {
				System.out.println("Rating must be between 1 and 5.");
			}
		} else {
			System.out.println("Cannot rate. Ride is not completed.");
		}
	}

	private boolean verifyUser(int id, int user_id){
		return (id == user_id);
	}

	private boolean checkRideIsCompleted(RideStatus ridestatus){
		return (ridestatus == RideStatus.COMPLETED);
	}
}
