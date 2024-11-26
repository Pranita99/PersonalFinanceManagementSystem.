package finance.management;

public class BudgetManager {
    private double monthlyBudget;
    private double expenses;
    private String warningMessage;

    public BudgetManager(double monthlyBudget) {
        this.monthlyBudget = monthlyBudget;
        this.expenses = 0;
    }
    
    

    public void addExpense(double amount) {
        if (amount < 0) {
            System.out.println("Error: Negative expense not allowed.");
            return;
        }
        expenses += amount;
        System.out.println("Expense added: $" + amount);
        notifyUser();
    }

    public void addIncome(double amount) {
        monthlyBudget += amount;
        System.out.println("Income added to budget: $" + amount);
    }
    
    public boolean isBudgetExceeded() {
        return expenses > monthlyBudget;
    }

    public void notifyUser() {
        if (expenses >= 0.8 * monthlyBudget) {
            warningMessage = "Warning: You are nearing your budget limit!";
        } else {
            warningMessage = null; // Clear the message if no warning condition
        }
        System.out.println(warningMessage);
    }
    
    public double getRemainingBudget() {
        // Print the current monthly budget and expenses for debugging
        System.out.println("Monthly Budget: " + monthlyBudget);
        System.out.println("Current Expenses: " + expenses);
        // Calculate and return the remaining budget
        return monthlyBudget - expenses;
    }
    
    public void updateBudget(double newBudget) {
        System.out.println("Updating budget from " + monthlyBudget + " to " + newBudget); // Debug statement
        this.monthlyBudget = newBudget;
        System.out.println("Monthly budget updated to: $" + newBudget);
    }
    
    
    
    public double getMonthlyBudget() {
        return monthlyBudget;
    }
    
    public String getWarningMessage() {
        return warningMessage;
    }
}
