package model;

import enums.VehicleType;
import java.time.LocalDateTime;

public class ParkingSpot {

    private final int spotId;
    private final VehicleType allowedType;
    private boolean isOccupied;
    private Vehicle parkedVehicle;
    private LocalDateTime entryTime;

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
        this.entryTime = LocalDateTime.now();   // ⏱ Entry Time
        this.isOccupied = true;
    }

    public void removeVehicle() {
        this.parkedVehicle = null;
        this.entryTime = null;
        this.isOccupied = false;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public int getSpotId() {
        return spotId;
    }

    public Vehicle getParkedVehicle() {
        return parkedVehicle;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }
}