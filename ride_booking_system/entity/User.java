package ride_booking_system.entity;

public class User {
	private int id;
	private String name;
	private String phoneNumber;
	int count = 1;
	 
	public User(String name, String phoneNumber) {
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.id = count++;
	}
	public String getName() {
		return name;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
}