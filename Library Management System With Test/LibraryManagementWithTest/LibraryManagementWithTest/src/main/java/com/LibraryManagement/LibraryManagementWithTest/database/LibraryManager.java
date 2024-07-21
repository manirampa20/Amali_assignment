package com.LibraryManagement.LibraryManagementWithTest.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class LibraryManager {

    private static final String URL = "jdbc:mysql://localhost:3306/library";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void createDatabaseTables() {
        String createBookTable = "CREATE TABLE IF NOT EXISTS book (" +
                "bookId INT AUTO_INCREMENT PRIMARY KEY," +
                "title VARCHAR(255) NOT NULL," +
                "author VARCHAR(255) NOT NULL," +
                "isbn VARCHAR(50) NOT NULL," +
                "genre VARCHAR(50) NOT NULL," +
                "copiesAvailable INT NOT NULL," +
                "isAvailable BOOLEAN NOT NULL" +
                ");";

        String createTransactionTable = "CREATE TABLE IF NOT EXISTS transaction (" +
                "transactionId INT AUTO_INCREMENT PRIMARY KEY," +
                "bookId INT," +
                "fullName VARCHAR(255) NOT NULL," +
                "borrowDate DATE NOT NULL," +
                "dueDate DATE NOT NULL," +
                "returnDate DATE," +
                "status VARCHAR(50) NOT NULL," +
                "FOREIGN KEY (bookId) REFERENCES book(bookId)" +
                ");";

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(createBookTable);
            statement.execute(createTransactionTable);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
