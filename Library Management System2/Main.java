import dao.BookDAO;
import dao.TransactionDAO;
import database.LibraryManager;
import models.Book;
import models.Transaction;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        LibraryManager.createDatabaseTables();

        // DATABASE

        BookDAO bookDAO = new BookDAO();

        //Create
        bookDAO.addBook(1, "Principles of Joy and Peace", "Ruth", "ISBN-12345");
//        bookDAO.addBook(2, "You live again", "Martin", "ISBN-12353");
//        bookDAO.addBook(3, "Make us proud", "CRUG", "ISBN-12335");
//        bookDAO.addBook(4, "LIVE AND MAKE IT", "MARVIN", "ISBN-12935");


//      // Read
        Book book1 = bookDAO.getBookById(1);
        System.out.println("Read One book - " + book1.getTitle());

//        // Read All
        List<Book> books = bookDAO.getAllBooks();
        System.out.println("Stored Books - " + books.size());

         //Update
        bookDAO.updateBook(120, "The GREAT WALL", "Marcus", "19", 1);
        bookDAO.getBookById(6);



        // Delete
        bookDAO.deleteBook(1);

        // Delete All
//        for (Book book : books)  {
//            bookDAO.deleteBook(book.getBookId());
//        }
//        System.out.println("Stored Books - Deleted" + books.size());


        TransactionDAO transactionDAO = new TransactionDAO();

        // Create a new transaction
        Transaction newTransaction = new Transaction();
        newTransaction.setTransactionId(1);
        newTransaction.setBookId(1);
        newTransaction.setFullName("John Doe");
        newTransaction.setBorrowDate("2024-06-25");
        newTransaction.setExpectedReturnDate("2024-07-25");
        newTransaction.setReturnDate("2024-07-01");
        newTransaction.setStatus("BORROWED");
        transactionDAO.insert(newTransaction);


        // Read a transaction
        Transaction transaction = transactionDAO.getTransactionById(1);
        System.out.println("Transaction: " + transaction.getFullName());

        // Update a transaction
//        transaction.setStatus("RETURNED");
//        transactionDAO.updateTransaction(transaction);

        // Delete
//        transactionDAO.deleteTransaction(1);
















        //        dsa.LibraryManagementSystem lms = new dsa.LibraryManagementSystem();
//
//        models.Category fiction = new models.Category("Fiction", "CAT0001");
//        models.Category science = new models.Category("Science", "CAT0002");
//
//        lms.addCategory(fiction);
//        lms.addCategory(science);
//
//        models.Book book1 = new models.Book(2,"models.Book Title 1", "Author 1", "ISBN0001", fiction);
//        models.EBook ebook1 = new models.EBook(23,"models.EBook Title 1", "Author 2", "ISBN0002", science, "downloadLink1");
//        models.AudioBook audiobook1 = new models.AudioBook(45,"models.AudioBook Title 1", "Author 3", "ISBN0003", fiction, "audioLink1");
//
//        lms.addBook(book1);
//        lms.addBook(ebook1);
//        lms.addBook(audiobook1);
//
//
//
//        models.Patron patron1 = new models.Patron("models.Patron 1", "ID0001");
//        lms.addPatron(patron1);
//
//        lms.borrowBook(book1, patron1);
//        lms.borrowBook(ebook1, patron1);
//        lms.borrowBook(audiobook1, patron1);
//
//        lms.returnBook(book1, patron1);

    }
}

