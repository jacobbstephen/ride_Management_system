package ride_booking_system.service;

import ride_booking_system.entity.Ride;
import ride_booking_system.exceptions.PaymentException;

public interface PaymentService {
	boolean makePayment(Ride ride, String paymentType) throws PaymentException;
}
