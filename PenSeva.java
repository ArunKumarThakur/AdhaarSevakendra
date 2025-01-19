package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Scanner;

public class PenSeva extends AadharSeva{


    public static ResultSet penDetails(String aadhaar) {
        try {

            Connection connection = getConnection();

            String query = "Select * from pancard where aadharNo = ?";

            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1, aadhaar);

            ResultSet result = statement.executeQuery();
            return result;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
