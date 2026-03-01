import enums.VehicleType;
import model.*;
import service.ParkingLotService;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        ParkingLotService parkingLot = new ParkingLotService();

        // Setup one floor with spots
        ParkingFloor floor1 = new ParkingFloor(1);
        floor1.addSpot(new ParkingSpot(1, VehicleType.CAR));
        floor1.addSpot(new ParkingSpot(2, VehicleType.BIKE));
        floor1.addSpot(new ParkingSpot(3, VehicleType.TRUCK));
        parkingLot.addFloor(floor1);

        boolean running = true;

        while (running) {
            System.out.println("\n===== PARKING LOT MENU =====");
            System.out.println("1. Park Vehicle");
            System.out.println("2. Unpark Vehicle");
            System.out.println("3. Exit");
            System.out.print("Choose option: ");

            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {

                case 1:
                    System.out.print("Enter License Plate: ");
                    String plate = sc.nextLine();

                    System.out.println("Select Vehicle Type:");
                    System.out.println("1. CAR");
                    System.out.println("2. BIKE");
                    System.out.println("3. TRUCK");

                    int typeChoice = sc.nextInt();
                    sc.nextLine();

                    Vehicle vehicle = null;

                    if (typeChoice == 1) {
                        vehicle = new Car(plate);
                    } else if (typeChoice == 2) {
                        vehicle = new Bike(plate);
                    } else if (typeChoice == 3) {
                        vehicle = new Truck(plate);
                    } else {
                        System.out.println("Invalid vehicle type.");
                        break;
                    }

                    parkingLot.parkVehicle(vehicle);
                    break;

                case 2:
                    System.out.print("Enter License Plate to remove: ");
                    String removePlate = sc.nextLine();

                    // Temporary vehicle object for search
                    Vehicle tempVehicle = new Car(removePlate);
                    parkingLot.unparkVehicle(tempVehicle);
                    break;

                case 3:
                    running = false;
                    System.out.println("Exiting system...");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }
        }

        sc.close();
    }
}