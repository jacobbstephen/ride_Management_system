package ride_booking_system.service;

import ride_booking_system.entity.Ride;
import ride_booking_system.entity.Payment;
import ride_booking_system.exceptions.PaymentException;

public class PaymentProcess implements PaymentService {
	@Override
	public boolean makePayment(Ride ride, String paymentType) throws PaymentException {
		if(ride == null) throw new PaymentException("There is no ride to pay");
		if(ride.getPayment() != null && ride.getPayment().isDone()) return false;
		if(ride.getStatus() != RideStatus.COMPLETED) {
			System.out.println("⚠️ Cannot pay before completing the ride.");
            return false;
		}
		System.out.println("Please pay via " + paymentType + "  Rs." + ride.getFare() + " to Driver " + ride.getDriver().getName() + " for the ride");
		Payment payment = new Payment(paymentType, ride.getFare());
		ride.setPayment(payment);
		
		return true;
	}
}
