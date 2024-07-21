package com.LibraryManagement.LibraryManagementWithTest.models;

import java.util.List;

public class Librarian {
    private String name;
    private String librarianId;

    public Librarian(String name, String librarianId) {
        this.name = name;
        this.librarianId = librarianId;
    }

    // Method to add a book to the library collection
    public void addBook(List<Book> library, Book newBook) {
        library.add(newBook);
        System.out.println("Book added: " + newBook.getTitle());
    }

    // Method to remove a book from the library collection
    public void removeBook(List<Book> library, Book book) {
        if (library.remove(book)) {
            System.out.println("Book removed: " + book.getTitle());
        } else {
            System.out.println("Book not found: " + book.getTitle());
        }
    }

    // Method to update book information
    public void updateBook(Book book, String title, String author, String genre, int copiesAvailable) {
        book.setTitle(title);
        book.setAuthor(author);
        book.setGenre(genre);
        book.setCopiesAvailable(copiesAvailable);
        System.out.println("Book updated: " + book.getTitle());
    }

    // Method to manage book borrowing
    public void borrowBook(Book book) {
        if (book.getCopiesAvailable() > 0) {
            book.borrowBook();
            System.out.println("Book borrowed: " + book.getTitle());
        } else {
            System.out.println("No copies available for: " + book.getTitle());
        }
    }

    // Method to manage book returning
    public void returnBook(Book book) {
        book.returnBook();
        System.out.println("Book returned: " + book.getTitle());
    }

    // Getters and setters for librarian fields
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLibrarianId() {
        return librarianId;
    }

    public void setLibrarianId(String librarianId) {
        this.librarianId = librarianId;
    }
}
