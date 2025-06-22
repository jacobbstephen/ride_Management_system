package ride_booking_system.service;


import ride_booking_system.entity.Driver;
import ride_booking_system.entity.Ride;
import ride_booking_system.exceptions.RideNotFoundException;
import ride_booking_system.repositories.DriverRepository;
import ride_booking_system.repositories.RideRepository;
import ride_booking_system.repositories.UserRepository;

public class CompleteRideService {
    RideRepository rideRepository;
    DriverRepository driverRepository;
	UserRepository userRepository;

    public CompleteRideService(){
		driverRepository = new DriverRepository();
		rideRepository = new RideRepository();
		userRepository = new UserRepository();
	}

	public void completeRide(int ride_id, int user_id) throws RideNotFoundException {
		Ride ride = rideRepository.findById(ride_id);
		if (ride == null)
			throw new RideNotFoundException("The requested ride cannot be found");
		if(!verifyUser(ride.getUser_id(), user_id)){
			System.out.println("User does not have the right to cancel this ride");
			return;
		}
		if (checkRideIsBooked(ride.getStatus())) {
			ride.setStatus(RideStatus.COMPLETED);
			int driver_id = ride.getDriver_id();
			Driver assignedDriver = driverRepository.findById(driver_id);
			assignedDriver.setAvailable(true);
			System.out.println("Ride marked as completed.");
		}else{
			System.out.println("The ride is already completed or cancelled");
		}
	}

	private boolean verifyUser(int id, int user_id){
		return (id == user_id);
	}

	private boolean checkRideIsBooked(RideStatus ridestatus){
		return (ridestatus == RideStatus.BOOKED);
	}
}


