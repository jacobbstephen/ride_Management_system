package ride_booking_system.service;

import ride_booking_system.entity.Driver;
import ride_booking_system.entity.Ride;
import ride_booking_system.entity.User;
import ride_booking_system.exceptions.DriverNotFoundException;



public class BikeRide extends RideServiceAbstract {
	
	@Override
	public Ride bookRide(User user, String pickup, String drop) throws DriverNotFoundException {
		Driver[] drivers = DriverRepository.getDrivers();
		int driverCount = DriverRepository.getDriverCount();
		Driver assignedDriver = null;
		
		for(int i = 0; i < driverCount; i++) {
			if(drivers[i].isAvailable() && drivers[i].getVechileType().equalsIgnoreCase("bike")) {
				assignedDriver = drivers[i];
				assignedDriver.setAvailable(false);
				break;
				
			}
		}
		if(assignedDriver == null) throw new DriverNotFoundException("All Drivers are busy. Couldn't assign driver for your ride User: " + user.getName());
		double distance = new LocationService().getDistanceFromGoogleMap(pickup, drop);
		double fare = calculateFare(distance);
		return new Ride(pickup, drop, distance, fare, assignedDriver, user);
	}	
}
