package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLOutput;

public class AadhaarUpdate extends AadharSeva{

    public static void updateAadhaar(String aadhaar) {
        System.out.println("========== UPDATE YOUR AADHAR BY YOURSELF =============");

        int opt = 1;

        while(opt != 0) {
            System.out.println("0. For Exit");
            System.out.println("1. Update Your Name");
            System.out.println("2. Update Your Address");

            opt = sc.nextInt();

            switch (opt) {
                //case 0 -> System.exit(1);
                case 1 -> updateName(aadhaar);
                case 2 -> updateAddress(aadhaar);
                default -> System.out.println("Invalid output");
            }
        }
    }

    public static void updateName(String aadhaar) {
        try {
            Connection connection = getConnection();
            if (sc.hasNextLine()) {
                sc.nextLine();
            }
            System.out.println("Enter New Name: ");

            String newName = sc.nextLine();

            String query = "Update aadharmap set name = ? where aadharNo = ?";

            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1, newName);
            statement.setString(2, aadhaar);
            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Name updated successfully.");
            } else {
                System.out.println("Aadhaar number not found, update failed.");
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateAddress(String aadhaar) {
        try {
            Connection connection = getConnection();
            if (sc.hasNextLine()) {
                sc.nextLine();
            }
            System.out.println("Enter New Address: ");

            String address = sc.nextLine();

            String query = "Update aadharmap set address = ? where aadharNo = ?";

            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1, address);
            statement.setString(2, aadhaar);
            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Address updated successfully.");
            } else {
                System.out.println("Aadhaar number not found, update failed.");
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
