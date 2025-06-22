package ride_booking_system.service;

import ride_booking_system.entity.Ride;
import ride_booking_system.entity.User;
import ride_booking_system.entity.Driver;
import ride_booking_system.entity.Payment;
import ride_booking_system.exceptions.PaymentException;
import ride_booking_system.repositories.DriverRepository;
import ride_booking_system.repositories.PaymentRepository;
import ride_booking_system.repositories.RideRepository;

public class PaymentProcess implements PaymentService {
	RideRepository rideRepository;
	DriverRepository  driverRepository;
	PaymentRepository paymentRepository;

	public PaymentProcess(){
		rideRepository = new RideRepository();
		driverRepository = new DriverRepository();
		paymentRepository = new PaymentRepository();
	}
	@Override
	public boolean makePayment(Ride ride, String paymentType, User user) throws PaymentException {
		if(ride == null) throw new PaymentException("There is no ride to pay");

		if(!verifyUser(ride.getUser_id(), user.getId())){
			System.out.println("User does not have the option to rate this ride");
			return false;
		}

		if(ride.getPayment() != null && ride.getPayment().isDone()) return false;

		if(ride.getStatus() != RideStatus.COMPLETED) {
			System.out.println("⚠️ Cannot pay before completing the ride.");
            return false;
		}

		int driver_id = ride.getDriver_id();
		Driver driver = driverRepository.findById(driver_id);

		System.out.println("Please pay via " + paymentType + "  Rs." + ride.getFare() + " to Driver " + driver.getName() + " for the ride");

		Payment payment = new Payment(paymentType, ride.getFare());
		
		ride.setPaymentId(payment.getId());
		ride.setPaymentDone(true);
		ride.setPayment(payment);
		ride.setMethodOfPayment(paymentType);
		return true;
	}

	private boolean verifyUser(int id, int user_id){
		return (id == user_id);
	}
}

/***
 * 
 * to do make the payment as id, and not pass as a whole payment object
 * let ride store the id and not the object of payment
 * so refactor this so that we can search for payment based on the id, and not directly access them
 */