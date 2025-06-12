package ride_booking_system.entity;

import ride_booking_system.service.RideStatus;

public class Ride {
	private String pickUpLocation;
	private String dropOffLocation;
	private double distance;
	private double fare;
	private Driver driver;
	private User user;
	private RideStatus status;
    private int rating;
    private boolean paymentDone = false;
    private String methodOfPayment;
	
	public Ride(String pickUpLocation, String dropOffLocation, double distance, double fare, Driver driver, User user) {
		this.pickUpLocation = pickUpLocation;
		this.dropOffLocation = dropOffLocation;
		this.distance = distance;
		this.fare = fare;
		this.driver = driver;
		this.user = user;
		this.status = RideStatus.BOOKED;
	}
	
	public boolean isPaymentDone() {
		return paymentDone;
	}
	
	public String getPickUpLocation() {
		return pickUpLocation;
	}
	public String getDropOffLocation() {
		return dropOffLocation;
	}
	public double getDistance() {
		return distance;
	}
	public double getFare() {
		return fare;
	}
	public Driver getDriver() {
		return driver;
	}
	public User getUser() {
		return user;
	}
	
	public RideStatus getStatus() {
		return status;
	}

	public void setStatus(RideStatus status) {
		this.status = status;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}
	

	public void setPaymentDone(boolean paymentDone) {
		this.paymentDone = paymentDone;
	}

	public void setMethodOfPayment(String methodOfPayment) {
		this.methodOfPayment = methodOfPayment;
	}

	public void printRideSummary() {
        System.out.println("==== Ride Summary ====");
        System.out.println("User: " + user.getName() + " (" + user.getPhoneNumber() + ")");
        System.out.println("From: " + pickUpLocation);
        System.out.println("To: " + dropOffLocation);
        System.out.println("Distance: " + distance + " km");
        System.out.println("Fare: ₹" + fare);
        System.out.println("Driver: " + driver.getName() + " (" + driver.getVechileType() + ")");
        System.out.println("Status: " + status);
        System.out.println("Rating: " + (rating == 0 ? "Not Rated" : rating + " ★"));
        System.out.println("Payment: " + (paymentDone == true ? "Payment done" : "Payment Not done"));
        System.out.println("Method of Payment: " + methodOfPayment);
        System.out.println("======================");
    }
	
}
