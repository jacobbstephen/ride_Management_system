package ride_booking_system;

import ride_booking_system.entity.Vehicle;
import ride_booking_system.entity.Driver;
import ride_booking_system.entity.User;
import ride_booking_system.exceptions.RideException;
import ride_booking_system.service.BookRideService;
import ride_booking_system.service.DriverRepository;

public class RideApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// simulating the driver addition using loops
		 
		try {
			// adding drivers
			for(int i = 1; i < 10; i++) {
				if(i % 3 == 0) DriverRepository.addDrivers(new Driver("D" + i, "XXXXXXXXX" + i, new Vehicle("auto","KL024556"), true));
				else if(i % 3 == 1) DriverRepository.addDrivers(new Driver("D" + i, "XXXXXXXXX" + i, new Vehicle("bike","KL024586"), true));
				else DriverRepository.addDrivers(new Driver("D" + i, "XXXXXXXXX" + i, new Vehicle("auto","KL024956"), true));
			}
			// Generate  users
			User u1 = new User("U1", "XXXXXXXX78");
			
			
			// book ride
			BookRideService.bookRide(u1,"auto","c","a");
			
		
			
		}catch(RideException e) {
			System.err.println(e.getMessage());
		}

	}

}
