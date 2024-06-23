public class Main {
    public static void main(String[] args) {
        LibraryManagementSystem lms = new LibraryManagementSystem();

        Category fiction = new Category("Fiction", "CAT0001");
        Category science = new Category("Science", "CAT0002");

        lms.addCategory(fiction);
        lms.addCategory(science);

        Book book1 = new Book("Book Title 1", "Author 1", "ISBN0001", fiction);
        EBook ebook1 = new EBook("EBook Title 1", "Author 2", "ISBN0002", science, "downloadLink1");
        AudioBook audiobook1 = new AudioBook("AudioBook Title 1", "Author 3", "ISBN0003", fiction, "audioLink1");

        lms.addBook(book1);
        lms.addBook(ebook1);
        lms.addBook(audiobook1);

        Patron patron1 = new Patron("Patron 1", "ID0001");
        lms.addPatron(patron1);

        lms.borrowBook(book1, patron1);
        lms.borrowBook(ebook1, patron1);
        lms.borrowBook(audiobook1, patron1);

        lms.returnBook(book1, patron1);

        //CRUD

        BookDAO bookDAO = new BookDAO();

        // Create
        bookDAO.addBook(6, "Go and make it", "Ruth", "9780743273565", 50);

        // Read
        bookDAO.getBookById(1);

         //Update
//        bookDAO.updateBook(1, "The Great Gatsby", "F. Scott Fitzgerald", 1925, "9780743273565");
//
//        // Delete
//        bookDAO.deleteBook(1);
    }
}

