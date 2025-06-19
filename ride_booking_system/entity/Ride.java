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
	public Boolean getPaymentDone() {
		return paymentDone;
	}
	public void setMethodOfPayment(String methodOfPayment) {
		this.methodOfPayment = methodOfPayment;
	}

	public String getMethodOfPayment() {
		return methodOfPayment;
	}

	public void setPickUpLocation(String pickUpLocation) {
		this.pickUpLocation = pickUpLocation;
	}

	public void setDropOffLocation(String dropOffLocation) {
		this.dropOffLocation = dropOffLocation;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public void setFare(double fare) {
		this.fare = fare;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}

	public void setUser(User user) {
		this.user = user;
	}



	
}
