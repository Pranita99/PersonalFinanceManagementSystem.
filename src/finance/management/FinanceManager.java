package finance.management;

import java.util.*;
import java.text.SimpleDateFormat;

@SuppressWarnings("unused")
public class FinanceManager {
    private List<Income> incomes;
    private List<Expense> expenses;
    private BudgetManager budgetManager;
    private ReportGenerator reportGenerator;
    private List<SavingGoal> savingGoals;

    public FinanceManager(double monthlyBudget) {
        incomes = new ArrayList<>();
        expenses = new ArrayList<>();
        budgetManager = new BudgetManager(monthlyBudget);
        reportGenerator = new ReportGenerator();
        savingGoals = new ArrayList<>();
    }

    public void addIncome(double amount, String description) {
        if (amount < 0) {
            System.out.println("Error: Negative income is not allowed.");
            return;
        }
        Income income = new Income(amount, description);
        incomes.add(income);
        reportGenerator.addTransaction(income);
        System.out.println("Income added to budget: " + amount); // Debug statement
    }

    public void addExpense(double amount, String description, String category) {
        if (amount < 0) {
            System.out.println("Error: Negative expense is not allowed.");
            return;
        }
        Expense expense = new Expense(amount, description, category);
        expenses.add(expense);
        budgetManager.addExpense(amount);
        budgetManager.notifyUser();
        reportGenerator.addTransaction(expense);
    }
    
    public void addSavingGoal(String name, double targetAmount) {
        SavingGoal savingGoal = new SavingGoal(name, targetAmount);
        savingGoals.add(savingGoal);
    }

    public void addSavingsToGoal(String goalName, double amount) {
        for (SavingGoal goal : savingGoals) {
            if (goalName.equals(goal.getName())) {
                goal.addSavings(amount);
                System.out.println("Added $" + amount + " to saving goal: " + goalName);
                return;
            }
        }
        System.out.println("Saving goal not found: " + goalName);
    }

    public double getTotalIncome() {
        return incomes.stream().mapToDouble(Income::getAmount).sum();
    }
    
    public double getRemainingBudget() {
        return budgetManager.getRemainingBudget();
    }


    public double getTotalExpenses() {
        return expenses.stream().mapToDouble(Expense::getAmount).sum();
    }

    public double getSavedAmountForGoal(String goalName) {
        for (SavingGoal goal : savingGoals) {
            if (goal.getName().equals(goalName)) {
                return goal.getSavedAmount();
            }
        }
        return 0;
    }

    public boolean isBudgetExceeded() {
        return budgetManager.isBudgetExceeded();
    }

    public void showSavingGoalsProgress() {
        for (SavingGoal goal : savingGoals) {
            goal.showProgress();
        }
    }

    public void showMonthlyReport() {
        reportGenerator.generateMonthlyReport();
    }

    public void showCategoryReport() {
        reportGenerator.generateCategoryReport();
    }

    public void showRemainingBudget() {
        System.out.println("Remaining Budget: $" + budgetManager.getRemainingBudget());
    }
    
    public void removeIncome(String description) {
        incomes.removeIf(income -> income.getDescription().equals(description));
        System.out.println("Income with description '" + description + "' removed.");
    }

    public void removeExpense(String description) {
        expenses.removeIf(expense -> expense.getDescription().equals(description));
        System.out.println("Expense with description '" + description + "' removed.");
    }

    public void modifySavingGoal(String name, double newTargetAmount) {
        for (SavingGoal goal : savingGoals) {
            if (goal.getName().equals(name)) {
                goal.setTargetAmount(newTargetAmount);
                System.out.println("Saving goal '" + name + "' updated with a new target of $" + newTargetAmount);
                return;
            }
        }
        System.out.println("Saving goal not found: " + name);
    }

    public void removeSavingGoal(String goalName) {
        savingGoals.removeIf(goal -> goal.getName().equals(goalName));
        System.out.println("Saving goal '" + goalName + "' removed.");
    }
    
    public BudgetManager getBudgetManager() {
        return budgetManager;
    }

    public List<Expense> getExpenses() {
        return expenses;
    }

    public SavingGoal getSavingGoal(String name) {
        for (SavingGoal goal : savingGoals) {
            if (goal.getName().equals(name)) {
                return goal;
            }
        }
        return null; // Or handle this case more explicitly if needed.
    }
    
    public void modifyIncome(Income income, boolean increase, double value) {
        if (increase) {
            // Increase the current income by adding the given value
            income.updateIncome(income.getAmount() + value);
        } else {
            // Set the income to the given value
            income.updateIncome(value);
       }
        
       
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Personal Finance Management System!");
        System.out.print("Enter your monthly budget: ");
        double monthlyBudget = scanner.nextDouble();
        FinanceManager financeManager = new FinanceManager(monthlyBudget);

        boolean running = true;

        while (running) {
            System.out.println("\n--- Menu ---");
            System.out.println("1. Add Income");
            System.out.println("2. Add Expense");
            System.out.println("3. Add Saving Goal");
            System.out.println("4. Add Savings to Goal");
            System.out.println("5. Show Monthly Report");
            System.out.println("6. Show Category Report");
            System.out.println("7. Show Saving Goals Progress");
            System.out.println("8. Show Remaining Budget");
            System.out.println("9. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter income amount: ");
                    double incomeAmount = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter income description: ");
                    String incomeDescription = scanner.nextLine();
                    financeManager.addIncome(incomeAmount, incomeDescription);
                    break;

                case 2:
                    System.out.print("Enter expense amount: ");
                    double expenseAmount = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter expense description: ");
                    String expenseDescription = scanner.nextLine();
                    System.out.print("Enter expense category: ");
                    String category = scanner.nextLine();
                    financeManager.addExpense(expenseAmount, expenseDescription, category);
                    break;

                case 3:
                    System.out.print("Enter saving goal name: ");
                    String goalName = scanner.nextLine();
                    System.out.print("Enter target amount: ");
                    double targetAmount = scanner.nextDouble();
                    financeManager.addSavingGoal(goalName, targetAmount);
                    break;

                case 4:
                    System.out.print("Enter saving goal name: ");
                    String savingGoalName = scanner.nextLine();
                    System.out.print("Enter amount to add to savings: ");
                    double savingAmount = scanner.nextDouble();
                    financeManager.addSavingsToGoal(savingGoalName, savingAmount);
                    break;

                case 5:
                    financeManager.showMonthlyReport();
                    break;

                case 6:
                    financeManager.showCategoryReport();
                    break;

                case 7:
                    financeManager.showSavingGoalsProgress();
                    break;

                case 8:
                    financeManager.showRemainingBudget();
                    break;

                case 9:
                    running = false;
                    System.out.println("Thank you for using the Personal Finance Management System. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }

        scanner.close();
    }
}

