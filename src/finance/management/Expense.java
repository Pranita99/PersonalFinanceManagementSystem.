package finance.management;

public class Expense extends Transaction {
    private String category;
   

    public Expense(double amount, String description, String category) {
        super(amount, description, category); // Fix the typo here
        this.category = category;
    }

    public String getCategory() {
        return category;
    }
    
    public void updateExpense(double newAmount, String newDescription) {
        this.amount = newAmount;
        this.description = newDescription;
        System.out.println("Expense updated to: $" + newAmount + " with description '" + newDescription + "'");
    }
}
