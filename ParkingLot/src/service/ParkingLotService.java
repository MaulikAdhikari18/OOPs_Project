package service;

import model.ParkingFloor;
import model.ParkingSpot;
import model.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class ParkingLotService {

    private final List<ParkingFloor> floors;

    public ParkingLotService() {
        this.floors = new ArrayList<>();
    }

    public void addFloor(ParkingFloor floor) {
        floors.add(floor);
    }

    public boolean parkVehicle(Vehicle vehicle) {

        for (ParkingFloor floor : floors) {

            ParkingSpot spot = floor.findAvailableSpot(vehicle);

            if (spot != null) {
                spot.parkVehicle(vehicle);
                System.out.println("Vehicle parked at Floor "
                        + floor.getFloorNumber()
                        + ", Spot "
                        + spot.getSpotId());
                return true;
            }
        }

        System.out.println("No available spot for vehicle: "
                + vehicle.getLicensePlate());
        return false;
    }

    public boolean unparkVehicle(Vehicle vehicle) {

        for (ParkingFloor floor : floors) {
            for (ParkingSpot spot : floor.getSpots()) {

                if (spot.isOccupied() &&
                        spot.getParkedVehicle().getLicensePlate()
                                .equals(vehicle.getLicensePlate())) {

                    spot.removeVehicle();
                    System.out.println("Vehicle removed from Floor "
                            + floor.getFloorNumber()
                            + ", Spot "
                            + spot.getSpotId());
                    return true;
                }
            }
        }

        System.out.println("Vehicle not found: "
                + vehicle.getLicensePlate());
        return false;
    }
}