package com.LibraryManagement.LibraryManagementWithTest.models;

import java.sql.Date;

public class Transaction {
    private int transactionId;
    private int bookId;
    private String fullName;
    private String borrowDate;
    private String dueDate;
    private String returnDate;
    private String status;


    private String transactionType;


    public Transaction(String transactionType) {
        this.transactionType = transactionType;
    }

    public Transaction(int i, int bookId, int i1, String borrow, Date date) {
        this.transactionId = i;
        this.bookId = bookId;
        this.borrowDate = date.toString();
        this.dueDate = date.toString();
        this.returnDate = date.toString();
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public Transaction(int transactionId, int bookId, String fullName, String borrowDate, String dueDate, String returnDate, String status) {
        this.transactionId = transactionId;
        this.bookId = bookId;
        this.fullName = fullName;
        this.borrowDate = borrowDate;
        this.dueDate = dueDate;
        this.returnDate = returnDate;
        this.status = status;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(String borrowDate) {
        this.borrowDate = borrowDate;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId=" + transactionId +
                ", bookId=" + bookId +
                ", fullName='" + fullName + '\'' +
                ", borrowDate='" + borrowDate + '\'' +
                ", dueDate='" + dueDate + '\'' +
                ", returnDate='" + returnDate + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
