package gui;

import javax.swing.*;
import service.RentalService;

public class RentVehicleForm {

    public RentVehicleForm() {

        JFrame frame = new JFrame("Rent Vehicle");

        frame.setSize(400, 400);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JLabel customerLabel = new JLabel("Customer ID:");
        customerLabel.setBounds(50, 50, 100, 25);

        JTextField customerField = new JTextField();
        customerField.setBounds(160, 50, 150, 25);

        JLabel vehicleLabel = new JLabel("Vehicle ID:");
        vehicleLabel.setBounds(50, 100, 100, 25);

        JTextField vehicleField = new JTextField();
        vehicleField.setBounds(160, 100, 150, 25);

        JLabel daysLabel = new JLabel("Days:");
        daysLabel.setBounds(50, 150, 100, 25);

        JTextField daysField = new JTextField();
        daysField.setBounds(160, 150, 150, 25);

        JButton rentButton = new JButton("Rent");
        rentButton.setBounds(130, 220, 120, 30);

        rentButton.addActionListener(e -> {
            try {
                int customerId = Integer.parseInt(customerField.getText());
                int vehicleId = Integer.parseInt(vehicleField.getText());
                int days = Integer.parseInt(daysField.getText());

                double pricePerDay = 1000;

                RentalService service = new RentalService();
                service.rentVehicle(vehicleId, customerId, days, pricePerDay);

                JOptionPane.showMessageDialog(frame, "Vehicle Rented Successfully!");

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Invalid Input!");
            }
        });

        frame.add(customerLabel);
        frame.add(customerField);
        frame.add(vehicleLabel);
        frame.add(vehicleField);
        frame.add(daysLabel);
        frame.add(daysField);
        frame.add(rentButton);

        frame.setVisible(true);
    }
}