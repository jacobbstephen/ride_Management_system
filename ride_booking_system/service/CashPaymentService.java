package ride_booking_system.service;

import ride_booking_system.entity.Ride;
import ride_booking_system.exceptions.PaymentException;

public class CashPaymentService implements PayementService {

	@Override
	public boolean makePayment(Ride ride) throws PaymentException {
		if(ride == null) throw new PaymentException("There is no ride to pay");
		if(ride.isPaymentDone()) return false;
		if(ride.getStatus() != RideStatus.COMPLETED) {
			System.out.println("⚠️ Cannot pay before completing the ride.");
            return false;
		}
		System.out.println("Please pay in cash  Rs." + ride.getFare() + " to " + ride.getDriver().getName() + " for the ride");
		ride.setMethodOfPayment("Cash");
		ride.setPaymentDone(true);
		return true;
	}
}
