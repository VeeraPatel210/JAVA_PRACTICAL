
package service;

import model.Account;
import model.SavingsAccount;
import model.CurrentAccount;
import model.InvalidAmountException;
import java.util.ArrayList;

public class BankService {

    private final Repository<Account> repository = new Repository<>();

    public Account openAccount(String name, long balance, String type, long limit) {
        Account account;

        if (balance < 0) {
            throw new IllegalArgumentException("Balance cannot be negative");
        }

        if (type.equalsIgnoreCase("savings")) {
            account = new SavingsAccount(name, balance, limit);
        } else if (type.equalsIgnoreCase("current")) {
            account = new CurrentAccount(name, balance, limit);
        } else {
            throw new IllegalArgumentException("Invalid account type");
        }

        repository.save(account);
        return account;
    }

    public Account findAccount(String accountNumber) {
        return repository.findById(accountNumber);
    }

    public void deposit(String accountNumber, long amount)
            throws InvalidAmountException {
        Account account = findAccount(accountNumber);

        if (account == null) {
            throw new IllegalArgumentException("Account not found");
        }

        account.deposit(amount);
    }

    public void withdraw(String accountNumber, long amount) {
        Account account = findAccount(accountNumber);

        if (account == null) {
            throw new IllegalArgumentException("Account not found");
        }

        if (!account.withdraw(amount)) {
            throw new IllegalArgumentException(
                "Invalid amount or insufficient balance"
            );
        }
    }

    public void transfer(String fromNumber, String toNumber, long amount)
            throws InvalidAmountException {
        Account from = findAccount(fromNumber);
        Account to = findAccount(toNumber);

        if (from == null || to == null) {
            throw new IllegalArgumentException("Account not found");
        }

        if (fromNumber.equals(toNumber)) {
            throw new IllegalArgumentException(
                "Cannot transfer to the same account"
            );
        }

        synchronized (from) {
            if (!from.withdraw(amount)) {
                throw new IllegalArgumentException(
                    "Invalid amount or insufficient balance"
                );
            }

            try {
                to.deposit(amount);
            } catch (InvalidAmountException e) {
                from.deposit(amount);
                throw e;
            }
        }
    }

    public String statement(String accountNumber) {
        Account account = findAccount(accountNumber);

        if (account == null) {
            return "Account not found";
        }

        return account.toString();
    }

    public ArrayList<Account> listAccounts() {
        return repository.findAll();
    }

    public void loadAccounts(ArrayList<Account> accounts) {
        repository.saveAll(accounts.toArray(new Account[0]));
    }
}
