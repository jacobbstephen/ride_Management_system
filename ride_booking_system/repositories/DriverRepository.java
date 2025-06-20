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

public class DriverRepository implements DriverCSVHeaders{
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

	public void save() throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
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
			if (tokens.length < 5) continue; 
			Vehicle vechicle = new Vehicle(tokens[VECHILE_TYPE],tokens[VECHILE_NUMBER]);
			Driver driver = new Driver(tokens[NAME], tokens[PHONE_NUMBER], vechicle, Boolean.parseBoolean(tokens[AVAILABLE])); // name, phone, vehicle, available
			drivers.add(driver);
			}
		} catch(IOException e){
			e.printStackTrace();
		}
	  
    }
}
