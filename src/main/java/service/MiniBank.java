package service;

import model.*;
import util.*;
import static util.Validator.isValidMobile;

public class MiniBank {
    public static void main(String[] args) {
        BankInfo bank = new BankInfo("MiniBank", "CHARUSAT");

        System.out.println("Welcome to " + bank.name());
        System.out.println("Branch: " + bank.branch());

        Account[] accounts = {
            new SavingsAccount("Riya", 5000, 1000),
            new CurrentAccount("Aarav", 3000, 2000),
            new FixedDepositAccount("Dhruv", 10000)
        };

        System.out.println("\nAccount Details:");

        for (Account account : accounts) {
            System.out.println(account);
            System.out.println("Interest Rate: " + account.interestRate() + "%");
            System.out.println("Yearly Interest: Rs. " + account.yearlyInterest());

            if (account instanceof SavingsAccount savings) {
                System.out.println("Minimum Balance: " + savings.getMinBalance());
            }

            System.out.println();
        }

        WithdrawRule rule1 = new WithdrawRule() {
            @Override
            public boolean allow(Account account, long amount) {
                return account.canWithdraw(amount);
            }
        };

        WithdrawRule rule2 = (account, amount) ->
                account.canWithdraw(amount);

        System.out.println("Anonymous class withdrawal allowed: "
                + rule1.allow(accounts[0], 1000));

        System.out.println("Lambda withdrawal allowed: "
                + rule2.allow(accounts[0], 1000));

        System.out.println("Savings account is Premium: "
                + (accounts[0] instanceof Premium));

        System.out.println("\nPractical 4: Validation");
        System.out.println(isValidMobile("9876543210"));
        System.out.println(isValidMobile("12345"));

        Command command = CommandParser.parse("DEPOSIT AC0001 500");
        System.out.println(command);

        System.out.println("\n"
                + StatementFormatter.buildStatement(accounts[0]));
    }
}
