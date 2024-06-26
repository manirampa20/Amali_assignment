package dao;

import database.LibraryManager;
import models.Transaction;

import java.sql.*;

public class TransactionDAO {

    // Create
    public void insert(Transaction transaction) {
        String query = "INSERT INTO Transaction (transactionId, bookId, fullName, borrowDate, expectedReturnDate, returnDate, status) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = LibraryManager.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {

            pstmt.setInt(1, transaction.getTransactionId());
            pstmt.setInt(2, transaction.getBookId());
            pstmt.setString(3, transaction.getFullName());
            pstmt.setDate(4, Date.valueOf(transaction.getBorrowDate()));
            pstmt.setDate(5, Date.valueOf(transaction.getExpectedReturnDate()));
            pstmt.setDate(6, Date.valueOf(transaction.getReturnDate()));
            pstmt.setString(7, transaction.getStatus());

            pstmt.executeUpdate();
            System.out.println("models.Transaction added successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Read Single models.Transaction
    public Transaction getTransactionById(int id) {
        String query = "SELECT * FROM Transaction WHERE transactionId = ?";

        try (Connection connection = LibraryManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, id);

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                Transaction transaction = new Transaction();
                transaction.setTransactionId(rs.getInt("transactionId"));
                transaction.setTransactionId(rs.getInt("bookId"));
                transaction.setFullName(rs.getString("fullName"));
                transaction.setBorrowDate(rs.getDate("borrowDate").toString());
                transaction.setExpectedReturnDate(rs.getDate("expectedReturnDate").toString());
                transaction.setReturnDate(rs.getDate("returnDate").toString());
                transaction.setStatus(rs.getString("status"));
                return transaction;
            } else {
                System.out.println("models.Book not found.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    // Update
    public void updateTransaction(Transaction transaction) {
        String query = "UPDATE Transaction SET fullName = ?, borrowDate = ?, expectedReturnDate = ?, returnDate = ?, status = ? WHERE transactionId = ?";

        try (Connection connection = LibraryManager.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(5, transaction.getTransactionId());
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                pstmt.setString(1, transaction.getFullName());
                pstmt.setDate(2, Date.valueOf(transaction.getBorrowDate()));
                pstmt.setDate(3, Date.valueOf(transaction.getExpectedReturnDate()));
                pstmt.setDate(4, Date.valueOf(transaction.getReturnDate()));
                pstmt.setString(5, transaction.getStatus());
                pstmt.setInt(6, transaction.getTransactionId());
                pstmt.executeUpdate();
            } else {
                System.out.println("models.Transaction not found.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete
    public void deleteTransaction(int id)  {
        String query = "DELETE FROM Transaction WHERE transactionId = ?";

        try (Connection connection = LibraryManager.getConnection();
                PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
