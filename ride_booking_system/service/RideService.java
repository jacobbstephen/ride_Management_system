package ride_booking_system.service;

import ride_booking_system.entity.Ride;
import ride_booking_system.entity.User;
import ride_booking_system.exceptions.DriverNotFoundException;
import ride_booking_system.exceptions.RideNotFoundException;

public interface RideService {
	double BASE_FARE = 10;
	double PRICE_PER_KM = 5;
	void bookRide(User currentUser, String pickup, String drop, String vehicleType) throws DriverNotFoundException;
	double calculateFare(double distance);
	boolean cancelRide(Ride ride) throws RideNotFoundException;
	void rateRide(Ride ride, int rating) throws RideNotFoundException;
	void completeRide(Ride ride) throws RideNotFoundException;
}

