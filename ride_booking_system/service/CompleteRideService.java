package ride_booking_system.service;

import java.io.IOException;
import java.util.Scanner;

import ride_booking_system.entity.Ride;
import ride_booking_system.exceptions.RideException;
import ride_booking_system.exceptions.RideNotFoundException;
import ride_booking_system.repositories.DriverRepository;
import ride_booking_system.repositories.RideRepository;

public class CompleteRideService {
    RideRepository rideRepository;
    DriverRepository driverRepository;
    public CompleteRideService(){
		driverRepository = new DriverRepository();
		rideRepository = new RideRepository();
	}
	


	public void completeRide(Ride ride) throws RideNotFoundException {
		if (ride == null)
			throw new RideNotFoundException("The requested ride cannot be found");
		if (ride.getStatus() == RideStatus.BOOKED) {
			ride.setStatus(RideStatus.COMPLETED);
			ride.getDriver().setAvailable(true);
			System.out.println("Ride marked as completed.");

		}
	}
}



// try {
// 				Scanner sc = new Scanner(System.in);
// 				System.out.print("Do You want to rate the ride: yes or no? :");
// 				String wantToRate = sc.next();
// 				if (wantToRate.equalsIgnoreCase("yes")) {
// 					System.out.print("On a Scale of 5 [ 1- 5], how much would u rate: ");
// 					int rating = sc.nextInt();
// 					// rateRide(ride, rating);
// 				}
// 				System.out.print("How Do You Like to pay? by Cash or UPI: ");
// 				String paymentMethod = sc.next();
// 				new PaymentProcess().makePayment(ride, paymentMethod);
// 				// printRideSummary(ride);
// 				rideRepository.save();
// 				try {
// 					driverRepository.save();
// 				} catch (IOException e) {
// 					e.printStackTrace();
// 				}

// 			} catch (RideException e) {
// 				System.err.println(e.getMessage());
// 			}
