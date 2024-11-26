package finance.management;

public class Income extends Transaction {
    public Income(double amount, String description) {
        super(amount, description, "Income"); // Passes category as "Income"
    }

    public void increaseIncome(double increment) {
        this.amount += increment;
    }
    public void updateIncome(double newAmount) {
        this.amount = newAmount;
        System.out.println("Income updated to: $" + newAmount);
    }
    
    public boolean isHighIncome(double amount) {
        return amount >= 1000.0; // Include 1000.0 as part of high income
    }
    
    public void updateDescription(String newDescription) {
        this.description = newDescription;
        System.out.println("Income description updated to: " + newDescription);
    }
    
    public double getTotalIncome(boolean includeFee) {
        return includeFee ? this.amount + 100.0 : this.amount;
    }
    
    
    }



