package finance.management;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BudgetManagerTest {
    private BudgetManager budgetManager;

    @BeforeEach
    public void setUp() {
        budgetManager = new BudgetManager(1000.0);
    }

    @Test
    public void testAddExpense() {
        budgetManager.addExpense(200.0);
        assertEquals(800.0, budgetManager.getRemainingBudget(), "Remaining budget should be 800.0");
    }

    @Test
    public void testIsBudgetExceeded() {
        budgetManager.addExpense(1200.0);
        assertTrue(budgetManager.isBudgetExceeded(), "Budget should be exceeded with 1200.0 expenses");
    }

    @Test
    public void testNotifyUser() {
        budgetManager.addExpense(850.0);
        
    }
    
    @Test
    public void testExactBudgetLimit() {
        budgetManager.addExpense(budgetManager.getMonthlyBudget() * 0.8);
        assertEquals("Warning: You are nearing your budget limit!", budgetManager.getWarningMessage());
    }

    @Test
    public void testExceedBudget() {
        budgetManager.addExpense(budgetManager.getMonthlyBudget() + 1);
        assertTrue(budgetManager.isBudgetExceeded());
    }
    
    @Test
    public void testAddNegativeExpense() {
        budgetManager.addExpense(-500.0);
        assertEquals(1000.0, budgetManager.getRemainingBudget(), "Negative expense should not change the budget");
    }

    @Test
    public void testAddIncome() {
        budgetManager.addIncome(500.0);
        assertEquals(1500.0, budgetManager.getMonthlyBudget(), "Budget should be increased by 500.0");
    }

    @Test
    public void testBoundaryNotifyUser() {
        budgetManager.addExpense(799.99); // Below the 80% threshold
        assertNull(budgetManager.getWarningMessage(), "Warning should not be triggered just below the 80% boundary");
    }

    @Test
    public void testBoundaryNotifyUserExact() {
        budgetManager.addExpense(800.0);
        assertEquals("Warning: You are nearing your budget limit!", budgetManager.getWarningMessage(), "Warning should be triggered exactly at 80% of budget");
    }

    @Test
    public void testUpdateBudgetNegativeValue() {
        budgetManager.updateBudget(-1000.0);
        assertEquals(-1000.0, budgetManager.getMonthlyBudget(), "Budget should be updated with negative value");
    }

    @Test
    public void testGetRemainingBudgetAfterMultipleExpenses() {
        budgetManager.addExpense(200.0);
        budgetManager.addExpense(300.0);
        assertEquals(500.0, budgetManager.getRemainingBudget(), "Remaining budget should be 500.0 after multiple expenses");
    }

    @Test
    public void testMethodCallReplacement() {
        budgetManager.updateBudget(2000.0);
        assertEquals(2000.0, budgetManager.getMonthlyBudget(), "Budget should be updated to 2000.0");
    }

    @Test
    public void testReturnValueMutation() {
        budgetManager.updateBudget(2000.0);
        double remaining = budgetManager.getRemainingBudget();
        assertEquals(2000.0, remaining, "Remaining budget should be correct after budget update");
    }
    
    
    
    @Test
    public void testIsBudgetExceededWhenFalse() {
        budgetManager.addExpense(500.0);
        assertFalse(budgetManager.isBudgetExceeded(), "Budget should not be exceeded with expenses below the budget.");
    }

    @Test
    public void testIsBudgetExceededWhenTrue() {
        budgetManager.addExpense(1200.0);
        assertTrue(budgetManager.isBudgetExceeded(), "Budget should be exceeded with expenses above the budget.");
    }
    
  

    @Test
    public void testAddExpenseWithBoundaryValues() {
        budgetManager.addExpense(0.0);
        assertEquals(1000.0, budgetManager.getRemainingBudget(), "Remaining budget should remain unchanged when adding zero expense.");

        budgetManager.addExpense(1000.0);
        assertEquals(0.0, budgetManager.getRemainingBudget(), "Remaining budget should be zero when adding the entire budget as expense.");
    }
}

