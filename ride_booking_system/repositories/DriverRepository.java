package ride_booking_system.repositories;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import ride_booking_system.entity.Driver;
import ride_booking_system.entity.Vehicle;
import ride_booking_system.exceptions.MaxLimitExceedException;

public class DriverRepository {
	private ArrayList<Driver> drivers = new ArrayList<>();
	private final String path="ride_booking_system/data/drivers.csv";
	public DriverRepository(){
		loadDriversFromCSV(path);
	}
	
	public ArrayList<Driver> getDrivers() {
		return drivers;
	}
	public int getDriverCount() {
		return drivers.size();
	}
	
	public void addDrivers(Driver driver) throws MaxLimitExceedException{
		drivers.add(driver);
	}

	public void writeDriversToCSV(String filePath) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Driver driver : drivers) {
                writer.write(driver.toCSV());
                writer.newLine();
			}
		}
	}
	public void loadDriversFromCSV(String filePath) {
		drivers.clear();
		try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
			String line;
			while ((line = reader.readLine()) != null) {
			String[] tokens = line.split(",");
			if (tokens.length < 5) continue; // adjust based on your fields
			Vehicle vechicle = new Vehicle(tokens[2],tokens[3]);
			Driver driver = new Driver(tokens[0], tokens[1], vechicle, Boolean.parseBoolean(tokens[4])); // name, phone, vehicle, available
			drivers.add(driver);
			}
		} catch(IOException e){
			e.printStackTrace();
		}
	  
    }
}
