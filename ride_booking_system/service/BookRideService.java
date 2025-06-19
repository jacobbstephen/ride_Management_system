package ride_booking_system.service;

import java.util.ArrayList;

import ride_booking_system.entity.Driver;
import ride_booking_system.entity.Ride;
import ride_booking_system.entity.User;
import ride_booking_system.exceptions.DriverNotFoundException;
import ride_booking_system.exceptions.RideNotFoundException;

public class BookRideService {
	private static double BASE_FARE = 10;
	private static double PRICE_PER_KM = 5;
	DriverRepository driverRepository = new DriverRepository();
	RideRepository rideRepository = new RideRepository();
	public BookRideService(){
		
	}
      public void bookRide(User user, String rideType, String pickup, String drop) throws DriverNotFoundException {
            ArrayList<Driver> drivers = driverRepository.getDrivers();
		int driverCount = driverRepository.getDriverCount();
		Driver assignedDriver = null;
		
		for(int i = 0; i < driverCount; i++) {
                  if(drivers.get(i).isAvailable() && drivers.get(i).getVechileType().getName().equals(rideType)) {
				assignedDriver = drivers.get(i);
				assignedDriver.setAvailable(false);
				break;
				
			}
		}
		if(assignedDriver == null) throw new DriverNotFoundException("All Drivers are busy. Couldn't assign driver for your ride. User: " + user.getName());
		double distance = new LocationService().getDistanceFromGoogleMap(pickup, drop);
		double fare = calculateFare(distance);
		rideRepository.addRide(pickup, drop, distance, fare, assignedDriver, user);
		rideRepository.save();

      }



      public boolean cancelRide(Ride ride) throws RideNotFoundException {
            throw new UnsupportedOperationException("Unimplemented method 'cancelRide'");
      }

      public void rateRide(Ride ride, int rating) throws RideNotFoundException {
            throw new UnsupportedOperationException("Unimplemented method 'rateRide'");
      }

      public void completeRide(Ride ride) throws RideNotFoundException {
            throw new UnsupportedOperationException("Unimplemented method 'completeRide'");
      }

      public static double calculateFare(double distance) {
		double totalFare = BASE_FARE + (PRICE_PER_KM * distance);
		return totalFare;
	}
}
