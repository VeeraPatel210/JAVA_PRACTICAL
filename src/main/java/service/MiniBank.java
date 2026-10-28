package service;

import model.Account;
import model.SavingsAccount;
import model.CurrentAccount;

public class MiniBank {

    public static void main(String[] args) {

        SavingsAccount a1 = new SavingsAccount("Dhruv", 5000, 1000);
        CurrentAccount a2 = new CurrentAccount("Bhavy", 2000, 1000);
        SavingsAccount a3 = new SavingsAccount("Pratik", 9000, 1000);

        AccountStore store = new AccountStore();

        store.add(a1);
        store.add(a2);
        store.add(a3);

        System.out.println("Accounts Sorted by Balance:");
        for (Account account : store.sortedByBalance()) {
            System.out.println(account);
        }

        System.out.println("\nTransaction History:");
        store.addTransaction("Deposited 500");
        store.addTransaction("Withdrawn 200");

        for (String transaction : store.getTransactionHistory()) {
            System.out.println(transaction);
        }

        System.out.println("\nPractical 13 - Generic Repository:");

        Repository<Account> repository = new Repository<>();

        repository.saveAll(a1, a2, a3);

        System.out.println("\nAccount Found by ID:");
        System.out.println(repository.findById(a1.getAccountNumber()));

        System.out.println("\nAll Accounts from Repository:");
        for (Account account : repository.findAll()) {
            System.out.println(account);
        }

        System.out.println("\nDeleting Account:");
        System.out.println(repository.delete(a2.getAccountNumber()));

        Integer[] numbers = {10, 20, 30};
        Double[] decimals = {10.5, 20.5, 30.5};
        Long[] largeNumbers = {100L, 200L, 300L};

        System.out.println("\nAverage of Integers: " + Repository.average(numbers));
        System.out.println("Average of Doubles: " + Repository.average(decimals));
        System.out.println("Average of Longs: " + Repository.average(largeNumbers));
    }
}