package ride_booking_system.service;

import ride_booking_system.entity.Ride;

public class RideSummaryService {
	public void printRideSummary(Ride ride) {
		System.out.println();
		System.out.println("====== Ride Summary ====");
		System.out.println("User: " + ride.getUser().getName() + " (" + ride.getUser().getPhoneNumber() + ")");
		System.out.println("From: " + ride.getPickUpLocation());
		System.out.println("To: " + ride.getDropOffLocation());
		System.out.println("Distance: " + ride.getDistance() + " km");
		System.out.println("Fare: Rs " + ride.getFare());
		System.out.println(
				"Driver: " + ride.getDriver().getName() + " (" + ride.getDriver().getVechileType().getName() + ")");
		System.out.println("Status: " + ride.getStatus());
		System.out.println("Rating: " + (ride.getRating() == 0 ? "Not Rated" : ride.getRating()));
		System.out.println("Payment: " + (ride.getPaymentDone() ? "Payment done" : "Payment Not done"));
		System.out.println("Method of Payment: " + ride.getMethodOfPayment());
		System.out.println("======================");
	}
}
