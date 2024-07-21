package com.LibraryManagement.LibraryManagementWithTest;

import com.LibraryManagement.LibraryManagementWithTest.dao.TransactionDAO;
import com.LibraryManagement.LibraryManagementWithTest.models.Transaction;
import org.junit.jupiter.api.*;

import java.sql.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TransactionDAOTest {
    private TransactionDAO transactionDAO;

    @BeforeAll
    public void setUp() {
        transactionDAO = new TransactionDAO();
    }

    @Test
    public void testInsertTransaction() {


        Transaction transaction = new Transaction(33, 1, "John Doe", "2024-07-01", "2024-08-01", "2024-07-15", "BORROWED");
        transactionDAO.insert(transaction);
        Transaction retrievedTransaction = transactionDAO.getTransactionById(transaction.getTransactionId());
        assertNotNull(retrievedTransaction);
        assertEquals("John Doe", retrievedTransaction.getFullName());
    }

    @Test
    public void testGetAllTransactions() {
        List<Transaction> transactions = transactionDAO.getAllTransactions();
        assertNotNull(transactions);
        assertTrue(transactions.size() > 0);
    }

    @Test
    public void testUpdateTransaction() {
        Transaction transaction = new Transaction(31, 1, "Jane Doe", "2024-06-01", "2024-07-01", "2024-06-20", "BORROWED");
        transactionDAO.insert(transaction);
        transaction.setStatus("RETURNED");
        transactionDAO.updateTransaction(transaction);
        Transaction updatedTransaction = transactionDAO.getTransactionById(transaction.getTransactionId());
        assertEquals("RETURNED", updatedTransaction.getStatus());
    }

    @Test
    public void testDeleteTransaction() {
        Transaction transaction = new Transaction(0, 3, "Test User", "2024-05-01", "2024-06-01", "2024-05-15", "BORROWED");
        transactionDAO.insert(transaction);
        transactionDAO.deleteTransaction(transaction.getTransactionId());
        Transaction deletedTransaction = transactionDAO.getTransactionById(transaction.getTransactionId());
        assertNull(deletedTransaction);
    }
}
