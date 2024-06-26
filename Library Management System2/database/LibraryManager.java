package database;

import java.sql.*;

public class LibraryManager {
    private static final String URL = "jdbc:mysql://localhost:3306/library";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    // Establish connection
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void createDatabaseTables() {
        createBookTable();
        createTransactionTable();
    }

    private static void createBookTable() {
        try {
            Connection connection = LibraryManager.getConnection();
            Statement statement = connection.createStatement();
            String createTableSQL = "CREATE TABLE IF NOT EXISTS book (" +
                    "bookId INT AUTO_INCREMENT PRIMARY KEY, " +
                    "title VARCHAR(255) NOT NULL, " +
                    "author VARCHAR(255) NOT NULL, " +
                    "isbn VARCHAR(13) NOT NULL " +
                    ");";

            statement.executeUpdate(createTableSQL);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void createTransactionTable() {
        try {
            Connection connection = LibraryManager.getConnection();
            Statement statement = connection.createStatement();
            String createTableSQL = "CREATE TABLE IF NOT EXISTS Transaction (\n" +
                    "    transactionId INT,\n" +
                    "    bookId INT,\n" +
                    "    fullName VARCHAR(255),\n" +
                    "    borrowDate DATE,\n" +
                    "    expectedReturnDate DATE,\n" +
                    "    returnDate DATE,\n" +
                    "    status VARCHAR(10) CHECK (status IN ('BORROWED', 'RETURNED'))\n" +
                    ");";

            statement.executeUpdate(createTableSQL);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}






