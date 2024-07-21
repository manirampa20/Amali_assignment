package com.LibraryManagement.LibraryManagementWithTest;

import com.LibraryManagement.LibraryManagementWithTest.dao.BookDAO;
import com.LibraryManagement.LibraryManagementWithTest.dao.TransactionDAO;
import com.LibraryManagement.LibraryManagementWithTest.database.LibraryManager;
import com.LibraryManagement.LibraryManagementWithTest.models.Book;
import com.LibraryManagement.LibraryManagementWithTest.models.Transaction;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class LibraryManagementWithTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryManagementWithTestApplication.class, args);

		// Initialize database tables
		LibraryManager.createDatabaseTables();

		// Initialize DAOs
		BookDAO bookDAO = new BookDAO();
		TransactionDAO transactionDAO = new TransactionDAO();

		// Create a new book and insert it into the database

		Book newBook = new Book(2, "POWER OF THE MIND", "ANDREW", "10", "Gospel", 7, true);

		bookDAO.addBook(newBook);

		System.out.println("Added new book: " + newBook);



		// Read all books
		List<Book> books = bookDAO.getAllBooks();
		System.out.println("Stored Books - " + books.size());

		// Read a specific book by ID
		Book book = bookDAO.getBookById(2);
		System.out.println("Read book by ID: " + book);


		// Update a book's details
		book.setTitle("The Great Wall Revised");
		bookDAO.updateBook(book);
		System.out.println("Updated book: " + book);

		// Delete a book
		bookDAO.deleteBook(book.getBookId());
		System.out.println("Deleted book with ID: " + book.getBookId());

 	     // Perform borrow and return book operations
		// Add the book again for the borrowing and returning demonstration
		bookDAO.addBook(newBook);
		Book bookToBorrow = bookDAO.getBookById(2);
		System.out.println("Book before borrowing: " + bookToBorrow);
		bookToBorrow.borrowBook();
		bookDAO.updateBook(bookToBorrow);
		System.out.println("Book after borrowing: " + bookToBorrow);

		// Return the book
		System.out.println("Book before returning: " + bookToBorrow);
		bookToBorrow.returnBook();
		bookDAO.updateBook(bookToBorrow);
		System.out.println("Book after returning: " + bookToBorrow);

		// Transaction handling (borrow and return)
		// Create a new transaction
		Transaction newTransaction = new Transaction(1, bookToBorrow.getBookId(), "John Doe", "2024-06-25", "2024-07-29", "2024-07-01", "BORROWED");
		transactionDAO.insert(newTransaction);
		System.out.println("New Transaction: " + newTransaction);

		// Read a transaction
		Transaction transaction = transactionDAO.getTransactionById(1);
		System.out.println("Transaction: " + transaction);

		// Update a transaction
		transaction.setStatus("RETURNED");
		transactionDAO.updateTransaction(transaction);
		System.out.println("Updated Transaction: " + transaction);

		// Delete a transaction (optional)
		// transactionDAO.deleteTransaction(1);
	}
}
