package ride_booking_system.entity;

import ride_booking_system.service.RideStatus;

public class Ride {
	private int id;
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
	private Payment payment;
	private String rideType;
	
	public Ride(String pickUpLocation, String dropOffLocation, User user, String rideType) {
		this.pickUpLocation = pickUpLocation;
		this.dropOffLocation = dropOffLocation;
		this.user = user;
		this.rideType = rideType;
	}
	
	public String getRideType() {
		return rideType;
	}

	public void setRideType(String rideType) {
		this.rideType = rideType;
	}

	public Payment getPayment() {
		return payment;
	}
	
	public boolean isPaymentDone() {
		return paymentDone;
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


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Ride [id=" + id + ", pickUpLocation=" + pickUpLocation + ", dropOffLocation=" + dropOffLocation
				+ ", distance=" + distance + ", fare=" + fare + ", driver=" + driver.getName() + ", user=" + user.getName() + ", status="
				+ status + ", rating=" + rating + ", paymentDone=" + paymentDone + ", methodOfPayment="
				+ methodOfPayment + ", payment=" + payment + ", rideType=" + rideType + "]";
	}

}