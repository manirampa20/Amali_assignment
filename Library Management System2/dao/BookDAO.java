package dao;

import database.LibraryManager;
import models.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {

    // Create
    public void addBook(int bookID, String title, String author, String isbn ) {
        String query = "INSERT INTO book (bookId, title, author, isbn) VALUES (?, ?, ?, ?)";


        try (Connection connection = LibraryManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, bookID);
            preparedStatement.setString(2, title);
            preparedStatement.setString(3, author);
            preparedStatement.setString(4, isbn);


            preparedStatement.executeUpdate();
            System.out.println("models.Book added successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Read all
    public List<Book> getAllBooks() {

        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM book";
        try (Connection conn = LibraryManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                books.add(
                        new Book(
                                rs.getInt("bookId"),
                                rs.getString("title"),
                                rs.getString("author"),
                                rs.getString("ISBN")
//                        rs.getInt("CategoryID")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    // Read Single models.Book
    public Book getBookById(int id) {
        String query = "SELECT * FROM book WHERE bookId = ?";

        try (Connection connection = LibraryManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, id);

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                Book book = new Book(
                    rs.getInt("bookId"),
                    rs.getString("title"),
                    rs.getString("author"),
                    rs.getString("ISBN")
                );
                return book;
               } else {
                System.out.println("models.Book not found.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //updateBook

    public void updateBook(int bookID, String title, String author, String isbn, int categoryID) {
        String query = "UPDATE book SET bookId = ?, title = ?, author = ?, isbn = ?, categoryID = ? WHERE id = ?";

        try (Connection connection = LibraryManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, bookID);
            preparedStatement.setString(2, title);
            preparedStatement.setString(3, author);
            preparedStatement.setString(4, isbn);
            preparedStatement.setInt(5, categoryID);


            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("models.Book updated successfully.");
            } else {
                System.out.println("models.Book not found.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //Delete book

    public void deleteBook(int id) {
        String query = "DELETE FROM book WHERE bookId = ?";

        try (Connection connection = LibraryManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, id);

            int rowsDeleted = preparedStatement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("models.Book deleted successfully.");
            } else {
                System.out.println("models.Book not found.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

