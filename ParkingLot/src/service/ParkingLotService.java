package service;

import model.ParkingFloor;
import model.ParkingSpot;
import model.Vehicle;

import java.util.ArrayList;
import java.util.List;

import java.time.Duration;
import java.time.LocalDateTime;

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

                if (spot.isOccupied() &&spot.getParkedVehicle().getLicensePlate().equals(vehicle.getLicensePlate())) {
                    LocalDateTime entryTime = spot.getEntryTime();
                    LocalDateTime exitTime = LocalDateTime.now();

                    long hours = Duration.between(entryTime, exitTime).toHours();
                    if (hours == 0) {
                        hours = 1; // minimum charge
                    }

                    long fee = hours * 50;

                    System.out.println("\n===== PARKING BILL =====");
                    System.out.println("Vehicle: " + vehicle.getLicensePlate());
                    System.out.println("Entry Time: " + entryTime);
                    System.out.println("Exit Time: " + exitTime);
                    System.out.println("Total Hours: " + hours);
                    System.out.println("Total Fee: ₹" + fee);

                    spot.removeVehicle();

                    return true;
                }
            }
        }

        System.out.println("Vehicle not found.");
        return false;
    }
}