package ride_booking_system;

import ride_booking_system.entity.Auto;
import ride_booking_system.entity.Bike;
import ride_booking_system.entity.Car;
import ride_booking_system.entity.Driver;
import ride_booking_system.entity.User;
import ride_booking_system.entity.Vehicle;
import ride_booking_system.exceptions.RideException;
import ride_booking_system.service.BookRideService;
import ride_booking_system.service.DriverRepository;

public class RideApp {

	public static void main(String[] args) {
		
		// simulating the driver addition using loops
		 
		try {
			// adding drivers
			Vehicle car = new Car();
			Vehicle auto = new Auto();
			Vehicle bike = new Bike();
;			for(int i = 1; i < 10; i++) {
				if(i % 3 == 0) DriverRepository.addDrivers(new Driver("D" + i, "XXXXXXXXX" + i, car, true));
				else if(i % 3 == 1) DriverRepository.addDrivers(new Driver("D" + i, "XXXXXXXXX" + i, bike, true));
				else DriverRepository.addDrivers(new Driver("D" + i, "XXXXXXXXX" + i, auto, true));
			}

			BookRideService bookRideService = new BookRideService();
			// Generate  users
			User u1 = new User("U1", "XXXXXXXX78");
			
			bookRideService.bookRide(u1, "A", "B", "car");

			
		
			
		}catch(RideException e) {
			System.err.println(e.getMessage());
		}

	}

}
