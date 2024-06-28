package com.example.demo.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

public class utility {

    private static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASSWORD = "1234";

    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            // Register PostgreSQL JDBC driver
            Class.forName("org.postgresql.Driver");

            // Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);

            // SQL query to insert random numbers
            String sql = "INSERT INTO users (phone_number) VALUES (?)";
            stmt = conn.prepareStatement(sql);

            // Generate and insert 10 random numbers
            Random random = new Random();
            for (int i = 0; i < 10; i++) {
                String randomNumber = generateRandomNumber(random);
                stmt.setString(1, randomNumber);
                stmt.executeUpdate();
                System.out.println("Inserted phone number: " + randomNumber);
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            // Close resources in a finally block
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static String generateRandomNumber(Random random) {
        // Generate a random 5-digit number
        int randomInt = random.nextInt(90000) + 10000;
        return String.valueOf(randomInt);
    }

}
