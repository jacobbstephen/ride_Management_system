package ride_booking_system.entity;

public class Vehicle  {
      private String name;
      private String vehicle_number;
      
      public Vehicle(String name, String vehicle_number) {
            this.name = name;
            this.vehicle_number = vehicle_number;
      }
      public String getName(){
            return this.name;
      }

      public String getVehicle_number() {
            return vehicle_number;
      }

      public void setName(String name) {
            this.name = name;
      }

      public void setVehicle_number(String vehicle_number) {
            this.vehicle_number = vehicle_number;
      };
      
}
