import java.util.Date;

class Transaction {
    private Book book;
    private Patron patron;
    private Date transactionDate;
    private String type; // "borrow" or "return"

    public Transaction(Book book, Patron patron, Date transactionDate, String type) {
        this.book = book;
        this.patron = patron;
        this.transactionDate = transactionDate;
        this.type = type;
    }

    // Getters and Setters
}
