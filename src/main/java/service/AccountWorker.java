
package service;

import model.Account;

public class AccountWorker implements Runnable {

    private Account account;
    private long amount;
    private boolean deposit;

    public AccountWorker(Account account, long amount, boolean deposit) {
        this.account = account;
        this.amount = amount;
        this.deposit = deposit;
    }

    @Override
    public void run() {
        try {
            if (deposit) {
                account.deposit(amount);
                System.out.println("Deposited: " + amount);
            } else {
                boolean success = account.withdraw(amount);

                if (success) {
                    System.out.println("Withdrawn: " + amount);
                } else {
                    System.out.println("Insufficient balance");
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
