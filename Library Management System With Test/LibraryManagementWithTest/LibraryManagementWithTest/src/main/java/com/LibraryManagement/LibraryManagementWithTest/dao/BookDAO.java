package com.LibraryManagement.LibraryManagementWithTest.dao;

import com.LibraryManagement.LibraryManagementWithTest.database.LibraryManager;
import com.LibraryManagement.LibraryManagementWithTest.models.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {

    // Method to add a new book to the database
    public void addBook(Book book) {
        String sql = "INSERT INTO book (title, author, isbn, genre, copiesAvailable, isAvailable) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = LibraryManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, book.getTitle());
            pstmt.setString(2, book.getAuthor());
            pstmt.setString(3, book.getIsbn());
            pstmt.setString(4, book.getGenre());
            pstmt.setInt(5, book.getCopiesAvailable());
            pstmt.setBoolean(6, book.isAvailable());
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                ResultSet generatedKeys = pstmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    book.setBookId(generatedKeys.getInt(1));
                }
            } else {
                throw new RuntimeException("Error adding book to database");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error adding book", e);
        }
    }


    // Method to get a book by ID
    public Book getBookById(int bookId) {
        String sql = "SELECT * FROM book WHERE bookId = ?";
        try (Connection conn = LibraryManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, bookId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Book(
                        rs.getInt("bookId"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("isbn"),
                        rs.getString("genre"),
                        rs.getInt("copiesAvailable"),
                        rs.getBoolean("isAvailable")
                );
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error retrieving book", e);
        }
    }



    // Method to get all books
    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM book";

        try (Connection connection = LibraryManager.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                int bookId = resultSet.getInt("bookId");
                String title = resultSet.getString("title");
                String author = resultSet.getString("author");
                String isbn = resultSet.getString("isbn");
                String genre = resultSet.getString("genre");
                int copiesAvailable = resultSet.getInt("copiesAvailable");
                boolean isAvailable = resultSet.getBoolean("isAvailable");
                books.add(new Book(bookId, title, author, isbn, genre, copiesAvailable, isAvailable));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return books;
    }

    // Method to update a book
    public void updateBook(Book book) {
        String sql = "UPDATE book SET title = ?, author = ?, isbn = ?, genre = ?, copiesAvailable = ?, isAvailable = ? WHERE bookId = ?";
        try (Connection conn = LibraryManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, book.getTitle());
            pstmt.setString(2, book.getAuthor());
            pstmt.setString(3, book.getIsbn());
            pstmt.setString(4, book.getGenre());
            pstmt.setInt(5, book.getCopiesAvailable());
            pstmt.setBoolean(6, book.isAvailable());
            pstmt.setInt(7, book.getBookId());
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new RuntimeException("No book found with id: " + book.getBookId());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error updating book", e);
        }
    }



    // Method to delete a book
    public void deleteBook(int bookId) {
        String sql = "DELETE FROM book WHERE bookId = ?";

        try (Connection connection = LibraryManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, bookId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    }


