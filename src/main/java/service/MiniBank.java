
package service;

import model.*;
import util.*;
import java.util.Scanner;

public class MiniBank {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            Account account = new SavingsAccount("Dhruv", 1000, 100);
            
System.out.println("\nPractical 8: Exception Handling");

try {
    account.withdraw(5000);
} catch (InsufficientFundsException e) {
    System.out.println(
            "Withdrawal failed: short by " + e.getShortfall());
} catch (InvalidAmountException e) {
    System.out.println(e.getMessage());
} finally {
    System.out.println("Balance: " + account.getBalance());
}


            System.out.println("Welcome to MiniBank");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Check Balance");
            System.out.println("4. Practical 7 Annotation Validation");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();

            try {
                switch (choice) {
                    case 1:
                        System.out.print("Enter deposit amount: ");
                        long deposit = sc.nextLong();
                        account.deposit(deposit);
                        System.out.println("Deposit successful.");
                        break;

                    case 2:
                        System.out.print("Enter withdrawal amount: ");
                        long amount = sc.nextLong();
                        account.withdraw(amount);
                        System.out.println("Withdrawal successful.");
                        break;

                    case 3:
                        System.out.println(
                                "Balance: " + account.getBalance());
                        break;

                    case 4:
                        Account invalidAccount =
                                new SavingsAccount(
                                        "VeryLongTestName", -100, 0);

                        String[] errors =
                                AnnotationValidator.validate(invalidAccount);

                        for (String error : errors) {
                            System.out.println(error);
                        }
                        break;

                    default:
                        System.out.println("Invalid choice.");
                }
            } catch (InsufficientFundsException e) {
                System.out.println(
                        "Withdrawal failed: short by "
                                + e.getShortfall());
            } catch (InvalidAmountException e) {
                System.out.println("Invalid amount: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Invalid input.");
            } finally {
                System.out.println("Transaction attempt completed.");
            }

            System.out.println(
                    "Current balance: " + account.getBalance());
        }
    }
}
