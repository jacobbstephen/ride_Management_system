package ride_booking_system;


import java.io.IOException;

import ride_booking_system.entity.Ride;
import ride_booking_system.entity.User;
import ride_booking_system.exceptions.RideException;
import ride_booking_system.repositories.UserRepository;
import ride_booking_system.service.BookRideService;
import ride_booking_system.service.CompleteRideService;
import ride_booking_system.service.PaymentProcess;
import ride_booking_system.service.RateRideService;
import ride_booking_system.service.RideSummaryService;


public class RideApp {
	public static void main(String[] args) {

		User u1 = new User("U1", "XXXXXXXX78");
		Ride ride1 = new Ride("a", "b", u1.getId(), "car");
		UserRepository userRepository = new UserRepository();
		BookRideService bookRideService = new BookRideService();

		try {
			userRepository.add(u1);
			userRepository.save();
			int user_id = u1.getId();
			bookRideService.bookRide(ride1);
			int ride_id = ride1.getId();


			
			CompleteRideService completeRideService = new CompleteRideService();
			completeRideService.completeRide(ride_id, user_id);

			// RateRideService rateRideService = new RateRideService();
			// rateRideService.rateRide(ride1, 4, u1);

			// PaymentProcess payementService = new PaymentProcess();
			// payementService.makePayment(ride1, "cash", u1);

			// RideSummaryService rideSummaryService = new RideSummaryService();
			// rideSummaryService.printRideSummary(ride1);
			
		} catch (RideException | IOException e) {
			e.printStackTrace();
		}

		

		}

}
