package com.LibraryManagement.LibraryManagementWithTest.dao;

import com.LibraryManagement.LibraryManagementWithTest.database.LibraryManager;
import com.LibraryManagement.LibraryManagementWithTest.models.Transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TransactionDAO {

    // Method to insert a new transaction
    public void insert(Transaction transaction) {
        String sql = "INSERT INTO transaction (transactionId, bookId, fullName, borrowDate, dueDate, returnDate, status) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = LibraryManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, transaction.getTransactionId());
            statement.setInt(2, transaction.getBookId());
            statement.setString(3, transaction.getFullName());
            statement.setString(4, transaction.getBorrowDate());
            statement.setString(5, transaction.getDueDate());
            statement.setString(6, transaction.getReturnDate());
            statement.setString(7, transaction.getStatus());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to get a transaction by ID
    public Transaction getTransactionById(int transactionId) {
        String sql = "SELECT * FROM transaction WHERE transactionId = ?";
        Transaction transaction = null;

        try (Connection connection = LibraryManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, transactionId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int bookId = resultSet.getInt("bookId");
                String fullName = resultSet.getString("fullName");
                String borrowDate = resultSet.getString("borrowDate");
                String dueDate = resultSet.getString("dueDate");
                String returnDate = resultSet.getString("returnDate");
                String status = resultSet.getString("status");
                transaction = new Transaction(transactionId, bookId, fullName, borrowDate, dueDate, returnDate, status);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return transaction;
    }

    // Method to update a transaction
    public void updateTransaction(Transaction transaction) {
        String sql = "UPDATE transaction SET bookId = ?, fullName = ?, borrowDate = ?, dueDate = ?, returnDate = ?, status = ? WHERE transactionId = ?";

        try (Connection connection = LibraryManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, transaction.getBookId());
            statement.setString(2, transaction.getFullName());
            statement.setString(3, transaction.getBorrowDate());
            statement.setString(4, transaction.getDueDate());
            statement.setString(5, transaction.getReturnDate());
            statement.setString(6, transaction.getStatus());
            statement.setInt(7, transaction.getTransactionId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to delete a transaction
    public void deleteTransaction(int transactionId) {
        String sql = "DELETE FROM transaction WHERE transactionId = ?";

        try (Connection connection = LibraryManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, transactionId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to perform a transaction (borrow/return book)
    public void performTransaction(int transactionId, String status, String returnDate) {
        // Update the transaction status and return date
        String sql = "UPDATE transaction SET status = ?, returnDate = ? WHERE transactionId = ?";

        try (Connection connection = LibraryManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, status);
            statement.setString(2, returnDate);
            statement.setInt(3, transactionId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void performTransaction(Transaction transaction) {
        // Check if the book is available before performing the transaction
        String checkAvailabilitySQL = "SELECT isAvailable, copiesAvailable FROM book WHERE bookId = ?";
        String updateBookStatusSQL = "UPDATE book SET isAvailable = ?, copiesAvailable = ? WHERE bookId = ?";

        try (Connection connection = LibraryManager.getConnection()) {
            // Check book availability
            try (PreparedStatement checkStmt = connection.prepareStatement(checkAvailabilitySQL)) {
                checkStmt.setInt(1, transaction.getBookId());
                ResultSet resultSet = checkStmt.executeQuery();
                if (resultSet.next()) {
                    boolean isAvailable = resultSet.getBoolean("isAvailable");
                    int copiesAvailable = resultSet.getInt("copiesAvailable");

                    if (!isAvailable || copiesAvailable <= 0) {
                        throw new SQLException("Book is not available for transaction.");
                    }

                    // Perform the transaction
                    insert(transaction);

                    // Update the book's availability status and number of copies available
                    if ("BORROW".equals(transaction.getTransactionType())) {
                        copiesAvailable--;
                        if (copiesAvailable == 0) {
                            isAvailable = false;
                        }
                    } else if ("RETURN".equals(transaction.getTransactionType())) {
                        copiesAvailable++;
                        isAvailable = true;
                    }

                    try (PreparedStatement updateStmt = connection.prepareStatement(updateBookStatusSQL)) {
                        updateStmt.setBoolean(1, isAvailable);
                        updateStmt.setInt(2, copiesAvailable);
                        updateStmt.setInt(3, transaction.getBookId());
                        updateStmt.executeUpdate();
                    }
                } else {
                    throw new SQLException("Book not found.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to get all transactions
    public List<Transaction> getAllTransactions() {
        String sql = "SELECT * FROM transaction";
        List<Transaction> transactions = new ArrayList<>();

        try (Connection connection = LibraryManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int transactionId = resultSet.getInt("transactionId");
                int bookId = resultSet.getInt("bookId");
                String fullName = resultSet.getString("fullName");
                String borrowDate = resultSet.getString("borrowDate");
                String dueDate = resultSet.getString("dueDate");
                String returnDate = resultSet.getString("returnDate");
                String status = resultSet.getString("status");

                Transaction transaction = new Transaction(transactionId, bookId, fullName, borrowDate, dueDate, returnDate, status);
                transactions.add(transaction);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return transactions;
    }
}
