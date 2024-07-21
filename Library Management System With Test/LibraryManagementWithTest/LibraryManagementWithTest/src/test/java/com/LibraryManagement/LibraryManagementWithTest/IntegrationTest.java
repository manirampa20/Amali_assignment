package com.LibraryManagement.LibraryManagementWithTest;

import com.LibraryManagement.LibraryManagementWithTest.dao.BookDAO;
import com.LibraryManagement.LibraryManagementWithTest.dao.TransactionDAO;
import com.LibraryManagement.LibraryManagementWithTest.database.LibraryManager;
import com.LibraryManagement.LibraryManagementWithTest.models.Book;
import com.LibraryManagement.LibraryManagementWithTest.models.Transaction;
import org.junit.jupiter.api.*;

import java.sql.Date;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class IntegrationTest {
    private static BookDAO bookDAO;
    private static TransactionDAO transactionDAO;

    @BeforeAll
    public static void setup() {
        bookDAO = new BookDAO();
        transactionDAO = new TransactionDAO();
        try (var conn = LibraryManager.getConnection()) {
            String createBookTableSQL = "CREATE TABLE book ("
                    + "bookId INT AUTO_INCREMENT, "
                    + "title VARCHAR(255), "
                    + "author VARCHAR(255), "
                    + "isbn VARCHAR(13), "
                    + "genre VARCHAR(255), "
                    + "copiesAvailable INT, "
                    + "isAvailable BOOLEAN, "
                    + "PRIMARY KEY (bookId))";
            conn.createStatement().execute(createBookTableSQL);

            String createTransactionTableSQL = "CREATE TABLE transaction ("
                    + "transactionId INT AUTO_INCREMENT, "
                    + "bookId INT, "
                    + "patronId INT, "
                    + "transactionType VARCHAR(255), "
                    + "transactionDate DATE, "
                    + "PRIMARY KEY (transactionId))";
            conn.createStatement().execute(createTransactionTableSQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testAddBookAndPerformTransaction() {
        // Add a new book
        Book book = new Book(0, "Integration Test Title", "Integration Test Author", "9876543210987", "Integration Test Genre", 5, true);
        bookDAO.addBook(book);
        Book addedBook = bookDAO.getBookById(book.getBookId());
        assertNotNull(addedBook, "Added book should not be null");
        assertEquals("Integration Test Title", addedBook.getTitle());


    }

    @AfterAll
    public static void cleanup() {
        try (var conn = LibraryManager.getConnection()) {
            String dropBookTableSQL = "DROP TABLE IF EXISTS book";
            conn.createStatement().execute(dropBookTableSQL);
            String dropTransactionTableSQL = "DROP TABLE IF EXISTS transaction";
            conn.createStatement().execute(dropTransactionTableSQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
