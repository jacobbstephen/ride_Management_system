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
   
    private Payment payment;
	

	public Ride(String pickUpLocation, String dropOffLocation, double distance, double fare, Driver driver, User user) {
		this.pickUpLocation = pickUpLocation;
		this.dropOffLocation = dropOffLocation;
		this.distance = distance;
		this.fare = fare;
		this.driver = driver;
		this.user = user;
		this.status = RideStatus.BOOKED;
	}
	public Payment getPayment() {
		return payment;
	}
	
	public void setPayment(Payment payment) {
		this.payment = payment;
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
	


	public void printRideSummary() {
	    System.out.println("==== Ride Summary ====");
	    System.out.println("User: " + user.getName() + " (" + user.getPhoneNumber() + ")");
	    System.out.println("From: " + pickUpLocation);
	    System.out.println("To: " + dropOffLocation);
	    System.out.println("Distance: " + distance + " km");
	    System.out.println("Fare: ₹" + fare);
	    System.out.println("Driver: " + driver.getName() + " (" + driver.getVehicleType().getType() + ")");
	    System.out.println("Status: " + status);
	    System.out.println("Rating: " + (rating == 0 ? "Not Rated" : rating + " ★"));

	    if (payment != null) {
	        System.out.println("Payment: " + (payment.isDone() ? "Payment done" : "Payment Not done"));
	        System.out.println("Method of Payment: " + payment.getPaymentMethod());
	    } else {
	        System.out.println("Payment: Not done");
	        System.out.println("Method of Payment: N/A");
	    }

	    System.out.println("======================");
	}

	
}
