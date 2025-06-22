package ride_booking_system.repositories;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ride_booking_system.entity.Driver;
import ride_booking_system.entity.Vehicle;
import ride_booking_system.repositories.headers.DriverCSVHeaders;

public class DriverRepository implements DriverCSVHeaders, RepositoryInterface<Driver>{
	private List<Driver> drivers;
	private final String path = "ride_booking_system/data/drivers.csv";

	public DriverRepository(){
		drivers = new ArrayList<>();
		load();
	}

    public void add(Driver driver){
		drivers.add(driver);
	}

    public Driver findById(int id){
		for(Driver driver: drivers){
			if(driver.getId() == id) return driver;
		}
		return null;
	}

    public List<Driver> getAll(){
		return drivers;
	}

	public int size() {
		return drivers.size();
	}
	
	public void load() {
		drivers.clear();
		try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
			String line;
			reader.readLine();
			while ((line = reader.readLine()) != null) {
				String[] fields = line.split(",");
				if (fields.length < 6) continue; 
				Vehicle vechicle = new Vehicle(fields[VECHILE_TYPE],fields[VECHILE_NUMBER]);
				int id = Integer.parseInt(fields[ID]);
				Driver driver = new Driver(id, fields[NAME], fields[PHONE_NUMBER], vechicle, Boolean.parseBoolean(fields[AVAILABLE])); // name, phone, vehicle, available
				add(driver);
			}
		} catch(IOException e){
			e.printStackTrace();
		}
    }

	public void save()  throws IOException{
		try(FileWriter writer = new FileWriter(path);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
        ) {
			bufferedWriter.write("Id,Name,PhoneNumber,VehicleType,VehicleNumber,Available");
			bufferedWriter.newLine();
			for (Driver driver : drivers) {
				String line = makeString(driver);
				bufferedWriter.write(line);
				bufferedWriter.newLine();
			}
		} catch (IOException e) {
			System.err.println("Error writing to CSV: " + e.getMessage());
		} 
	}


	private String makeString(Driver driver) {
		return String.join(",",
			String.valueOf(driver.getId()),
			driver.getName(),
			driver.getPhoneNumber(),
			driver.getVechileType().getName(),
			driver.getVechileType().getVehicle_number(),
			String.valueOf(driver.isAvailable())
		);
	}

}
