package finance.management;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SavingsGoalTest {
    private SavingGoal savingGoal;

    @BeforeEach
    public void setUp() {
        savingGoal = new SavingGoal("Vacation", 1000.0);
    }

    @Test
    public void testGetName() {
        assertEquals("Vacation", savingGoal.getName(), "Name should be 'Vacation'");
    }

    @Test
    public void testGetTargetAmount() {
        assertEquals(1000.0, savingGoal.getTargetAmount(), "Target amount should be 1000.0");
    }

    @Test
    public void testAddSavings() {
        savingGoal.addSavings(200.0);
        assertEquals(200.0, savingGoal.getSavedAmount(), "Saved amount should be 200.0 after adding savings");
    }

    @Test
    public void testIsGoalReachedFalse() {
        savingGoal.addSavings(500.0);
        assertFalse(savingGoal.isGoalReached(), "Goal should not be reached with 500.0 saved");
    }

    @Test
    public void testIsGoalReachedTrue() {
        savingGoal.addSavings(1000.0);
        assertTrue(savingGoal.isGoalReached(), "Goal should be reached with 1000.0 saved");
    }

    @Test
    public void testDeleteSavings() {
        savingGoal.addSavings(500.0);
        savingGoal.deleteSavings(300.0);
        assertEquals(200.0, savingGoal.getSavedAmount(), "Saved amount should be 200.0 after deleting 300.0");
    }

    @Test
    public void testDeleteSavingsMoreThanSaved() {
        savingGoal.addSavings(200.0);
        savingGoal.deleteSavings(300.0); // This should trigger error
        assertEquals(200.0, savingGoal.getSavedAmount(), "Saved amount should remain 200.0");
    }
    
    @Test
    public void testAchieveSavingsGoalExactly() {
        SavingGoal savingGoal = new SavingGoal("Vacation", 1000);
        savingGoal.addSavings(1000);
        assertTrue(savingGoal.isGoalReached(), "Savings goal should be achieved when target amount is met.");
    }

    @Test
    public void testExceedSavingsGoal() {
        SavingGoal savingGoal = new SavingGoal("New Car", 5000);
        savingGoal.addSavings(6000);
        assertEquals(6000, savingGoal.getSavedAmount(), "Savings goal amount should reflect correctly even if exceeded.");
    }
    
 // Test Case for Relational Operator Replacement (ROR)
    @Test
    public void testRelationalOperatorReplacement() {
        savingGoal.addSavings(999.9);
        assertFalse(savingGoal.isGoalReached(), "Goal should not be reached with saved amount less than target.");
        savingGoal.addSavings(0.1);
        assertTrue(savingGoal.isGoalReached(), "Goal should be reached when saved amount is equal to target.");
    }
    
 // Test Case for Conditional Boundary Mutations (CBM)
    @Test
    public void testBoundaryConditions() {
        savingGoal.addSavings(1000.0);
        assertTrue(savingGoal.isGoalReached(), "Savings goal should be reached exactly at boundary.");
        savingGoal.setTargetAmount(999.9);
        assertTrue(savingGoal.isGoalReached(), "Savings goal should still be achieved after reducing target slightly.");
    }

    // Test Case for Parameter Replacement (PR)
    @Test
    public void testParameterReplacement() {
        SavingGoal savingGoal = new SavingGoal("Travel", 2000);
        savingGoal.setTargetAmount(2500); // Replacing targetAmount parameter to see effect on goal reached
        assertEquals(2500, savingGoal.getTargetAmount(), "Target amount should be updated to new value.");
    }

    // Test Case for Method Call Replacement (MCR)
    @Test
    public void testMethodCallReplacement() {
        savingGoal.addSavings(1000.0);
        savingGoal.deleteSavings(500.0); // Replace addSavings with deleteSavings to see effect
        assertEquals(500.0, savingGoal.getSavedAmount(), "Saved amount should be reduced to 500.0 after deletion.");
    }

    // Test Case for Return Value Mutations (RVM)
    @Test
    public void testReturnValueMutation() {
        savingGoal.addSavings(1000.0);
        boolean goalReached = savingGoal.isGoalReached(); // Mutate return value
        assertTrue(goalReached, "Savings goal should be reached with 1000.0 saved.");
    }
    
    @Test
    public void testAddSavingsOverflow() {
        savingGoal.addSavings(Double.MAX_VALUE);
        savingGoal.addSavings(1.0); // Try adding beyond the maximum value
        assertEquals(Double.MAX_VALUE, savingGoal.getSavedAmount(), "Adding beyond max value should cap at Double.MAX_VALUE.");
    }

    @Test
    public void testNegativeSavingsAddition() {
        savingGoal.addSavings(-500.0); // Adding negative savings
        assertEquals(0.0, savingGoal.getSavedAmount(), "Adding negative savings should not change the saved amount.");
    }

    @Test
    public void testDeleteSavingsToZero() {
        savingGoal.addSavings(1000.0);
        savingGoal.deleteSavings(1000.0); // Deleting all savings
        assertEquals(0.0, savingGoal.getSavedAmount(), "All savings should be deleted and amount should be zero.");
    }

    @Test
    public void testProgressCalculation() {
        savingGoal.addSavings(500.0);
        savingGoal.showProgress(); // Check if progress calculation is correct
        // The test should capture the output, verify progress = 50%
    }
    
    @Test
    public void testSmallPositiveSavingsAddition() {
        savingGoal.addSavings(0.01); // Smallest possible positive addition
        assertEquals(0.01, savingGoal.getSavedAmount(), "Saved amount should reflect the small positive addition.");
    }
    
    @Test
    public void testUpdateTargetToMaxValue() {
        savingGoal.setTargetAmount(Double.MAX_VALUE);
        assertEquals(Double.MAX_VALUE, savingGoal.getTargetAmount(), "Target amount should be updated to maximum value.");
    }
    
    @Test
    public void testSequentialSavingsAdditions() {
        savingGoal.addSavings(100.0);
        savingGoal.addSavings(500.0);
        savingGoal.addSavings(-50.0); // Should not affect
        assertEquals(600.0, savingGoal.getSavedAmount(), "Total savings should reflect only the positive additions.");
    }
    
    @Test
    public void testGoalReachedExactlyWithBoundarySavings() {
        savingGoal.addSavings(savingGoal.getTargetAmount());
        assertTrue(savingGoal.isGoalReached(), "Goal should be achieved when saved amount exactly equals the target.");
    }

    @Test
    public void testSavingsJustBelowTarget() {
        savingGoal.addSavings(savingGoal.getTargetAmount() - 0.01);
        assertFalse(savingGoal.isGoalReached(), "Goal should not be achieved with savings just below the target.");
    }

    @Test
    public void testNegativeSavingsDeletion() {
        savingGoal.addSavings(500.0);
        savingGoal.deleteSavings(-100.0); // Should not affect
        assertEquals(500.0, savingGoal.getSavedAmount(), "Savings should remain unaffected when trying to delete a negative amount.");
    }

    @Test
    public void testFloatingPointPrecisionAddition() {
        savingGoal.addSavings(0.1);
        savingGoal.addSavings(0.2);
        assertEquals(0.3, savingGoal.getSavedAmount(), 1e-9, "Saved amount should handle floating-point precision correctly.");
    }

    @Test
    public void testSetNegativeTargetAmount() {
        savingGoal.setTargetAmount(-1000.0);
        assertTrue(savingGoal.getTargetAmount() >= 0, "Target amount should not be set to a negative value.");
    }

}


