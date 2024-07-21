package com.LibraryManagement.LibraryManagementWithTest;

import com.LibraryManagement.LibraryManagementWithTest.dao.BookDAO;
import com.LibraryManagement.LibraryManagementWithTest.models.Book;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BookDAOTest {

    @Mock
    private BookDAO bookDAO;

    @InjectMocks
    private BookDAOTest bookDAOTest;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 5, 10})
    public void testGetBookById(int bookId) {
        Book mockBook = new Book(bookId, "Title", "Author", "1234567890123", "Genre", 1, true);

        when(bookDAO.getBookById(bookId)).thenReturn(mockBook);

        Book retrievedBook = bookDAO.getBookById(bookId);
        assertNotNull(retrievedBook);
        assertEquals(bookId, retrievedBook.getBookId());
        assertEquals("Title", retrievedBook.getTitle()); // Additional check for Title
    }

    @ParameterizedTest
    @MethodSource("provideBooksForAddTest")
    public void testAddBook(Book book, boolean shouldExist) {
        when(bookDAO.getBookById(book.getBookId())).thenReturn(shouldExist ? book : null);

        bookDAO.addBook(book);
        Book retrievedBook = bookDAO.getBookById(book.getBookId());

        if (shouldExist) {
            assertNotNull(retrievedBook);
            assertEquals(book.getTitle(), retrievedBook.getTitle());
            assertEquals(book.getAuthor(), retrievedBook.getAuthor()); // Additional check for Author
        } else {
            assertNull(retrievedBook);
        }
    }

    private static Stream<org.junit.jupiter.params.provider.Arguments> provideBooksForAddTest() {
        return Stream.of(
                org.junit.jupiter.params.provider.Arguments.of(
                        new Book(1, "Book 1", "Author 1", "1234567890123", "Genre 1", 1, true), true
                ),
                org.junit.jupiter.params.provider.Arguments.of(
                        new Book(2, "Book 2", "Author 2", "1234567890124", "Genre 2", 2, false), false
                )
        );
    }

    // Additional regression test for edge cases
    @ParameterizedTest
    @ValueSource(ints = {0, -1, Integer.MAX_VALUE})
    public void testGetBookByIdEdgeCases(int bookId) {
        when(bookDAO.getBookById(bookId)).thenReturn(null);

        Book retrievedBook = bookDAO.getBookById(bookId);
        assertNull(retrievedBook);
    }

    // Regression test for adding books with existing IDs
    @ParameterizedTest
    @MethodSource("provideBooksForAddTest")
    public void testAddBookWithExistingId(Book book, boolean shouldExist) {
        if (shouldExist) {
            when(bookDAO.getBookById(book.getBookId())).thenReturn(book);
        } else {
            when(bookDAO.getBookById(book.getBookId())).thenReturn(null);
        }

        bookDAO.addBook(book);
        Book retrievedBook = bookDAO.getBookById(book.getBookId());

        if (shouldExist) {
            assertNotNull(retrievedBook);
            assertEquals(book.getTitle(), retrievedBook.getTitle());
        } else {
            assertNull(retrievedBook);
        }
    }
}
