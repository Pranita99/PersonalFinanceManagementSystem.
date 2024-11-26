 Mutation Testing Report

Course : CS 731 - Software Testing

Project Title : Mutation Testing on Personal Finance Management System

Student Name : Pranita Ganguly - MS2024o17

Instructor : Prof. Meenakshi D Souza

Date : November 25, 2024

 1. Introduction to Mutation Testing

Mutation Testing is a software testing technique used to assess the quality of software tests by introducing small modifications or "mutations" in the program code and observing if the tests can detect these changes. If the tests fail, it indicates that the mutant was killed; if they pass, the mutant survives, which implies potential inadequacy in the test suite. The primary goal is to create effective test cases that can capture these subtle changes, thereby improving the robustness of the code.
Traditional test coverage (e.g., line, branch) measures whether code is executed by tests but doesn't determine if the tests can identify faults in the code. Mutation testing, on the other hand, evaluates the quality of test cases by seeding faults and verifying if these faults are detected, which makes it a more reliable metric for test adequacy.

1.2 Tools Used

Java (PIT) : PIT is a state-of-the-art mutation testing tool for Java that runs unit tests against automatically modified versions of application code to determine if the test suite can catch introduced mutations. It provides detailed reports combining line coverage and mutation coverage.

1.3 Objective of Mutation Testing

The primary objective of mutation testing is to demonstrate the strength of the test suite by killing the introduced mutants. Two types of mutant killings are discussed in this report:

1. Weak Mutant Killing : A mutant is weakly killed if the state of the program differs immediately after the mutated part is executed.
2. Strong Mutant Killing : A mutant is strongly killed if the output of the mutated version differs from the original.

Our goal for this project was to achieve strong mutant killing for all generated mutants to effectively assess the robustness of the test suite.

 2. Project Overview

Project Idea : Personal Finance Management System

The project involves creating a Personal Finance Management System to help users manage their expenses, income, and budget. It includes features such as adding and categorizing expenses, tracking income, setting a budget, and generating reports. The application is designed in Java and contains sufficient complexity to apply various mutation testing operators.

Key Functionalities :

- Income Management : Add, edit, and delete sources of income.
-  Expense Management : Add, edit, categorize, and track expenses.
-  Budget Tracking : Set monthly budget limits and notify the user when spending exceeds a specific percentage of the budget.
-  Reports and Summary : Generate monthly summaries of total income, expenses, and savings. Generate spending trends categorized by expenses.

Project Structure
The project is structured into several classes:
- Transaction: Represents both income and expense records.
- Income: Extends Transaction, managing income data.
- Expense: Extends Transaction, managing expense data and categories.
- BudgetManager: Handles budget setting and tracking.
- ReportGenerator: Generates spending summaries and trends.
- FinanceManager: The main class that manages all other classes.

 3. Mutation Testing Plan

The mutation testing plan involved two levels of mutation testing: Unit-Level and Integration-Level.

3.1 Unit-Level Mutation Operators
- Arithmetic Operator Replacement (AOR): Mutate arithmetic operations (e.g., changing "+" to "-") in calculations such as remaining budget.
- Conditional Boundary Mutations (CBM): Alter budget limits (e.g., changing ">=" to ">" when checking budget exceedance).
- Relational Operator Replacement (ROR): Replace relational operators in expense threshold checks (e.g., replacing "<" with "<=").

3.2 Integration-Level Mutation Operators
- Parameter Replacement (PR): Mutate parameters passed between classes (e.g., replacing expense category during method calls).
- Return Value Mutations (RVM): Modify return values for integration methods, such as generating different outputs for report summaries.
- Method Call Replacement (MCR): Replace calls between classes (e.g., substituting a method for income management when dealing with expenses).

4. Testing Tools

We used **PIT** for mutation testing at both the unit and integration levels. PIT is a comprehensive tool for Java that allows easy integration with existing unit tests and provides valuable insights through mutation and line coverage reports.

5. Mutation Operators Used

Unit-Level Mutation Operators
1. Arithmetic Operator Replacement (AOR): Altered arithmetic operations for calculating remaining budget and expense summaries.
2. Conditional Boundary Mutations (CBM) : Modified boundary conditions when comparing expenses against budget thresholds.
3. Relational Operator Replacement (ROR) : Changed relational comparisons during income and expense categorization.

Integration-Level Mutation Operators
1. Parameter Replacement (PR): Replaced method parameters when testing communication between `Expense` and `ReportGenerator` classes.
2. Method Call Replacement (MCR): Modified method call relationships between `Income` and `BudgetManager` classes.
3. Return Value Mutations (RVM): Changed return values in `ReportGenerator` during report generation to simulate various scenarios.

 6. Mutation Testing Results

The mutation testing was conducted using PIT, and the results were as follows:

- Number of Classes: 7
- Line Coverage: 72% (224/313 lines covered)
- Mutation Coverage: 42% (82/196 mutants killed)
- Test Strength: 56% (82/146 tests strong enough to kill mutants)



7. Lines of code 

Unit and Integration Testing in Java (PIT)
       
  Total Lines : 1859


BudgetManager.java ( Class + Test File ) → 186
FinanceManager.java ( Class + Test File ) →499
Income.java ( Class + Test File ) →174
Expense.java ( Class + Test File ) →219
Transaction.java ( Class + Test File ) →166
SavingsGoal.java ( Class + Test File ) →255
Report Generator ( Class + Test File ) →360




8.  Analysis

The mutation coverage is at 42%, which indicates that the test suite needs improvement to detect more faults in the application. The test strength is 56%, implying that around half of the tests were effective in killing mutants. Further enhancement of the test cases, particularly around the expense management and budget threshold calculations, is required to improve the overall mutation score.

9. Conclusion

This project highlights the effectiveness of mutation testing as a method to assess the robustness of test suites. By introducing various mutations and observing if the tests could detect them, we were able to gauge the quality of our test cases. Although the current mutation score of 42% suggests that improvements are needed, it also provided a clear direction for enhancing the testing quality. The use of PIT proved effective in identifying weak spots within the Personal Finance Management System, particularly in handling edge cases around income, expenses, and budget management.

10. Future Work

- Enhance Test Coverage: Improve unit tests by adding more edge cases for expense and budget-related calculations.
- Optimize Mutation Operators: Refine the mutation operators to target uncovered portions of the code.
- Parallel Testing: Explore parallel test execution to reduce the time taken for mutation testing and improve efficiency.


 11. Individual Contribution

This project was completed solely by Pranita Ganguly (MS2024o17), including the design, implementation, and mutation testing of the Personal Finance Management System. The insights gained through mutation testing will contribute to the continuous improvement of the project.


