package finance.management;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class IncomeTest {
    private Income income;

    @BeforeEach
    public void setUp() {
        // Set up an initial valid income for testing
        income = new Income(1000.0, "Initial Salary");
    }

    @Test
    public void testIncomeCreation() {
        assertEquals(1000.0, income.getAmount(), "Income amount should be set correctly.");
        assertEquals("Initial Salary", income.getDescription(), "Income description should be set correctly.");
    }

    @Test
    public void testNegativeIncome() {
        Income negativeIncome = new Income(-500.0, "Negative Income");
        assertEquals(-500.0, negativeIncome.getAmount(), "Income amount should allow negative values.");
    }

    @Test
    public void testZeroIncome() {
        Income zeroIncome = new Income(0.0, "Zero Income"); // Automatically uses "Income" as category
        assertEquals(0.0, zeroIncome.getAmount(), "Income amount should be set correctly for zero value.");
    }

    @Test
    public void testMaxIncomeValue() {
        double maxIncome = Double.MAX_VALUE;
        Income highIncome = new Income(maxIncome, "Max Income");
        assertEquals(maxIncome, highIncome.getAmount(), "Maximum income value should be handled correctly.");
    }

    @Test
    public void testZeroIncomeAmount() {
        Income zeroIncome = new Income(0.0, "No Payment");
        assertEquals(0.0, zeroIncome.getAmount(), "Zero income should be correctly set.");
    }

    @Test
    public void testIncomeWithMaxValue() {
        Income highIncome = new Income(Double.MAX_VALUE, "Max Income");
        assertEquals(Double.MAX_VALUE, highIncome.getAmount(), "Income with maximum value should be handled correctly.");
    }
    
    @Test
    public void testBoundaryIncomeValue() {
        Income smallIncome = new Income(0.01, "Income");
        assertEquals(0.01, smallIncome.getAmount(), "Income amount should be set correctly for a small positive value.");
    }
    
    @Test
    public void testUpdateIncomeAndDescription() {
        Income income = new Income(1000.0, "Initial Payment");
        
        // Update amount
        income.updateIncome(1200.0);
        assertEquals(1200.0, income.getAmount(), "Income amount should be updated correctly.");

        // Update description
        income.updateDescription("Updated Payment");
        assertEquals("Updated Payment", income.getDescription(), "Income description should be updated correctly.");
    }
    
    @Test
    public void testUpdateDescriptionToNull() {
        income.updateDescription(null);
        assertNull(income.getDescription(), "Income description should be updated to null.");
    }
    
    @Test
    public void testUpdateIncomeWithSmallPositiveValue() {
        income.updateIncome(0.01);
        assertEquals(0.01, income.getAmount(), "Income amount should be updated correctly for a small positive value.");
    }
    @Test
    public void testUpdateDescriptionToEmptyString() {
        income.updateDescription("");
        assertEquals("", income.getDescription(), "Income description should be updated to an empty string.");
    }
    
    @Test
    public void testUpdateDescriptionWithSpecialCharacters() {
        income.updateDescription("@!#$%^&*()");
        assertEquals("@!#$%^&*()", income.getDescription(), "Income description should accept special characters.");
    }
    
    @Test
    public void testRepeatedUpdates() {
        for (int i = 1; i <= 10; i++) {
            double newAmount = 100.0 * i;
            String newDescription = "Updated Payment " + i;

            income.updateIncome(newAmount);
            assertEquals(newAmount, income.getAmount(), "Income amount should be updated correctly on iteration " + i);

            income.updateDescription(newDescription);
            assertEquals(newDescription, income.getDescription(), "Income description should be updated correctly on iteration " + i);
        }
    }
    
    @Test
    public void testConstructorWithNullDescription() {
        Income incomeWithNullDescription = new Income(1000.0, null);
        assertEquals(1000.0, incomeWithNullDescription.getAmount(), "Income amount should be set correctly.");
        assertNull(incomeWithNullDescription.getDescription(), "Income description should be null.");
    }
    
    @Test
    public void testIncreaseIncome() {
        income.increaseIncome(500.0);
        assertEquals(1500.0, income.getAmount(), "Income should be increased by the specified amount.");
    }
    
 
    @Test
    public void testIsHighIncomeBoundary() {
        assertTrue(income.isHighIncome(1000.0), "Income should be equal to the boundary value for high income.");
    }

    @Test
    public void testIsHighIncomeAboveBoundary() {
        assertTrue(income.isHighIncome(1001.0), "Income slightly above the boundary should be considered high income.");
    }

    @Test
    public void testIsNotHighIncomeBelowBoundary() {
        assertFalse(income.isHighIncome(999.0), "Income slightly below the boundary should not be considered high income.");
    }
    
    @Test
    public void testReturnValueMutation() {
        assertEquals(1100.0, income.getTotalIncome(true), "Total income should include a fee.");
        assertEquals(1000.0, income.getTotalIncome(false), "Total income should not include a fee.");
    }

    }

