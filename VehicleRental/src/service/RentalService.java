package service;

import database.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RentalService {

    // Rent Vehicle
    public void rentVehicle(int vehicleId, int customerId, int days, double pricePerDay) {

        String insertRental = "INSERT INTO rentals(vehicle_id, customer_id, days, total_cost) VALUES(?,?,?,?)";
        String updateVehicle = "UPDATE vehicles SET available=false WHERE vehicle_id=?";

        double totalCost = days * pricePerDay;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement rentalPs = conn.prepareStatement(insertRental);
             PreparedStatement vehiclePs = conn.prepareStatement(updateVehicle)) {

            // Insert rental record
            rentalPs.setInt(1, vehicleId);
            rentalPs.setInt(2, customerId);
            rentalPs.setInt(3, days);
            rentalPs.setDouble(4, totalCost);

            rentalPs.executeUpdate();

            // Update vehicle availability
            vehiclePs.setInt(1, vehicleId);
            vehiclePs.executeUpdate();

            System.out.println("Vehicle rented successfully!");
            System.out.println("Total Cost: " + totalCost);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

        // Return Vehicle
    public void returnVehicle(int vehicleId) {

        String query = "UPDATE vehicles SET available=true WHERE vehicle_id=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, vehicleId);
            ps.executeUpdate();

            System.out.println("Vehicle returned successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}