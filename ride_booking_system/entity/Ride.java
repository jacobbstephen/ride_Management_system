package ride_booking_system.entity;

import ride_booking_system.service.RideStatus;

public class Ride {
	private int id;
	private String pickUpLocation;
	private String dropOffLocation;
	private double distance;
	private double fare;
	private RideStatus status;
    private int rating;
    private boolean paymentDone = false;
    private String methodOfPayment;
	private String rideType;
	private int user_id;
	private int driver_id;
	private int paymentId;
	
	public Ride(String pickUpLocation, String dropOffLocation, int user_id, String rideType) {
		this.pickUpLocation = pickUpLocation;
		this.dropOffLocation = dropOffLocation;
		this.rideType = rideType;
		this.user_id = user_id;
	}
	

	public String getRideType() {
		return rideType;
	}

	public void setRideType(String rideType) {
		this.rideType = rideType;
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


	
	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getDriver_id() {
		return driver_id;
	}

	public void setDriver_id(int driver_id) {
		this.driver_id = driver_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}



	public int getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}

	// @Override
	// public String toString() {
	// 	return "Ride [id=" + id + ", pickUpLocation=" + pickUpLocation + ", dropOffLocation=" + dropOffLocation
	// 			+ ", distance=" + distance + ", fare=" + fare + ", driver=" + driver.getName() + ", user=" + user.getName() + ", status="
	// 			+ status + ", rating=" + rating + ", paymentDone=" + paymentDone + ", methodOfPayment="
	// 			+ methodOfPayment + ", payment=" + payment + ", rideType=" + rideType + "]";
	// }

}