package ride_booking_system.service;

import ride_booking_system.entity.Driver;
import ride_booking_system.entity.Ride;
import ride_booking_system.entity.User;
import ride_booking_system.exceptions.DriverNotFoundException;
import ride_booking_system.exceptions.RideNotFoundException;

public class BookRideService {
	private static double BASE_FARE = 10;
	private static double PRICE_PER_KM = 5;
	
      public static Ride bookRide(User user, String rideType, String pickup, String drop) throws DriverNotFoundException {
            // TODO Auto-generated method stub
            Driver[] drivers = DriverRepository.getDrivers();
		int driverCount = DriverRepository.getDriverCount();
		Driver assignedDriver = null;
		
		for(int i = 0; i < driverCount; i++) {
			if(drivers[i].isAvailable() && drivers[i].getVechileType().getName()==rideType) {
				assignedDriver = drivers[i];
				assignedDriver.setAvailable(false);
				break;
				
			}
		}
		if(assignedDriver == null) throw new DriverNotFoundException("All Drivers are busy. Couldn't assign driver for your ride. User: " + user.getName());
		double distance = new LocationService().getDistanceFromGoogleMap(pickup, drop);
		double fare = calculateFare(distance);
            return new Ride(pickup, drop, distance, fare, assignedDriver, user);
      }



      public boolean cancelRide(Ride ride) throws RideNotFoundException {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'cancelRide'");
      }

      public void rateRide(Ride ride, int rating) throws RideNotFoundException {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'rateRide'");
      }

      public void completeRide(Ride ride) throws RideNotFoundException {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'completeRide'");
      }
      
      public static double calculateFare(double distance) {
		double totalFare = BASE_FARE + (PRICE_PER_KM * distance);
		return totalFare;
	}
}
