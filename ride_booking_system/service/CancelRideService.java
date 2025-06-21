package ride_booking_system.service;

import java.io.IOException;

import ride_booking_system.entity.Ride;
import ride_booking_system.exceptions.RideNotFoundException;
import ride_booking_system.repositories.DriverRepository;
import ride_booking_system.repositories.RideRepository;


public class CancelRideService {
    RideRepository rideRepository = new RideRepository();
    DriverRepository driverRepository = new DriverRepository();

    public CancelRideService(){
		driverRepository = new DriverRepository();
		rideRepository = new RideRepository();
	}
    public boolean cancelRide(Ride ride) throws RideNotFoundException {
		if (ride == null)
			throw new RideNotFoundException("The requested ride cannot be found");
		if (ride.getStatus() == RideStatus.BOOKED) {
			ride.setStatus(RideStatus.CANCELLED);
			ride.getDriver().setAvailable(true);
			System.out.println("Your ride is cancelled");
			try {
				rideRepository.save();
				driverRepository.save();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return true;
		}
		System.out.println("Cannot cancel. Ride is either already cancelled or completed.");
		return false;
	}
}