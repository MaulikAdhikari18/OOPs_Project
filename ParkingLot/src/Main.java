import enums.VehicleType;
import model.*;
import service.ParkingLotService;

public class Main {

    public static void main(String[] args) {

        // Create Parking Lot Service
        ParkingLotService parkingLot = new ParkingLotService();

        // Create Floor 1
        ParkingFloor floor1 = new ParkingFloor(1);

        // Add Spots to Floor 1
        floor1.addSpot(new ParkingSpot(1, VehicleType.CAR));
        floor1.addSpot(new ParkingSpot(2, VehicleType.BIKE));
        floor1.addSpot(new ParkingSpot(3, VehicleType.TRUCK));

        // Add Floor to Parking Lot
        parkingLot.addFloor(floor1);

        // Create Vehicles
        Vehicle car = new Car("CAR123");
        Vehicle bike = new Bike("BIKE123");
        Vehicle truck = new Truck("TRUCK123");

        // Park Vehicles
        parkingLot.parkVehicle(car);
        parkingLot.parkVehicle(bike);
        parkingLot.parkVehicle(truck);

        // Try Parking Extra Car (Should Fail if no spot)
        Vehicle car2 = new Car("CAR999");
        parkingLot.parkVehicle(car2);

        // Unpark Car
        parkingLot.unparkVehicle(car);

        // Try Parking Again
        parkingLot.parkVehicle(car2);
    }
}