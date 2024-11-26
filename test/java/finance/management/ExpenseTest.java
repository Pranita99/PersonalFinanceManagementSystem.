package finance.management;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import finance.management.ReportGenerator;

import static org.junit.jupiter.api.Assertions.*;

public class ExpenseTest {
    private Expense expense;
    private ReportGenerator reportGenerator;

    @BeforeEach
    public void setUp() {
        expense = new Expense(500.0, "Groceries", "Food");
        reportGenerator = new ReportGenerator();
        
    }

    @Test
    public void testGetCategory() {
        assertEquals("Food", expense.getCategory(), "Category should be 'Food'");
    }

    @Test
    public void testUpdateExpense() {
        expense.updateExpense(300.0, "Snacks");
        assertEquals(300.0, expense.getAmount(), "Amount should be updated to 300.0");
        assertEquals("Snacks", expense.getDescription(), "Description should be updated to 'Snacks'");
    }
    
    @Test
    public void testHighExpenseAmount() {
        Expense highExpense = new Expense(Double.MAX_VALUE, "High Value", "Misc");
        assertEquals(Double.MAX_VALUE, highExpense.getAmount(), "High expense value should be handled correctly.");
    }

    @Test
    public void testNegativeExpenseAmount() {
        Expense negativeExpense = new Expense(-100.0, "Negative Value", "Misc");
        assertEquals(-100.0, negativeExpense.getAmount(), "Negative expense value should be allowed.");
    }
    
    @Test
    public void testUpdateExpenseWithDifferentOperations() {
        // Update expense amount using different arithmetic operations
        expense.updateExpense(500.0 + 100.0, "Increased Snacks");
        assertEquals(600.0, expense.getAmount(), "Amount should be updated with addition.");
        assertEquals("Increased Snacks", expense.getDescription(), "Description should be updated.");

        expense.updateExpense(300.0 * 2, "Doubled Expense");
        assertEquals(600.0, expense.getAmount(), "Amount should be updated using multiplication.");
        assertEquals("Doubled Expense", expense.getDescription(), "Description should be updated.");
    }
    
    @Test
    public void testBoundaryExpenseAmount() {
        Expense boundaryExpense = new Expense(0.01, "Small Expense", "Misc");
        assertEquals(0.01, boundaryExpense.getAmount(), "Expense amount should be set correctly for a small positive value.");
        assertEquals("Misc", boundaryExpense.getCategory(), "Expense category should be set correctly.");
    }

    @Test
    public void testNegativeBoundaryExpense() {
        Expense negativeBoundary = new Expense(-0.01, "Negative Boundary", "Misc");
        assertEquals(-0.01, negativeBoundary.getAmount(), "Negative boundary expense should be allowed.");
    }
    
    @Test
    public void testHighBoundaryExpense() {
        Expense highExpense = new Expense(Double.MAX_VALUE, "High Value Expense", "Luxury");
        assertEquals(Double.MAX_VALUE, highExpense.getAmount(), "Expense amount should handle maximum double value.");
    }
    
    @Test
    public void testCategoryReplacement() {
        expense = new Expense(300.0, "Utilities", "Electricity");
        assertEquals("Electricity", expense.getCategory(), "Initial category should be 'Electricity'.");

        // Replace the category parameter and assert
        expense = new Expense(300.0, "Utilities", "Water");
        assertEquals("Water", expense.getCategory(), "Category should be replaced with 'Water'.");
    }
    
    @Test
    public void testMethodCallReplacement() {
        assertEquals("Groceries", expense.getDescription(), "Description should be 'Groceries'.");
        
        // Replace with category method to see if it behaves correctly
        assertEquals("Food", expense.getCategory(), "Category should be 'Food'.");
    }
    
    @Test
    public void testReturnValueMutation() {
        expense.updateExpense(200.0, "New Snacks");
        assertEquals(200.0, expense.getAmount(), "Expense amount should be updated correctly.");

        // Mutate the return value of getAmount() (simulate it to return zero)
        double mutatedValue = 0.0;
        assertNotEquals(mutatedValue, expense.getAmount(), "Expense amount should not return zero after valid update.");
    }
    
    @Test
    public void testUpdateExpenseToZero() {
        expense.updateExpense(0.0, "Zero Expense");
        assertEquals(0.0, expense.getAmount(), "Expense amount should be updated to zero.");
        assertEquals("Zero Expense", expense.getDescription(), "Description should be updated to 'Zero Expense'.");
    }

    @Test
    public void testUpdateExpenseToNegativeValue() {
        expense.updateExpense(-200.0, "Negative Update");
        assertEquals(-200.0, expense.getAmount(), "Expense amount should be updated to negative value.");
        assertEquals("Negative Update", expense.getDescription(), "Description should be updated to 'Negative Update'.");
    }

    @Test
    public void testInvalidCategoryInput() {
        Expense invalidCategoryExpense = new Expense(200.0, "Invalid Category", null);
        assertNull(invalidCategoryExpense.getCategory(), "Category should be null if invalid.");
    }

    @Test
    public void testCategoryWithSpecialCharacters() {
        Expense specialCategoryExpense = new Expense(200.0, "Special Category", "@Groceries!#");
        assertEquals("@Groceries!#", specialCategoryExpense.getCategory(), "Category should handle special characters.");
    }

    @Test
    public void testLongDescriptionUpdate() {
        String longDescription = "This is a very long description to test if the updateExpense method can handle long descriptions properly";
        expense.updateExpense(150.0, longDescription);
        assertEquals(longDescription, expense.getDescription(), "Description should be updated to the long value.");
    }

    @Test
    public void testDecimalAmountUpdate() {
        expense.updateExpense(123.456, "Decimal Update");
        assertEquals(123.456, expense.getAmount(), "Expense amount should handle decimal values correctly.");
        assertEquals("Decimal Update", expense.getDescription(), "Description should be updated to 'Decimal Update'.");
    }
    
    @Test
    public void testCalculateSavingsAdditionToSubtraction() {
        // Adding Income and Expense, then change addition to subtraction
        double initialSavings = reportGenerator.calculateSavings();
        reportGenerator.addTransaction(new Income(500, "Bonus"));
        reportGenerator.addTransaction(new Expense(300, "Electricity", "Utilities"));
        double modifiedSavings = reportGenerator.calculateTotalIncome() - reportGenerator.calculateTotalExpenses();
        assertEquals(modifiedSavings, reportGenerator.calculateSavings(), "Savings calculation should be correct after modifying arithmetic operation.");
    }

    
    // Relational Operator Replacement (ROR) Test Cases
    @Test
    public void testBoundaryConditionForExpenses() {
        Expense highExpense = new Expense(Double.MAX_VALUE, "Luxury Purchase", "Luxury");
        reportGenerator.addTransaction(highExpense);
        Expense highestExpense = (Expense) reportGenerator.findHighestExpense();
        assertEquals(highExpense, highestExpense, "Highest expense should be correctly identified.");
    }

   
    // Conditional Boundary Mutations (CBM) Test Cases
   
  

    @Test
    public void testMonthlyReportWithNegativeExpense() {
        reportGenerator.addTransaction(new Expense(-200, "Refund", "Misc"));
        reportGenerator.generateMonthlyReport();
        // Verify that negative expense is handled correctly and deducted from total expenses.
    }

    @Test
    public void testHighIncomeTransaction() {
        Income highIncome = new Income(Double.MAX_VALUE, "High Salary");
        reportGenerator.addTransaction(highIncome);
        reportGenerator.generateMonthlyReport();
        // Verify that high income is handled correctly.
    }

    @Test
    public void testBoundaryExpenseTransaction() {
        Expense boundaryExpense = new Expense(0.0, "Free Lunch", "Food");
        reportGenerator.addTransaction(boundaryExpense);
        reportGenerator.generateCategoryReport();
        // Verify that zero expense is handled correctly.
    }

    @Test
    public void testGenerateReportWithMultipleCategories() {
        reportGenerator.addTransaction(new Expense(200, "Electricity", "Utilities"));
        reportGenerator.addTransaction(new Expense(300, "Internet", "Utilities"));
        reportGenerator.addTransaction(new Expense(100, "Dining", "Food"));
        reportGenerator.generateCategoryReport();
        // Capture System Output for Verification of multiple categories.
    }

   
   

   

  
}




