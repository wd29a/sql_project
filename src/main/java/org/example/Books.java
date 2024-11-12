package org.example;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Database directory and name
        String dbDirectory = "database_files/";
        String dbName = "books.db";
        String full = dbDirectory + dbName;
        String fulldbPath = "jdbc:sqlite:" + full;

        // Connecting to the database
        try (Connection connection = DriverManager.getConnection(fulldbPath)) {
            if (connection != null) {
                System.out.println("Connected to database");

                // SQL to create students table with AUTO-INCREMENT ID and surname
                String createSQLTable = "CREATE TABLE IF NOT EXISTS book_details (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT, " +  // id as auto-increment integer
                        "name VARCHAR(100) NOT NULL, " +
                        "authors  VARCHAR(100) NOT NULL, " +
                        "publication_year VARCHAR(100), " +
                        "copies_sold INTEGER NOT NULL DEFAULT 0,"+
                        "book_type VARCHAR(100) NOT NULL)";

                try (Statement statement = connection.createStatement()) {
                    statement.execute(createSQLTable);  // Execute the table creation
                } catch (SQLException e) {
                    System.out.println("Error creating table: " + e.getMessage());
                }

            } else {
                System.out.println("Had problems with connection");
            }

        } catch (SQLException e) {
            System.out.println("Error " + e.getMessage());
        }

        // Collecting student information
        System.out.print("Enter book name: ");
        String name = sc.nextLine();  // The name entered by the user
        System.out.print("Enter author: ");
        String authors = sc.nextLine();
        System.out.print("Enter book publication year (YYYY): ");
        String publication_year = sc.nextLine();  // The date of birth entered by the user
        System.out.print("Enter book type (hard|ebook)");
        String book_type = sc.nextLine();  // The email entered by the user




        Integer copies_sold= 0;


        // SQL statement to insert student data, ID is auto-incremented
        String insertSQLStatement = "INSERT INTO users (name, surname, date_of_birth, email,registration_date,role_id,number_of_books_uploaded)" +
                " VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(fulldbPath)) {
            try (PreparedStatement statement = connection.prepareStatement(insertSQLStatement)) {
                statement.setString(1, name);  // Set name as VARCHAR
                statement.setString(2, surname);  // Set surname as VARCHAR
                statement.setString(3, dateOfBirth);  // Set date of birth as VARCHAR
                statement.setString(4, email);  // Set email as VARCHAR
                statement.setString(5, registration_date);
                statement.setInt(6, role_id);
                statement.setInt(7, number_of_books_uploaded);
                statement.executeUpdate();  // Execute the insertion
                System.out.println("User data has been inserted successfully!");
            } catch (SQLException e) {
                System.out.println("Error inserting data: " + e.getMessage());
            }

        } catch (SQLException e) {
            System.out.println("Error " + e.getMessage());
        }
    }
}