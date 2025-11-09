package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DatabaseService {

    private static final String URL = "jdbc:mysql://localhost:3306/WeatherDB";
    private static final String USER = "root";
    private static final String PASS = "Kinshu@8725";

    public static void addUser(String name, String email, String city) throws Exception {
        Connection conn = DriverManager.getConnection(URL, USER, PASS);
        String sql = "INSERT INTO Users (name, email, city) VALUES (?, ?, ?)";

        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, name);
        stmt.setString(2, email);
        stmt.setString(3, city);

        stmt.executeUpdate();
        System.out.println("User added successfully!");

        stmt.close();
        conn.close();
    }
}
