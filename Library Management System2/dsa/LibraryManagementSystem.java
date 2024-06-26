package dsa;

import models.Book;
import models.Category;
import models.Patron;
import models.Transaction;

import java.util.Stack;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Date;

class LibraryManagementSystem {
    private LinkedList<Book> books;
    private LinkedList<Patron> patrons;
    private LinkedList<Category> categories;
    private Stack<Transaction> transactionHistory;
    private Queue<Transaction> transactionQueue;

    public LibraryManagementSystem() {
        books = new LinkedList<>();
        patrons = new LinkedList<>();
        categories = new LinkedList<>();
        transactionHistory = new Stack<>();
        transactionQueue = new LinkedList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void addPatron(Patron patron) {
        patrons.add(patron);
    }

    public void addCategory(Category category) {
        categories.add(category);
    }

//    public void borrowBook(Book book, Patron patron) {
//        book.borrowBook();
//        Transaction transaction = new Transaction(book, patron, new Date(), "borrow");
//        transactionHistory.push(transaction);
//        transactionQueue.add(transaction);
//    }
//
//    public void returnBook(Book book, Patron patron) {
//        book.returnBook();
//        Transaction transaction = new Transaction(book, patron, new Date(), "return");
//        transactionHistory.push(transaction);
//        transactionQueue.add(transaction);
//    }

    // Other methods for managing the library
}
