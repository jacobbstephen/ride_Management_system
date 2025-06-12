package ride_booking_system.service;

import ride_booking_system.entity.Driver;
import ride_booking_system.exceptions.MaxLimitExceedException;

public class DriverRepository {
	private static int MAX_DRIVERS = 20;
	private static Driver drivers[] = new Driver[MAX_DRIVERS];
	private static int driverCount = 0;
	
	public static Driver[] getDrivers() {
		return drivers;
	}
	public static int getDriverCount() {
		return driverCount;
	}
	
	public static void addDrivers(Driver driver) throws MaxLimitExceedException{
		if(driverCount > MAX_DRIVERS) throw new MaxLimitExceedException("Sorry. We cannot add any more drivers.");
		drivers[driverCount++] = driver;
	}
}
