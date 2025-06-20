package ride_booking_system.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import ride_booking_system.entity.Driver;
import ride_booking_system.entity.Ride;
import ride_booking_system.entity.User;
import ride_booking_system.exceptions.DriverNotFoundException;
import ride_booking_system.exceptions.RideException;
import ride_booking_system.exceptions.RideNotFoundException;
import ride_booking_system.repositories.DriverRepository;
import ride_booking_system.repositories.RideRepository;

public class BookRideService implements RideService {

	DriverRepository driverRepository = new DriverRepository();
	RideRepository rideRepository = new RideRepository();

	public Ride bookRide(User user, String rideType, String pickup, String drop) throws DriverNotFoundException {
		Driver assignedDriver = findDriver(rideType);
		if (assignedDriver == null)
			throw new DriverNotFoundException(
					"All Drivers are busy. Couldn't assign driver for your ride. User: " + user.getName());

		assignedDriver.setAvailable(false);
		double distance = new LocationService().getDistanceFromGoogleMap(pickup, drop);
		double fare = calculateFare(distance);
		Ride ride = new Ride(pickup, drop, distance, fare, assignedDriver, user);
		rideRepository.addRide(ride);
		return ride;
	}

	private Driver findDriver(String rideType) {
		ArrayList<Driver> drivers = driverRepository.getDrivers();
		int driverCount = driverRepository.getDriverCount();
		Driver assignedDriver = null;
		for (int i = 0; i < driverCount; i++) {
			if (drivers.get(i).isAvailable() && drivers.get(i).getVechileType().getName().equals(rideType)) {
				assignedDriver = drivers.get(i);
				assignedDriver.setAvailable(false);
				break;

			}
		}

		return assignedDriver;
	}

	public void printRideSummary(Ride ride) {
		System.out.println("====== Ride Summary ====");
		System.out.println("User: " + ride.getUser().getName() + " (" + ride.getUser().getPhoneNumber() + ")");
		System.out.println("From: " + ride.getPickUpLocation());
		System.out.println("To: " + ride.getDropOffLocation());
		System.out.println("Distance: " + ride.getDistance() + " km");
		System.out.println("Fare: ₹" + ride.getFare());
		System.out.println(
				"Driver: " + ride.getDriver().getName() + " (" + ride.getDriver().getVechileType().getName() + ")");
		System.out.println("Status: " + ride.getStatus());
		System.out.println("Rating: " + (ride.getRating() == 0 ? "Not Rated" : ride.getRating() + " ★"));
		System.out.println("Payment: " + (ride.getPaymentDone() ? "Payment done" : "Payment Not done"));
		System.out.println("Method of Payment: " + ride.getMethodOfPayment());
		System.out.println("======================");
	}

	@Override
	public boolean cancelRide(Ride ride) throws RideNotFoundException {
		if (ride == null)
			throw new RideNotFoundException("The requested ride cannot be found");
		if (ride.getStatus() == RideStatus.BOOKED) {
			ride.setStatus(RideStatus.CANCELLED);
			ride.getDriver().setAvailable(true);
			System.out.println("Your ride is cancelled");
			rideRepository.save();
			try {
				driverRepository.save();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return true;
		}
		System.out.println("Cannot cancel. Ride is either already cancelled or completed.");
		return false;
	}

	@Override
	public void rateRide(Ride ride, int rating) throws RideNotFoundException {
		if (ride == null)
			throw new RideNotFoundException("The requested ride cannot be found");
		if (ride.getStatus() == RideStatus.COMPLETED) {
			if (rating >= 1 && rating <= 5) {
				ride.setRating(rating);
				System.out.println("⭐ Thank you for rating the ride " + rating + " stars.");
			} else {
				System.out.println("❌ Rating must be between 1 and 5.");
			}
		} else {
			System.out.println("⚠️ Cannot rate. Ride is not completed.");
		}
	}

	@Override
	public void completeRide(Ride ride) throws RideNotFoundException {
		if (ride == null)
			throw new RideNotFoundException("The requested ride cannot be found");
		if (ride.getStatus() == RideStatus.BOOKED) {
			ride.setStatus(RideStatus.COMPLETED);
			ride.getDriver().setAvailable(true);
			System.out.println("Ride marked as completed.");

			try {
				Scanner sc = new Scanner(System.in);
				System.out.print("Do You want to rate the ride: yes or no? :");
				String wantToRate = sc.next();
				if (wantToRate.equalsIgnoreCase("yes")) {
					System.out.print("On a Scale of 5 [ 1- 5], how much would u rate: ");
					int rating = sc.nextInt();
					rateRide(ride, rating);
				}
				System.out.print("How Do You Like to pay? by Cash or UPI: ");
				String paymentMethod = sc.next();
				new PaymentProcess().makePayment(ride, paymentMethod);
				printRideSummary(ride);
				rideRepository.save();
				try {
					driverRepository.save();
				} catch (IOException e) {
					e.printStackTrace();
				}

			} catch (RideException e) {
				System.err.println(e.getMessage());
			}

		}
	}

	@Override
	public double calculateFare(double distance) {
		return BASE_FARE + (PRICE_PER_KM * distance);
	}
}
