package ride_booking_system.entity;



public class Driver {
    private String name;
    private String phoneNumber;
    private Vehicle vehicleType;  
    private boolean available;

    public Driver(String name, String phoneNumber, Vehicle vehicleType, boolean available) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.vehicleType = vehicleType;
        this.available = available;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Vehicle getVehicleType() {
        return vehicleType;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return name + " : " + phoneNumber + " : " + vehicleType.getType() + " : " + available;
    }
}
