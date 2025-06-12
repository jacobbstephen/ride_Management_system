package ride_booking_system.service;

import ride_booking_system.entity.Ride;
import ride_booking_system.entity.User;
import ride_booking_system.exceptions.DriverNotFoundException;
import ride_booking_system.exceptions.RideNotFoundException;

public interface RideService {
	Ride bookRide(User user, String pickup, String drop) throws DriverNotFoundException;
	double calculateFare(double distance);
	boolean cancelRide(Ride ride) throws RideNotFoundException;
	void rateRide(Ride ride, int rating) throws RideNotFoundException;
	void completeRide(Ride ride) throws RideNotFoundException;
}

