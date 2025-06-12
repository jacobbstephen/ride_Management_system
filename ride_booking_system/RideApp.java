package ride_booking_system;

import ride_booking_system.entity.Driver;
import ride_booking_system.entity.User;
import ride_booking_system.exceptions.RideException;
import ride_booking_system.service.DriverRepository;

public class RideApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// simulating the driver addition using loops
		 
		try {
			// adding drivers
			for(int i = 1; i < 10; i++) {
				if(i % 3 == 0) DriverRepository.addDrivers(new Driver("D" + i, "XXXXXXXXX" + i, "car", true));
				else if(i % 3 == 1) DriverRepository.addDrivers(new Driver("D" + i, "XXXXXXXXX" + i, "bike", true));
				else DriverRepository.addDrivers(new Driver("D" + i, "XXXXXXXXX" + i, "auto", true));
			}
			// Generate  users
			User u1 = new User("U1", "XXXXXXXX78");
			
			
			// book ride
			u1.bookRide("C", "B", "Car");
			u1.completeCurrentRide();
			
		
			
		}catch(RideException e) {
			System.err.println(e.getMessage());
		}

	}

}
