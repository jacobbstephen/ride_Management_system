package ride_booking_system.service;

public class LocationService {
	public double getDistanceFromGoogleMap(String source, String destination) {
		if(source.equalsIgnoreCase("A") && destination.equalsIgnoreCase("B") || source.equalsIgnoreCase("B") && destination.equalsIgnoreCase("A") ) 
			return 10.0;
		if(source.equalsIgnoreCase("B") && destination.equalsIgnoreCase("C") || source.equalsIgnoreCase("C") && destination.equalsIgnoreCase("B")) 
			return 14.5;
		if(source.equalsIgnoreCase("A") && destination.equalsIgnoreCase("C") || source.equalsIgnoreCase("C") && destination.equalsIgnoreCase("A"))
			return 20.0;
		return 8.0;
	}
}
