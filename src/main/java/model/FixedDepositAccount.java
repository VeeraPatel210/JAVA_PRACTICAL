
package model;

public class FixedDepositAccount extends Account {
    private static final long serialVersionUID = 1L;

    private int months;

    public FixedDepositAccount(String name, long balance, int months) {
        super(name, balance);
        this.months = months;
    }

    @Override
    public double interestRate() {
        return months >= 12 ? 7.0 : 5.0;
    }

    @Override
    public boolean canWithdraw(long amount) {
        return false;
    }

    public int getMonths() {
        return months;
    }
}
