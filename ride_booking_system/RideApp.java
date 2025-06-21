package ride_booking_system;


import ride_booking_system.entity.Ride;
import ride_booking_system.entity.User;
import ride_booking_system.exceptions.RideException;
import ride_booking_system.service.BookRideService;
import ride_booking_system.service.CompleteRideService;
import ride_booking_system.service.PaymentProcess;
import ride_booking_system.service.RateRideService;
import ride_booking_system.service.RideSummaryService;


public class RideApp {
	public static void main(String[] args) {

		User u1 = new User("U1", "XXXXXXXX78");
		Ride ride1 = new Ride("a", "b", u1, "car");
		try {
			BookRideService bookRideService = new BookRideService();
			bookRideService.bookRide(ride1);
			

			CompleteRideService completeRideService = new CompleteRideService();
			completeRideService.completeRide(ride1);

			RateRideService rateRideService = new RateRideService();
			rateRideService.rateRide(ride1, 4);

			PaymentProcess payementService = new PaymentProcess();
			payementService.makePayment(ride1, "cash");

			RideSummaryService rideSummaryService = new RideSummaryService();
			rideSummaryService.printRideSummary(ride1);
			


		} catch (RideException e) {
			e.printStackTrace();
		}
		

		}

}
