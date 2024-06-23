import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookDAO {

    public void addBook(int bookID, String title, String author, String isbn, int categoryID ) {
        String query = "INSERT INTO books (bookID, title, author, isbn, categoryID) VALUES (?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, bookID);
            preparedStatement.setString(1, title);
            preparedStatement.setString(2, author);
            preparedStatement.setString(3, isbn);
            preparedStatement.setInt(4, categoryID);

            preparedStatement.executeUpdate();
            System.out.println("Book added successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        }

        //getbook

    public void getBookById(int id) {
        String query = "SELECT * FROM books WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                System.out.println("BookID: " + resultSet.getInt("BookID"));
                System.out.println("Title: " + resultSet.getString("title"));
                System.out.println("Author: " + resultSet.getString("author"));
                System.out.println("ISBN: " + resultSet.getString("ISBN"));
                System.out.println("CategoryID: " + resultSet.getInt("CategoryID"));
            } else {
                System.out.println("Book not found.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //updateBook

    public void updateBook(int bookID, String title, String author, String isbn, int categoryID) {
        String query = "UPDATE books SET bookID = ?, title = ?, author = ?, isbn = ?, categoryID = ? WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, bookID);
            preparedStatement.setString(2, title);
            preparedStatement.setString(3, author);
            preparedStatement.setString(4, isbn);
            preparedStatement.setInt(5, categoryID);


            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Book updated successfully.");
            } else {
                System.out.println("Book not found.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //Delete book

    public void deleteBook(int id) {
        String query = "DELETE FROM books WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, id);

            int rowsDeleted = preparedStatement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Book deleted successfully.");
            } else {
                System.out.println("Book not found.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

