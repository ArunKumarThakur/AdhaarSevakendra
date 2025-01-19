package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.System.exit;

public class AadharSeva {

    public static Scanner sc = new Scanner(System.in);
    public static String LOAD_DRIVER = "com.mysql.cj.jdbc.Driver";
    public static String URL = "jdbc:mysql://localhost:3306/studentdb";
    public static String USER = "root";
    public static String PASSWORD ="root";
    public static void main(String[] args) {
        System.out.println("\n\n");
        System.out.println("================== WELCOME TO AADHAAR SEWA KENDRA =================");
        int opt = 1;

        while (opt != 0) {

            System.out.println("1. Aadhaar Registration ");
            System.out.println("2. Aadhaar Update ");
            System.out.println("3. Aadhaar Pan Linked check ");
            System.out.println("4. Aadhaar Details ");
            System.out.println("5. Pen Details ");
            System.out.println("Press numeric value to services ");
            opt = sc.nextInt();
            sc.nextLine();
            switch (opt) {
                case 0 -> exit(1);
                case 1 -> AadhaarRegistration.aadhaarRegistration();
                case 2 -> aadhaarUpdate();
                case 3 -> aadhaarPanLinked();
                case 4 -> detailsByAadhaar();
                case 5 -> panDetails();
                default -> System.out.println("Invalid Input");
            }
        }
    }


    public static void aadhaarUpdate(){
        System.out.println("Enter Your aadhaar");
        String aadhaar = sc.nextLine();
        AadhaarUpdate.updateAadhaar(aadhaar);
    }

    public static void detailsByAadhaar() {
        ResultSet result = null;

        try {
            System.out.println("Enter aadhaar number: ");
            String aadhaar = sc.nextLine();
            result = aadhaarDetails(aadhaar);

            if(result == null) {
                System.out.println("Invalid aadhaar");
                return;
            }
            if(result.next()) {
                String aadharNumber = result.getString("aadharNo");
                String name = result.getString("name");
                String fatherName = result.getString("fatherName");
                String address = result.getString("address");

                ArrayList<String> al = new ArrayList<>();
                al.add(aadharNumber);al.add(name);al.add(fatherName);al.add(address);
                System.out.println(
                        al.toString()
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(LOAD_DRIVER);
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return connection;
    }

    public static ResultSet aadhaarDetails(String aadhaar) {
        try {
            Connection connection = getConnection();

            String query = "Select * from aadharmap where aadharNo = ?";

            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1, aadhaar);

            ResultSet result = statement.executeQuery();

            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;

    }
    public static void panDetails() {
        System.out.println("Enter aadhaar number: ");
        String aadhaar = sc.nextLine();
        ResultSet result = null;
        try {
            result = PenSeva.penDetails(aadhaar);
            if(result.next()) {
                String aadharNumber = result.getString("aadharNo");
                String panNumber = result.getString("panNumber");
                String bank = result.getString("bank");
                String investment = result.getString("Investment");

                ArrayList<String> al = new ArrayList<>();
                al.add(aadharNumber);al.add(panNumber);al.add(bank);al.add(investment);
                System.out.println(
                        al.toString()
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void aadhaarPanLinked() {
        System.out.println("Enter aadhaar number: ");
        String aadhaar = sc.nextLine();
        ResultSet set1 = null;
        ResultSet set2 = null;

        try {
            set1 = aadhaarDetails(aadhaar);
            set2 = PenSeva.penDetails(aadhaar);
        } catch (Exception e) {
            e.printStackTrace();
        }


        if(set1 != null && set2 != null)
            System.out.println("Aadhaar Map Linked Found (:");
        else
            System.out.println("Aadhaar Map Doesn't Linked ):");
    }
}
