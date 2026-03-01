package model;

import enums.VehicleType;

public class ParkingSpot {

    private final int spotId;
    private final VehicleType allowedType;
    private boolean isOccupied;
    private Vehicle parkedVehicle;

    public ParkingSpot(int spotId, VehicleType allowedType) {
        this.spotId = spotId;
        this.allowedType = allowedType;
        this.isOccupied = false;
    }

    public boolean canPark(Vehicle vehicle) {
        return !isOccupied && vehicle.getType() == allowedType;
    }

    public void parkVehicle(Vehicle vehicle) {
        if (!canPark(vehicle)) {
            throw new IllegalStateException("Cannot park vehicle here");
        }
        this.parkedVehicle = vehicle;
        this.isOccupied = true;
    }

    public Vehicle getParkedVehicle() {
        return parkedVehicle;
    }

    public void removeVehicle() {
        this.parkedVehicle = null;
        this.isOccupied = false;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public int getSpotId() {
        return spotId;
    }
}