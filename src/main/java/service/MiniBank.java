package service;
import model.Account;
import model.SavingsAccount;
import model.CurrentAccount;

import java.util.ArrayList;

public class MiniBank {
    public static void main(String[] args) {

        AccountStore store = new AccountStore();

        Account a1 = new SavingsAccount("Feni", 5000, 1000);
        Account a2 = new CurrentAccount("Veera", 2000, 1000);
        Account a3 = new SavingsAccount("Hetvi", 9000, 1000);

        store.add(a1);
        store.add(a2);
        store.add(a3);

        System.out.println("All Accounts:");
        for (Account account : store.getAll()) {
            System.out.println(account);
        }

        System.out.println("\nSearching for account:");
        System.out.println(store.get(a2.getAccountNumber()));

        System.out.println("\nAccounts Sorted by Balance:");
        ArrayList<Account> sorted = store.sortedByBalance();

        for (Account account : sorted) {
            System.out.println(account);
        }

        store.addTransaction("Deposit: 500");
        store.addTransaction("Withdrawal: 200");

        System.out.println("\nTransaction History:");
        for (String transaction : store.getTransactionHistory()) {
            System.out.println(transaction);
        }

        TransactionProcessor processor = new TransactionProcessor();

        processor.addAccount(a1);
        processor.addAccount(a2);
        processor.addAccount(a3);

        System.out.println("\nAccount from ConcurrentHashMap:");
        System.out.println(processor.getAccount(a1.getAccountNumber()));

        processor.stop();
    }
}
