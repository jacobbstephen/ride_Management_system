package ride_booking_system.entity;

public class Driver {
	private int id;
	private String name;
	private String phoneNumber;
	private Vehicle vechileType;
	private boolean available;
	
	public Driver(int id, String name, String phoneNumber, Vehicle vechileType, boolean available ) {
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.vechileType = vechileType;
		this.available = available;
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setVechileType(Vehicle vechileType) {
		this.vechileType = vechileType;
	}

	
	public Vehicle getVechileType() {
		return vechileType;
	}
	public boolean isAvailable() {
		return available;
	}
	
	public void setAvailable(boolean available) {
		this.available = available;
	}
	@Override
	public  String toString() {
		return id + " : " + name + " : " + phoneNumber + " : " + vechileType.getName() + " : " + vechileType.getVehicle_number() + " : " + available;
	}	
}
