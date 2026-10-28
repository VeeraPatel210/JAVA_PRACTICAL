
package model;
import model.annotation.Id;
import java.io.Serializable;

public abstract class Account implements Transactable, InterestBearing, Serializable, Comparable<Account> {

    private static final long serialVersionUID = 1L;
    @Id
    protected String accountNumber;
    protected long balance;
    protected String ownerName;

    private static int count = 1000;

    public Account(String ownerName, long balance) {
        this.accountNumber = "AC" + (++count);
        this.ownerName = ownerName;
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public long getBalance() {
        return balance;
    }

    public String getOwnerName() {
        return ownerName;
    }

    @Override
    public synchronized void deposit(long amount) throws InvalidAmountException {
        if (amount <= 0) {
            throw new InvalidAmountException("Amount must be positive");
        }
        balance += amount;
    }

    public boolean canWithdraw(long amount) {
        return amount > 0 && amount <= balance;
    }

    @Override
    public synchronized boolean withdraw(long amount) {
        if (canWithdraw(amount)) {
            balance -= amount;
            return true;
        }
        return false;
    }

    @Override
    public int compareTo(Account other) {
        return this.accountNumber.compareTo(other.accountNumber);
    }

    @Override
    public String toString() {
        return accountNumber + " | " + ownerName + " | Balance: " + balance;
    }
}
