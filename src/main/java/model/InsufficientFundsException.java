
package model;

public class InsufficientFundsException extends Exception {
    private final long shortfall;

    public InsufficientFundsException(long shortfall) {
        super("Insufficient funds");
        this.shortfall = shortfall;
    }

    public long getShortfall() {
        return shortfall;
    }
}
