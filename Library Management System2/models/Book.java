package models;

public class Book {
    private int bookId;
    private String title;
    private String author;
    private String ISBN;
    private boolean isAvailable;

    public Book(int bookId, String title, String author, String ISBN) {
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.isAvailable = true;
    }
    // Getter methods
    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getISBN() {
        return ISBN;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public int getBookId() {
        return bookId;
    }


    public void borrowBook() {
        if (isAvailable) {
            isAvailable = false;
        } else {
            System.out.println("models.Book is not available for borrowing.");
        }
    }

    public void returnBook() {
        isAvailable = true;
    }

    // Getters and Setters
}

class EBook extends Book {
    private String downloadLink;

    public EBook(int bookID,String title, String author, String ISBN, String downloadLink) {
        super(bookID, title, author, ISBN);
        this.downloadLink = downloadLink;
    }


}

class AudioBook extends Book {
    private String audioLink;

    public AudioBook(int bookID,String title, String author, String ISBN, String audioLink) {
        super(bookID, title, author, ISBN);
        this.audioLink = audioLink;
    }


}

