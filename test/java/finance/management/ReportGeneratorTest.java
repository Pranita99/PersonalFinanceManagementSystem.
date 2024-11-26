package finance.management;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import finance.management.FinanceManager;

import static org.junit.jupiter.api.Assertions.*;

public class ReportGeneratorTest {
    private ReportGenerator reportGenerator;
    private Income income;
    private Expense expense;

    @BeforeEach
    public void setUp() {
        reportGenerator = new ReportGenerator();
        income = new Income(2000.0, "Salary");
        expense = new Expense(500.0, "Groceries", "Food");
        reportGenerator.addTransaction(income);
        reportGenerator.addTransaction(expense);
    }

    @Test
    public void testGenerateMonthlyReport() {
        reportGenerator.generateMonthlyReport();
        // Capture System Output for Verification
    }

    @Test
    public void testGenerateCategoryReport() {
        reportGenerator.generateCategoryReport();
        // Capture System Output for Verification
    }

    @Test
    public void testEmptyTransactionReport() {
        ReportGenerator emptyReportGenerator = new ReportGenerator();
        emptyReportGenerator.generateMonthlyReport();
        // Capture System Output and check for empty report message.
    }

    @Test
    public void testMultipleTransactionsReport() {
        reportGenerator.addTransaction(new Income(500, "Bonus"));
        reportGenerator.addTransaction(new Expense(100, "Snacks", "Food"));
        reportGenerator.generateMonthlyReport();
        // Capture System Output and verify report accuracy.
    }

    // Additional Test Cases for Better Mutation Coverage

    @Test
    public void testNegativeIncomeTransaction() {
        Income negativeIncome = new Income(-1000.0, "Negative Salary");
        reportGenerator.addTransaction(negativeIncome);
        reportGenerator.generateMonthlyReport();
        // Verify that negative income is handled correctly.
    }

    @Test
    public void testBoundaryIncomeTransaction() {
        Income boundaryIncome = new Income(0.0, "Zero Salary");
        reportGenerator.addTransaction(boundaryIncome);
        reportGenerator.generateMonthlyReport();
        // Verify that zero income is handled correctly.
    }

    @Test
    public void testGenerateReportWithMultipleCategories() {
        reportGenerator.addTransaction(new Expense(200, "Electricity", "Utilities"));
        reportGenerator.addTransaction(new Expense(300, "Internet", "Utilities"));
        reportGenerator.addTransaction(new Expense(100, "Dining", "Food"));
        reportGenerator.generateCategoryReport();
        // Capture System Output for Verification of multiple categories.
    }

  @Test
    public void testUpdateTransactionBoundary() {
        Income newIncome = new Income(3000.0, "Freelance Work");
        reportGenerator.updateTransaction(income, newIncome);
        assertEquals(2, reportGenerator.getTransactionCount(), "Transaction count should remain the same after updating a transaction.");
        assertEquals(newIncome, reportGenerator.calculateTotalIncome() > income.getAmount() ? newIncome : income, "Income transaction should be updated correctly.");
    }

 // Arithmetic Operator Replacement (AOR) Test Cases
    @Test
    public void testCalculateSavingsAdditionToSubtraction() {
        // Adding Income and Expense, then change addition to subtraction
        double initialSavings = reportGenerator.calculateSavings();
        reportGenerator.addTransaction(new Income(500, "Bonus"));
        reportGenerator.addTransaction(new Expense(300, "Electricity", "Utilities"));
        double modifiedSavings = reportGenerator.calculateTotalIncome() - reportGenerator.calculateTotalExpenses();
        assertEquals(modifiedSavings, reportGenerator.calculateSavings(), "Savings calculation should be correct after modifying arithmetic operation.");
    }

    @Test
    public void testCalculateAverageExpenseMultiplicationToDivision() {
        // Test to cover multiplication or division changes in calculateAverageExpense
        double averageExpense = reportGenerator.calculateAverageExpense();
        assertTrue(averageExpense > 0, "Average expense should be greater than zero after valid entries.");
    }

    // Relational Operator Replacement (ROR) Test Cases
    @Test
    public void testBoundaryConditionForExpenses() {
        Expense highExpense = new Expense(Double.MAX_VALUE, "Luxury Purchase", "Luxury");
        reportGenerator.addTransaction(highExpense);
        Expense highestExpense = (Expense) reportGenerator.findHighestExpense();
        assertEquals(highExpense, highestExpense, "Highest expense should be correctly identified.");
    }

    @Test
    public void testTransactionCountEquality() {
        assertEquals(2, reportGenerator.getTransactionCount(), "Transaction count should match the expected value.");
        reportGenerator.removeTransaction(income);
        assertEquals(1, reportGenerator.getTransactionCount(), "Transaction count should be updated after removing a transaction.");
    }

    // Conditional Boundary Mutations (CBM) Test Cases
    @Test
    public void testClearTransactionsBoundary() {
        assertEquals(2, reportGenerator.getTransactionCount(), "Transaction count should be initially 2.");
        reportGenerator.clearTransactions();
        assertEquals(0, reportGenerator.getTransactionCount(), "Transaction count should be 0 after clearing transactions.");
    }

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

  

    // New Test Cases to Target Specific Mutation Operators

    // Arithmetic Operator Replacement (AOR)
    @Test
    public void testCalculateTotalIncomeSubtraction() {
        double totalIncome = reportGenerator.calculateTotalIncome();
        reportGenerator.addTransaction(new Income(-1000, "Negative Income"));
        assertEquals(totalIncome - 1000, reportGenerator.calculateTotalIncome(), "Total income should correctly reflect subtraction mutation.");
    }

    @Test
    public void testCalculateTotalExpensesMultiplication() {
        double totalExpenses = reportGenerator.calculateTotalExpenses();
        reportGenerator.addTransaction(new Expense(200, "Double Expense", "Misc"));
        double expectedExpenses = totalExpenses + 200;
        assertEquals(expectedExpenses, reportGenerator.calculateTotalExpenses(), "Total expenses should correctly handle multiplication mutation.");
    }

    // Relational Operator Replacement (ROR)
    @Test
    public void testIncomeComparisonGreaterThanOrEqual() {
        Income newIncome = new Income(3000.0, "Freelance");
        reportGenerator.addTransaction(newIncome);
        assertTrue(reportGenerator.calculateTotalIncome() >= 2000.0, "Total income should be greater than or equal to the original income.");
    }

    @Test
    public void testExpenseComparisonLessThan() {
        Expense smallExpense = new Expense(100.0, "Small Purchase", "Misc");
        reportGenerator.addTransaction(smallExpense);
        assertTrue(reportGenerator.calculateTotalExpenses() < 1000.0, "Total expenses should be less than 1000 after adding a small expense.");
    }

    // Conditional Boundary Mutations (CBM)
    @Test
    public void testBoundaryCheckForEmptyTransactions() {
        reportGenerator.clearTransactions();
        assertEquals(0, reportGenerator.getTransactionCount(), "Transaction count should be zero after clearing transactions.");
        reportGenerator.generateMonthlyReport();
        // Verify that monthly report handles empty state correctly.
    }

    @Test
    public void testBoundaryCheckForMaximumTransactions() {
        for (int i = 0; i < 100; i++) {
            reportGenerator.addTransaction(new Expense(10.0, "Expense " + i, "Misc"));
        }
        assertEquals(102, reportGenerator.getTransactionCount(), "Transaction count should reflect added transactions correctly.");
    }
}





