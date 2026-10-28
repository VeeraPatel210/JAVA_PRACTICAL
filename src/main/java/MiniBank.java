
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

            if (account instanceof SavingsAccount savings) {
    System.out.println("Minimum Balance: " + savings.getMinBalance());
}

            System.out.println();
        }

        System.out.println("Savings withdrawal: "
                + accounts[0].withdraw(2000));

        System.out.println("Current account withdrawal: "
                + accounts[1].withdraw(4000));

        System.out.println("Fixed deposit withdrawal: "
                + accounts[2].withdraw(1000));

        System.out.println("\nPractical 4: Validation");
        System.out.println(Validator.isValidMobile("9876543210"));
        System.out.println(Validator.isValidMobile("12345"));

        Command command = CommandParser.parse("DEPOSIT AC0001 500");
        System.out.println(command);

        System.out.println("\n" + StatementFormatter.buildStatement(accounts[0]));
    }
}
