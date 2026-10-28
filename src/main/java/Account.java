
import java.util.Objects;

public abstract class Account {
    private final String accountNumber;
    private String ownerName;
    protected long balance;
    private boolean active;
    private static int counter = 0;

    private static String generateNumber() {
        return String.format("AC%04d", ++counter);
    }

    public Account(String ownerName, long balance) {
        this.accountNumber = generateNumber();
        this.ownerName = ownerName;
        this.balance = balance;
        this.active = true;
    }

    public abstract double interestRate();

    public abstract boolean canWithdraw(long amount);

    public void deposit(long amount) {
        if (amount > 0) {
            balance += amount;
        }
    }

    public boolean withdraw(long amount) {
        if (amount > 0 && canWithdraw(amount)) {
            balance -= amount;
            return true;
        }
        return false;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public long getBalance() {
        return balance;
    }

    public boolean isActive() {
        return active;
    }

    @Override
    public String toString() {
        return accountNumber + " | " + ownerName + " | " + balance;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Account other)) return false;
        return accountNumber.equals(other.accountNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountNumber);
    }
}
