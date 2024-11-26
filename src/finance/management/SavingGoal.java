package finance.management;
class SavingGoal {
    private String name;
    private double targetAmount;
    private double savedAmount;

    public SavingGoal(String name, double targetAmount) {
        this.name = name;
        this.targetAmount = targetAmount;
        this.savedAmount = 0;
    }

    public String getName() {
        return name;
    }

    public double getTargetAmount() {
        return targetAmount;
    }
    public double getSavedAmount() {
        return savedAmount;
    }

    public void setTargetAmount(double targetAmount) {
        if (targetAmount < 0) {
            System.out.println("Error: Target amount cannot be negative.");
            return;
        }
        this.targetAmount = targetAmount;
    }
    public void addSavings(double amount) {
        if (amount > 0) {
            savedAmount += amount;
        } else {
            System.out.println("Error: Cannot add negative savings.");
        }
    }

    public boolean isGoalReached() {
        return savedAmount >= targetAmount;
    }
    
    public void deleteSavings(double amount) {
        if (amount < 0) {
            System.out.println("Error: Cannot delete a negative amount.");
            return;
        }
        if (savedAmount >= amount) {
            savedAmount -= amount;
            System.out.println("Deleted $" + amount + " from saving goal: " + name);
        } else {
            System.out.println("Error: Cannot delete more than saved.");
        }
    }
    
    public void showProgress() {
        System.out.println("Saving Goal: " + name);
        System.out.println("Target Amount: $" + targetAmount);
        System.out.println("Saved Amount: $" + savedAmount);
        System.out.println("Progress: " + (savedAmount / targetAmount * 100) + "%");
    }
}
