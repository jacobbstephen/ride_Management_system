package ride_booking_system.service;

import java.util.ArrayList;
import ride_booking_system.entity.Driver;
import ride_booking_system.entity.Ride;
import ride_booking_system.exceptions.DriverNotFoundException;
import ride_booking_system.repositories.DriverRepository;
import ride_booking_system.repositories.RideRepository;

public class BookRideService {
	
	DriverRepository driverRepository;
	RideRepository rideRepository;
	
	double BASE_FARE = 10;
	double PRICE_PER_KM = 5;

	public BookRideService(){
		driverRepository = new DriverRepository();
		rideRepository = new RideRepository();
	}

	public void bookRide(Ride ride) throws DriverNotFoundException {
		Driver assignedDriver = findDriver(ride.getRideType());
		if (assignedDriver == null)
			throw new DriverNotFoundException(
					"All Drivers are busy. Couldn't assign driver for your ride. User: " + ride.getUser().getName());

		assignedDriver.setAvailable(false);
		double distance = new LocationService().getDistanceFromGoogleMap(ride.getPickUpLocation(), ride.getDropOffLocation());
		double fare = calculateFare(distance);
		ride.setFare(fare);
		ride.setDistance(distance);
		ride.setStatus(RideStatus.BOOKED);
		ride.setDriver(assignedDriver);
		rideRepository.addRide(ride);
	}

	private Driver findDriver(String rideType) {
		ArrayList<Driver> drivers = driverRepository.getDrivers();
		int driverCount = driverRepository.getDriverCount();
		Driver assignedDriver = null;
		for (int i = 0; i < driverCount; i++) {
			if (drivers.get(i).isAvailable() && drivers.get(i).getVechileType().getName().equals(rideType)) {
				assignedDriver = drivers.get(i);
				assignedDriver.setAvailable(false);
				break;

			}
		}

		return assignedDriver;
	}

	
	private double calculateFare(double distance) {
		return BASE_FARE + (PRICE_PER_KM * distance);
	}
}