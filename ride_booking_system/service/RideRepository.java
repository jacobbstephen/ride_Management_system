package ride_booking_system.service;


import ride_booking_system.entity.Ride;
import ride_booking_system.exceptions.MaxLimitExceedException;

public class RideRepository {
	private static int MAX_COUNT = 20;
	private static Ride rides[] = new Ride[MAX_COUNT];
	private static int rideCount = 0;
	

	public static Ride[] getRides() {
		return rides;
	}

	public static void addRides(Ride ride) throws MaxLimitExceedException{
		if(rideCount > MAX_COUNT) throw new MaxLimitExceedException("Sorry. We cannot add any more Rides.");
		rides[rideCount++] = ride;
	}
	
}
