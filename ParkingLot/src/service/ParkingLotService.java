package service;

import model.ParkingFloor;
import model.ParkingSpot;
import model.Vehicle;

import java.util.ArrayList;
import java.util.List;
import model.Ticket;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import java.time.Duration;
import java.time.LocalDateTime;

public class ParkingLotService {

    private Map<String, Ticket> activeTickets = new HashMap<>();
    private final List<ParkingFloor> floors;


    public ParkingLotService() {
        this.floors = new ArrayList<>();
    }

    public void addFloor(ParkingFloor floor) {
        floors.add(floor);
    }

    //Park Vehicle
    public String parkVehicle(Vehicle vehicle) {

        if (isVehicleAlreadyParked(vehicle.getLicensePlate())) {
            System.out.println("Vehicle already parked in the parking lot!");
            return null;
        }

        for (ParkingFloor floor : floors) {

            ParkingSpot spot = floor.findAvailableSpot(vehicle);

            if (spot != null) {

                spot.parkVehicle(vehicle);

                String ticketId = UUID.randomUUID().toString().substring(0, 6);

                Ticket ticket = new Ticket(ticketId, vehicle);

                activeTickets.put(ticketId, ticket);

                System.out.println("\nVehicle Parked Successfully!");
                System.out.println("Ticket ID: " + ticketId);
                System.out.println("Floor: " + floor.getFloorNumber());
                System.out.println("Spot: " + spot.getSpotId());

                return ticketId;
            }
        }

        System.out.println("No available spot.");
        return null;
    }

    //Unpark Vehicle
    public void unparkVehicle(String ticketId) {

        Ticket ticket = activeTickets.get(ticketId);

        if (ticket == null) {
            System.out.println("Invalid Ticket ID.");
            return;
        }

        Vehicle vehicle = ticket.getVehicle();

        for (ParkingFloor floor : floors) {
            for (ParkingSpot spot : floor.getSpots()) {

                if (spot.isOccupied() && spot.getParkedVehicle().getLicensePlate().equals(vehicle.getLicensePlate())) {

                    LocalDateTime entry = ticket.getEntryTime();
                    LocalDateTime exit = LocalDateTime.now();

                    long hours = Duration.between(entry, exit).toHours();
                    if (hours == 0) hours = 1;

                    long fee = hours * 50;

                    System.out.println("\n===== PARKING BILL =====");
                    System.out.println("Ticket ID: " + ticketId);
                    System.out.println("Vehicle: " + vehicle.getLicensePlate());
                    System.out.println("Entry Time: " + entry);
                    System.out.println("Exit Time: " + exit);
                    System.out.println("Total Hours: " + hours);
                    System.out.println("Total Fee: ₹" + fee);

                    spot.removeVehicle();
                    activeTickets.remove(ticketId);

                    return;
                }
            }
        }
    }

    //Display Parking Status
    public void displayParkingStatus() {
        System.out.println("\n===== PARKING LOT STATUS =====");
        for (ParkingFloor floor : floors) {
            floor.displaySpots();
        }
    }

    //Check
    private boolean isVehicleAlreadyParked(String licensePlate) {

        for (Ticket ticket : activeTickets.values()) {
            if (ticket.getVehicle().getLicensePlate().equalsIgnoreCase(licensePlate)) {
                return true;
            }
        }

        return false;
    }
}