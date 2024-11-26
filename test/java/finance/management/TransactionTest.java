package finance.management;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TransactionTest {
    private Transaction transaction;

    @BeforeEach
    public void setUp() {
        // Set up an initial transaction for testing
        transaction = new Transaction(1500.0, "Freelance Payment", "Income");
    }

    @Test
    public void testTransactionCreation() {
        Transaction transaction = new Transaction(1500.0, "Freelance Payment", "Income");
        assertEquals(1500.0, transaction.getAmount(), "Transaction amount should be set correctly.");
        assertEquals("Freelance Payment", transaction.getDescription(), "Transaction description should be set correctly.");
        assertEquals("Income", transaction.getCategory(), "Transaction category should be set correctly.");
    }
    
    @Test
    public void testNegativeTransactionAmount() {
        Transaction negativeTransaction = new Transaction(-300.0, "Negative Transaction", "Misc");
        assertEquals(-300.0, negativeTransaction.getAmount(), "Transaction amount should be allowed to be negative.");
        assertEquals("Negative Transaction", negativeTransaction.getDescription(), "Transaction description should be set correctly.");
        assertEquals("Misc", negativeTransaction.getCategory(), "Transaction category should be set correctly.");
    }

    @Test
    public void testZeroTransactionAmount() {
        Transaction zeroTransaction = new Transaction(0.0, "Zero Transaction", "Misc");
        assertEquals(0.0, zeroTransaction.getAmount(), "Transaction amount should be set correctly for zero value.");
        assertEquals("Zero Transaction", zeroTransaction.getDescription(), "Transaction description should be set correctly.");
        assertEquals("Misc", zeroTransaction.getCategory(), "Transaction category should be set correctly.");
    }

    @Test
    public void testEmptyDescription() {
        Transaction emptyDescriptionTransaction = new Transaction(800.0, "", "Misc");
        assertEquals(800.0, emptyDescriptionTransaction.getAmount(), "Transaction amount should be set correctly.");
        assertEquals("", emptyDescriptionTransaction.getDescription(), "Transaction description can be empty.");
        assertEquals("Misc", emptyDescriptionTransaction.getCategory(), "Transaction category should be set correctly.");
    }

    @Test
    public void testMultiCategoryTransaction() {
        Transaction multiCategoryTransaction = new Transaction(500.0, "Online Shopping", "Misc");
        assertEquals(500.0, multiCategoryTransaction.getAmount(), "Transaction amount should be set correctly.");
        assertEquals("Online Shopping", multiCategoryTransaction.getDescription(), "Transaction description should be set correctly.");
        assertEquals("Misc", multiCategoryTransaction.getCategory(), "Transaction should handle multiple categories correctly.");
    }
    
    @Test
    public void testHighValueTransaction() {
        Transaction highValueTransaction = new Transaction(1_000_000.0, "High Value Purchase", "Luxury");
        assertEquals(1_000_000.0, highValueTransaction.getAmount(), "High value transaction amount should be set correctly.");
        assertEquals("High Value Purchase", highValueTransaction.getDescription(), "Transaction description should be set correctly.");
        assertEquals("Luxury", highValueTransaction.getCategory(), "Transaction category should be set correctly.");
    }

    @Test
    public void testNegativeDescriptionTransaction() {
        Transaction negativeDescriptionTransaction = new Transaction(200.0, "Negative Value", "Refund");
        assertEquals(200.0, negativeDescriptionTransaction.getAmount(), "Transaction amount should be set correctly.");
        assertEquals("Negative Value", negativeDescriptionTransaction.getDescription(), "Transaction description should handle negative terminology correctly.");
        assertEquals("Refund", negativeDescriptionTransaction.getCategory(), "Transaction category should be set correctly.");
    }

    @Test
    public void testTransactionWithSpecialCharacters() {
        Transaction specialCharTransaction = new Transaction(100.0, "Purchase at Store #123!", "Groceries");
        assertEquals(100.0, specialCharTransaction.getAmount(), "Transaction amount should be set correctly.");
        assertEquals("Purchase at Store #123!", specialCharTransaction.getDescription(), "Transaction description should handle special characters correctly.");
        assertEquals("Groceries", specialCharTransaction.getCategory(), "Transaction category should be set correctly.");
    }

    @Test
    public void testBoundaryValueTransaction() {
        Transaction boundaryTransaction = new Transaction(0.01, "Minimal Transaction", "Misc");
        assertEquals(0.01, boundaryTransaction.getAmount(), "Transaction amount should be set correctly for minimal non-zero value.");
        assertEquals("Minimal Transaction", boundaryTransaction.getDescription(), "Transaction description should be set correctly.");
        assertEquals("Misc", boundaryTransaction.getCategory(), "Transaction category should be set correctly.");
    }

    @Test
    public void testEmptyCategoryTransaction() {
        Transaction emptyCategoryTransaction = new Transaction(300.0, "Payment without category", "");
        assertEquals(300.0, emptyCategoryTransaction.getAmount(), "Transaction amount should be set correctly.");
        assertEquals("Payment without category", emptyCategoryTransaction.getDescription(), "Transaction description should be set correctly.");
        assertEquals("", emptyCategoryTransaction.getCategory(), "Transaction category can be empty.");
    }
    
    @Test
    public void testArithmeticCalculationInTransaction() {
        Transaction incomeTransaction = new Transaction(1500.0, "Bonus Income", "Income");
        assertEquals(1500.0 * 2, incomeTransaction.getAmount() * 2, "Multiplication should be calculated correctly.");
        
        assertEquals(1500.0 + 500, incomeTransaction.getAmount() + 500, "Addition should be calculated correctly.");
        
        assertEquals(1500.0 - 300, incomeTransaction.getAmount() - 300, "Subtraction should be calculated correctly.");

        assertEquals(1500.0 / 3, incomeTransaction.getAmount() / 3, "Division should be calculated correctly.");
    }
    
    @Test
    public void testRelationalOperatorForTransaction() {
        Transaction smallTransaction = new Transaction(100.0, "Grocery Purchase", "Groceries");
        Transaction largeTransaction = new Transaction(5000.0, "Laptop Purchase", "Electronics");

        assertTrue(largeTransaction.getAmount() > smallTransaction.getAmount(), "Amount of larger transaction should be greater.");
        
        assertFalse(smallTransaction.getAmount() > largeTransaction.getAmount(), "Amount of smaller transaction should not be greater.");

        assertTrue(largeTransaction.getAmount() >= 5000.0, "Transaction amount should be greater than or equal to 5000.");

        assertFalse(smallTransaction.getAmount() >= 200.0, "Small transaction should not be greater than or equal to 200.");
    }
    
    @Test
    public void testBoundaryTransactionAmount() {
        Transaction minimalTransaction = new Transaction(0.01, "Minimal Payment", "Misc");
        assertEquals(0.01, minimalTransaction.getAmount(), "Minimal non-zero value should be allowed.");

        Transaction zeroTransaction = new Transaction(0.0, "Zero Transaction", "Misc");
        assertEquals(0.0, zeroTransaction.getAmount(), "Transaction with zero amount should be allowed.");
    }
}



 
