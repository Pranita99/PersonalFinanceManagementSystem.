package finance.management;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class FinanceManagerTest {
    
    private FinanceManager financeManager;

    @BeforeEach
    public void setUp() {
        financeManager = new FinanceManager(1000); // Set monthly budget
    }

    @Test
    public void testAddIncome() {
        financeManager.addIncome(3000, "Salary");
        financeManager.showMonthlyReport();
        assertEquals(3000, financeManager.getTotalIncome(), "The total income should be 3000.");
    }

    @Test
    public void testAddExpense() {
        financeManager.addExpense(500, "Rent", "Housing");
        financeManager.showCategoryReport();
        assertEquals(500, financeManager.getTotalExpenses(), "The total expenses should be 500.");
    }

    @Test
    public void testAddSavingGoal() {
        financeManager.addSavingGoal("Vacation Fund", 1000);
        financeManager.addSavingsToGoal("Vacation Fund", 200);
        financeManager.showSavingGoalsProgress();
        assertEquals(200, financeManager.getSavedAmountForGoal("Vacation Fund"), "Saved amount should be 200.");
    }

    @Test
    public void testBudgetExceeded() {
        financeManager.addExpense(1200, "New Phone", "Gadget");
        assertTrue(financeManager.isBudgetExceeded(), "Budget should be exceeded after adding expense of 1200.");
    }

    @Test
    public void testRemainingBudget() {
        financeManager.addExpense(400, "Groceries", "Food");
        assertEquals(600, financeManager.getRemainingBudget(), "Remaining budget should be 600.");
    }
    
    @Test
    public void testRemoveIncome() {
        financeManager.addIncome(500, "Bonus");
        financeManager.removeIncome("Bonus");
        assertEquals(0, financeManager.getTotalIncome(), "The income should be removed, so total income should be 0.");
    }

    @Test
    public void testRemoveExpense() {
        financeManager.addExpense(300, "Internet Bill", "Utilities");
        financeManager.removeExpense("Internet Bill");
        assertEquals(0, financeManager.getTotalExpenses(), "The expense should be removed, so total expenses should be 0.");
    }

    @Test
    public void testModifySavingGoal() {
        financeManager.addSavingGoal("Car", 2000);
        financeManager.modifySavingGoal("Car", 2500);
        assertEquals(2500, financeManager.getSavingGoal("Car").getTargetAmount(), "The target amount for the 'Car' saving goal should be updated to 2500.");
    }

    @Test
    public void testSavingGoalCompletion() {
        financeManager.addSavingGoal("Vacation", 1000);
        financeManager.addSavingsToGoal("Vacation", 1000);
        assertTrue(financeManager.getSavedAmountForGoal("Vacation") >= 1000, "The saving goal should be completed.");
    }

    @Test
    public void testBudgetWarnings() {
        financeManager.addExpense(800, "Rent", "Housing");
        assertEquals(200, financeManager.getRemainingBudget(), "Remaining budget should be 200 after adding an 800 expense on a 1000 budget.");
    }

    @Test
    public void testAddZeroIncome() {
        financeManager.addIncome(0, "Zero Income");
        assertEquals(0, financeManager.getTotalIncome(), "Adding zero income should keep total income at 0.");
    }

    @Test
    public void testNegativeIncomeNotAllowed() {
        financeManager.addIncome(-100, "Negative Income");
        assertEquals(0, financeManager.getTotalIncome(), "Negative income should not be allowed.");
    }
    
    @Test
    public void testUpdateBudget() {
        financeManager.getBudgetManager().updateBudget(2000);
        assertEquals(2000, financeManager.getBudgetManager().getRemainingBudget(), "Budget should be updated to 2000.");
    }
    
    @Test
    public void testAddIncomeNegative() {
        financeManager.addIncome(-1000, "Invalid Income");
        assertEquals(0, financeManager.getTotalIncome(), "Income should not be added if it's negative");
    }

    @Test
    public void testAddExpenseNegative() {
        financeManager.addExpense(-500, "Invalid Expense", "Misc");
        assertEquals(0, financeManager.getTotalExpenses(), "Expense should not be added if it's negative");
    }

    @Test
    public void testAddSavingsToNonexistentGoal() {
        financeManager.addSavingsToGoal("Nonexistent Goal", 200.0);
        assertEquals(0, financeManager.getSavedAmountForGoal("Nonexistent Goal"), "Should be 0 for non-existent saving goal");
    }

    @Test
    public void testIntegrationIncomeAndExpenses() {
        financeManager.getBudgetManager().updateBudget(2000); // Set the budget to the correct value
        System.out.println("Budget after update: " + financeManager.getBudgetManager().getRemainingBudget()); // Debugging print statement
        
        financeManager.addIncome(2000, "Salary");
        financeManager.addExpense(500, "Groceries", "Food");
        
        assertEquals(2000, financeManager.getTotalIncome(), "Total income should be 2000");
        assertEquals(500, financeManager.getTotalExpenses(), "Total expenses should be 500");
        assertEquals(1500, financeManager.getRemainingBudget(), "Remaining budget should be 1500"); // This line is failing due to unexpected budget value
    }
    
   @Test
    public void testParameterReplacement() {
        financeManager.addIncome(1000, "Salary");
        financeManager.addExpense(500, "Rent", "Housing");
        assertEquals("Rent", financeManager.getExpenses().get(0).getDescription());
    }
   
   @Test
   public void testModifySavingGoalBoundary() {
       financeManager.addSavingGoal("Car Fund", 5000);
       financeManager.modifySavingGoal("Car Fund", 4999.99);
       assertEquals(4999.99, financeManager.getSavingGoal("Car Fund").getTargetAmount(), "Boundary mutation should still result in correct goal target update");
   }

   // Test Case for Return Value Mutations (RVM)
   @Test
   public void testReturnValueMutationOnSavingGoal() {
       financeManager.addSavingGoal("Holiday Fund", 1000);
       SavingGoal goal = financeManager.getSavingGoal("Holiday Fund");
       assertNotNull(goal, "Goal should not be null when correctly added");
   }

   
   

   // Test Case for Integration Level Mutation: Method Call Replacement (MCR)
   @Test
   public void testModifySavingGoalMethodReplacement() {
       financeManager.addSavingGoal("Car", 3000);
       financeManager.modifySavingGoal("Car", 3500);
       SavingGoal goal = financeManager.getSavingGoal("Car");
       assertEquals(3500.0, goal.getTargetAmount(), "The goal should be modified correctly");
       
       financeManager.removeSavingGoal("Car");
       assertNull(financeManager.getSavingGoal("Car"), "Goal should be null after being removed");
   }

   // Test Case for Integration Level Mutation: Return Value Mutations (RVM)
   @Test
   public void testGetExpensesReturnValue() {
       financeManager.addExpense(300, "Internet", "Utilities");
       List<Expense> expenses = financeManager.getExpenses();
       assertFalse(expenses.isEmpty(), "Expenses should not be empty when there are items in it");
   }
   
   @Test
   public void testAddIncomeBoundary() {
       // Boundary test for zero income
       financeManager.addIncome(0, "Zero Salary");
       assertEquals(0, financeManager.getTotalIncome(), "Adding zero income should not affect the total income.");
       
       // Negative income should not be allowed
       financeManager.addIncome(-500, "Negative Income");
       assertEquals(0, financeManager.getTotalIncome(), "Negative income should not be added.");
   }

   @Test
   public void testAddExpenseBoundary() {
       // Adding zero expense
       financeManager.addExpense(0, "No Expense", "Misc");
       assertEquals(0, financeManager.getTotalExpenses(), "Adding zero expense should not affect the total expenses.");
       
       // Adding negative expense should not be allowed
       financeManager.addExpense(-100, "Negative Expense", "Misc");
       assertEquals(0, financeManager.getTotalExpenses(), "Negative expense should not be added.");
   }
   
  // Test for Relational Operator Replacement (ROR)
   @Test
   public void testRelationalOperatorReplacementInBudgetExceeded() {
       financeManager.addExpense(1500, "Rent", "Housing");
       assertTrue(financeManager.isBudgetExceeded(), "Budget should be exceeded with expense greater than budget.");
   }

   // Test for Conditional Boundary Mutations (CBM)
   @Test
   public void testConditionalBoundaryMutationForSavings() {
       financeManager.addSavingGoal("Vacation", 1000);
       financeManager.addSavingsToGoal("Vacation", 999.9);
       assertFalse(financeManager.getSavingGoal("Vacation").isGoalReached(), "Savings goal should not be reached with 999.9 saved.");
       financeManager.addSavingsToGoal("Vacation", 0.1);
       assertTrue(financeManager.getSavingGoal("Vacation").isGoalReached(), "Savings goal should be reached exactly at boundary.");
   }

   // Integration Level Test - Parameter Replacement (PR)
   @Test
   public void testParameterReplacementInAddIncome() {
       financeManager.addIncome(1000, "Salary");
       financeManager.addExpense(500, "Rent", "Housing");
       assertEquals("Rent", financeManager.getExpenses().get(0).getDescription(), "Parameter replacement should reflect in expense description.");
   }

   // Integration Level Test - Method Call Replacement (MCR)
   @Test
   public void testMethodCallReplacementInModifyIncome() {
       Income income = new Income(5000, "Job");
       financeManager.modifyIncome(income, true, 1000);
       assertEquals(6000, income.getAmount(), "Income should be increased correctly using modifyIncome.");
   }

   

    
   public void modifyIncome(Income income, boolean increase, double value) {
       if (increase) {
           income.increaseIncome(value);
       } else {
           income.updateIncome(value);
       }
   }
   
   
   }


