
package service;


import model.Account;
import model.SavingsAccount;
import model.CurrentAccount;
import model.FixedDepositAccount;

import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;

public class MiniBank {

    public static void main(String[] args) {

        try {
            Path dataFolder = Path.of("data");
            Path logFolder = dataFolder.resolve("logs");
            Path accountFile = dataFolder.resolve("accounts.dat");
            Path reportFile = dataFolder.resolve("report.txt");

            Files.createDirectories(logFolder);

            Account[] accounts = {
                new SavingsAccount("Dhruv", 5000, 500),
                new CurrentAccount("Bhavy", 8000, 2000),
                new FixedDepositAccount("Pratik", 10000, 12)
            };

            TransactionLog.append(
                logFolder.resolve("transactions.txt"),
                "Deposit,500"
            );

            TransactionLog.append(
                logFolder.resolve("transactions.txt"),
                "Withdrawal,200"
            );

            StatePersister.save(accounts, accountFile);
            System.out.println("Accounts saved successfully.");

            Account[] loadedAccounts = StatePersister.load(accountFile);

            System.out.println("\nLoaded Accounts:");
            for (Account account : loadedAccounts) {
                System.out.println(account);
            }

            ReportGenerator.generate(logFolder, reportFile);

            System.out.println("\nReport:");
            System.out.println(Files.readString(reportFile));

            System.out.println("Practical 11 completed successfully.");

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
