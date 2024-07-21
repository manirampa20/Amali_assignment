package com.LibraryManagement.LibraryManagementWithTest.models;

public class Book {
    private int bookId;
    private String title;
    private String author;
    private String isbn;
    private String genre;
    private int copiesAvailable;
    private boolean isAvailable;

    public Book(int bookId, String title, String author, String isbn, String genre, int copiesAvailable, boolean isAvailable) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.genre = genre;
        this.copiesAvailable = copiesAvailable;
        this.isAvailable = isAvailable;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getCopiesAvailable() {
        return copiesAvailable;
    }

    public void setCopiesAvailable(int copiesAvailable) {
        this.copiesAvailable = copiesAvailable;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public void borrowBook() {
        if (copiesAvailable > 0) {
            copiesAvailable--;
            if (copiesAvailable == 0) {
                isAvailable = false;
            }
        }
    }

    public void returnBook() {
        copiesAvailable++;
        isAvailable = true;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", isbn='" + isbn + '\'' +
                ", genre='" + genre + '\'' +
                ", copiesAvailable=" + copiesAvailable +
                ", isAvailable=" + isAvailable +
                '}';
    }
}
