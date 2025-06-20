package ride_booking_system;

import ride_booking_system.entity.Vehicle;


import java.util.Scanner;

import ride_booking_system.entity.Driver;
import ride_booking_system.entity.Ride;
import ride_booking_system.entity.User;
import ride_booking_system.exceptions.DriverNotFoundException;
import ride_booking_system.exceptions.RideException;
import ride_booking_system.repositories.DriverRepository;
import ride_booking_system.service.BookRideService;

public class RideApp {
	public static void bookAndHandleRide(User user, String rideType, String pickup, String drop) {
    Scanner sc = new Scanner(System.in);
    try {
		BookRideService bookRideService = new BookRideService();
        Ride ride = bookRideService.bookRide(user, rideType, pickup, drop);
		System.out.println("Driver assigned for user{ " + ride.getUser().getName() + "} is " + ride.getDriver().getName());

        System.out.println(user.getName() + ": Enter whether you want to complete or cancel the ride. Type: {complete or cancel}");
        String choice = sc.next();

        if (choice.equalsIgnoreCase("complete")) {
            bookRideService.completeRide(ride);
        } else if (choice.equalsIgnoreCase("cancel")) {
            bookRideService.cancelRide(ride);
        } else {
            System.out.println("❌ Invalid input.");
        }

    } catch (RideException e) {
        System.err.println("Error: " + e.getMessage());
    }
}

	public static void main(String[] args) {


		BookRideService bookRideService = new BookRideService();

		User u1 = new User("U1", "XXXXXXXX78");
		Ride ride1 = new Ride("a", "b", u1, "car");

		try {
			bookRideService.bookRide(ride1);
			System.out.println(ride1);
		} catch (DriverNotFoundException e) {

			e.printStackTrace();
		}



		// try {
		// 	// adding drivers
		// 	// for (int i = 1; i < 10; i++) {
		// 	// 	if (i % 3 == 0)
		// 	// 		driverRepository
		// 	// 				.addDrivers(new Driver("D" + i, "XXXXXXXXX" + i, new Vehicle("auto", "KL024556"), true));
		// 	// 	else if (i % 3 == 1)
		// 	// 		driverRepository
		// 	// 				.addDrivers(new Driver("D" + i, "XXXXXXXXX" + i, new Vehicle("bike", "KL024586"), true));
		// 	// 	else
		// 	// 		driverRepository
		// 	// 				.addDrivers(new Driver("D" + i, "XXXXXXXXX" + i, new Vehicle("car", "KL024956"), true));
		// 	// }

			

		// 	// Generate users
		

			
		// 	// bookAndHandleRide(u1, "auto", "c", "a");	
		// 	// bookAndHandleRide(u2, "car", "a", "b");	
			
		// } catch (RideException e) {
		// 	System.err.println(e.getMessage());
		// }

	}

}
