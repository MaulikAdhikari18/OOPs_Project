package model;

import enums.VehicleType;
import java.util.ArrayList;
import java.util.List;

public class ParkingFloor {

    private final int floorNumber;
    private final List<ParkingSpot> spots;

    public ParkingFloor(int floorNumber) {
        this.floorNumber = floorNumber;
        this.spots = new ArrayList<>();
    }

    public void addSpot(ParkingSpot spot) {
        spots.add(spot);
    }

    public List<ParkingSpot> getSpots() {
        return spots;
    }

    public boolean removeVehicle(String licensePlate) {
        for (ParkingSpot spot : spots) {
            if (spot.isOccupied() &&
                spot.getParkedVehicle().getLicensePlate().equals(licensePlate)) {
                spot.removeVehicle();
                return true;
            }
        }
        return false;
    }

    public ParkingSpot findAvailableSpot(Vehicle vehicle) {
        for (ParkingSpot spot : spots) {
            if (spot.canPark(vehicle)) {
                return spot;
            }
        }
        return null; // No available spot
    }

    public int getFloorNumber() {
        return floorNumber;
    }
}