class Book {
    private String title;
    private String author;
    private String ISBN;
    private boolean isAvailable;
    private Category category;

    public Book(String title, String author, String ISBN, Category category) {
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.isAvailable = true;
        this.category = category;
    }

    public void borrowBook() {
        if (isAvailable) {
            isAvailable = false;
        } else {
            System.out.println("Book is not available for borrowing.");
        }
    }

    public void returnBook() {
        isAvailable = true;
    }

    // Getters and Setters
}

class EBook extends Book {
    private String downloadLink;

    public EBook(String title, String author, String ISBN, Category category, String downloadLink) {
        super(title, author, ISBN, category);
        this.downloadLink = downloadLink;
    }


}

class AudioBook extends Book {
    private String audioLink;

    public AudioBook(String title, String author, String ISBN, Category category, String audioLink) {
        super(title, author, ISBN, category);
        this.audioLink = audioLink;
    }


}

