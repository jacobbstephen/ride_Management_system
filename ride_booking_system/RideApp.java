package ride_booking_system;

import ride_booking_system.entity.Vehicle;
import ride_booking_system.entity.Driver;
import ride_booking_system.entity.User;
import ride_booking_system.exceptions.RideException;
import ride_booking_system.repositories.DriverRepository;
import ride_booking_system.service.BookRideService;

public class RideApp {

	public static void main(String[] args) {
		
		
		// simulating the driver addition using loops
		 DriverRepository driverRepository = new DriverRepository();
		try {
			// adding drivers
			for(int i = 1; i < 10; i++) {
				if(i % 3 == 0) driverRepository.addDrivers(new Driver("D" + i, "XXXXXXXXX" + i, new Vehicle("auto","KL024556"), true));
				else if(i % 3 == 1) driverRepository.addDrivers(new Driver("D" + i, "XXXXXXXXX" + i, new Vehicle("bike","KL024586"), true));
				else driverRepository.addDrivers(new Driver("D" + i, "XXXXXXXXX" + i, new Vehicle("car","KL024956"), true));
			}
			// Generate  users
			User u1 = new User("U1", "XXXXXXXX78");
			User u2 = new User("Jeev","9947712302");
			
			// book ride
			BookRideService bookRideService = new BookRideService();
			bookRideService.bookRide(u1,"auto","c","a");
			bookRideService.bookRide(u2,"car","c","a");

		
			
		}catch(RideException e) {
			System.err.println(e.getMessage());
		}

	}

}
