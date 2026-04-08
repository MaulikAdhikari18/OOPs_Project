package gui;

import javax.swing.*;

public class Dashboard {

    public Dashboard() {

        JFrame frame = new JFrame("Vehicle Rental System");

        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        JLabel title = new JLabel("Vehicle Rental Dashboard");
        title.setBounds(150, 30, 200, 30);

        JButton viewVehicles = new JButton("View Vehicles");
        viewVehicles.setBounds(170, 100, 150, 30);

        JButton rentVehicle = new JButton("Rent Vehicle");
        rentVehicle.setBounds(170, 150, 150, 30);

        JButton returnVehicle = new JButton("Return Vehicle");
        returnVehicle.setBounds(170, 200, 150, 30);

        frame.add(title);
        frame.add(viewVehicles);
        frame.add(rentVehicle);
        frame.add(returnVehicle);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new Dashboard();
    }
}