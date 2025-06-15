package ride_booking_system.entity;

public class User {
	private String name;
	private String phoneNumber;
	 
	public User(String name, String phoneNumber) {
		this.name = name;
		this.phoneNumber = phoneNumber;
	}
	public String getName() {
		return name;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}

}
