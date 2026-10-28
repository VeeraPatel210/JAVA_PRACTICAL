
package service;

import model.Account;
import model.InvalidAmountException;
import util.StatePersister;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class MiniBank {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        BankService bank = new BankService();

        ArrayList<Account> savedAccounts = StatePersister.load();
        bank.loadAccounts(savedAccounts);

        boolean running = true;

        while (running) {

            System.out.println("\n===== MINIBANK =====");
            System.out.println("1. Open Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer");
            System.out.println("5. View Statement");
            System.out.println("6. List Accounts");
            System.out.println("7. Generate Report");
            System.out.println("8. Exit");
            System.out.print("Enter choice: ");

            try {
                int choice = Integer.parseInt(sc.nextLine());

                switch (choice) {

                    case 1:
                        System.out.print("Owner name: ");
                        String name = sc.nextLine();

                        System.out.print("Initial balance: ");
                        long balance = Long.parseLong(sc.nextLine());

                        System.out.print("Account type (savings/current): ");
                        String type = sc.nextLine();

                        System.out.print(
                            "Minimum balance or overdraft limit: ");
                        long limit = Long.parseLong(sc.nextLine());

                        Account account =
                            bank.openAccount(name, balance, type, limit);

                        System.out.println(
                            "Account opened successfully!");
                        System.out.println(account);
                        break;

                    case 2:
                        System.out.print("Account number: ");
                        String depositId = sc.nextLine();

                        System.out.print("Deposit amount: ");
                        long depositAmount =
                            Long.parseLong(sc.nextLine());

                        bank.deposit(depositId, depositAmount);
                        System.out.println("Deposit successful.");
                        break;

                    case 3:
                        System.out.print("Account number: ");
                        String withdrawId = sc.nextLine();

                        System.out.print("Withdrawal amount: ");
                        long withdrawAmount =
                            Long.parseLong(sc.nextLine());

                        bank.withdraw(withdrawId, withdrawAmount);
                        System.out.println("Withdrawal successful.");
                        break;

                    case 4:
                        System.out.print("From account number: ");
                        String from = sc.nextLine();

                        System.out.print("To account number: ");
                        String to = sc.nextLine();

                        System.out.print("Transfer amount: ");
                        long transferAmount =
                            Long.parseLong(sc.nextLine());

                        bank.transfer(from, to, transferAmount);
                        System.out.println("Transfer successful.");
                        break;

                    case 5:
                        System.out.print("Account number: ");
                        String statementId = sc.nextLine();

                        System.out.println(
                            bank.statement(statementId));
                        break;

                    case 6:
                        ArrayList<Account> accounts =
                            bank.listAccounts();

                        if (accounts.isEmpty()) {
                            System.out.println("No accounts found.");
                        } else {
                            for (Account a : accounts) {
                                System.out.println(a);
                            }
                        }
                        break;

                    case 7:
                        try {
                            ReportGenerator.generate(
                                Path.of("logs"),
                                Path.of("bank-report.txt")
                            );

                            System.out.println(
                                "Report generated: bank-report.txt");
                        } catch (IOException e) {
                            System.out.println(
                                "Report error: " + e.getMessage());
                        }
                        break;

                    case 8:
                        StatePersister.save(bank.listAccounts());
                        running = false;
                        System.out.println("Accounts saved. Goodbye!");
                        break;

                    default:
                        System.out.println("Invalid choice.");
                }

            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");

            } catch (IllegalArgumentException |
                     InvalidAmountException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        sc.close();
    }
}
