package finance.management;
import finance.management.FinanceManager;

import java.util.*;

public class ReportGenerator {
    private List<Transaction> transactions;

    public ReportGenerator() {
        transactions = new ArrayList<>();
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public void generateMonthlyReport() {
        double totalIncome = 0;
        double totalExpenses = 0;

        for (Transaction transaction : transactions) {
            if (transaction instanceof Income) {
                totalIncome += transaction.getAmount();
            } else if (transaction instanceof Expense) {
                totalExpenses += transaction.getAmount();
            }
        }

        System.out.println("--- Monthly Report ---");
        System.out.println("Total Income: $" + totalIncome);
        System.out.println("Total Expenses: $" + totalExpenses);
        System.out.println("Savings: $" + (totalIncome - totalExpenses));
    }

    public void generateCategoryReport() {
        Map<String, Double> categoryExpenses = new HashMap<>();

        for (Transaction transaction : transactions) {
            if (transaction instanceof Expense) {
                Expense expense = (Expense) transaction;
                categoryExpenses.put(expense.getCategory(), categoryExpenses.getOrDefault(expense.getCategory(), 0.0) + expense.getAmount());
            }
        }

        System.out.println("--- Category-wise Expense Report ---");
        for (Map.Entry<String, Double> entry : categoryExpenses.entrySet()) {
            System.out.println(entry.getKey() + ": $" + entry.getValue());
        }
    }
    
    public void generateYearlyReport() {
        double totalIncome = 0;
        double totalExpenses = 0;

        for (Transaction transaction : transactions) {
            if (transaction instanceof Income) {
                totalIncome += transaction.getAmount();
            } else if (transaction instanceof Expense) {
                totalExpenses += transaction.getAmount();
            }
        }

        System.out.println("--- Yearly Report ---");
        System.out.println("Total Income: $" + totalIncome);
        System.out.println("Total Expenses: $" + totalExpenses);
        System.out.println("Savings: $" + (totalIncome - totalExpenses));
    }

    public void clearTransactions() {
        transactions.clear();
    }

    public int getTransactionCount() {
        return transactions.size();
    }

    public void removeTransaction(Transaction transaction) {
        transactions.remove(transaction);
    }

    public Transaction findHighestExpense() {
        Expense highestExpense = null;

        for (Transaction transaction : transactions) {
            if (transaction instanceof Expense) {
                if (highestExpense == null || transaction.getAmount() > highestExpense.getAmount()) {
                    highestExpense = (Expense) transaction;
                }
            }
        }

        return highestExpense;
    }

    public List<Expense> filterExpensesByCategory(String category) {
        List<Expense> filteredExpenses = new ArrayList<>();

        for (Transaction transaction : transactions) {
            if (transaction instanceof Expense) {
                Expense expense = (Expense) transaction;
                if (expense.getCategory().equals(category)) {
                    filteredExpenses.add(expense);
                }
            }
        }

        return filteredExpenses;
    }

    public void updateTransaction(Transaction oldTransaction, Transaction newTransaction) {
        int index = transactions.indexOf(oldTransaction);
        if (index != -1) {
            transactions.set(index, newTransaction);
        }
       
    }
    
    public double calculateAverageExpense() {
        double totalExpenses = 0;
        int expenseCount = 0;

        for (Transaction transaction : transactions) {
            if (transaction instanceof Expense) {
                totalExpenses += transaction.getAmount();
                expenseCount++;
            }
        }

        return expenseCount > 0 ? totalExpenses / expenseCount : 0;
    }

    public double calculateTotalIncome() {
        double totalIncome = 0;

        for (Transaction transaction : transactions) {
            if (transaction instanceof Income) {
                totalIncome += transaction.getAmount();
            }
        }

        return totalIncome;
    }

    public double calculateTotalExpenses() {
        double totalExpenses = 0;

        for (Transaction transaction : transactions) {
            if (transaction instanceof Expense) {
                totalExpenses += transaction.getAmount();
            }
        }

        return totalExpenses;
    }

    public double calculateSavings() {
        return calculateTotalIncome() - calculateTotalExpenses();
    }
}




