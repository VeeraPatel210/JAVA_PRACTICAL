
import java.util.Scanner;

public class MiniBank {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        BankInfo bank = new BankInfo("MiniBank", "CHARUSAT");
        System.out.println("Welcome to " + bank.name());
        System.out.println("Branch: " + bank.branch());

        Customer c1 = new Customer("Riya", "riya@gmail.com", "9876543210");
        Customer c2 = new Customer("Aarav", "aarav@gmail.com", "9876543211");

        System.out.println("\nCustomer Details:");
        System.out.println(c1.getCustomerId() + " | " + c1.getName());
        System.out.println(c2.getCustomerId() + " | " + c2.getName());

        Account[] accounts = {
            new Account("Riya", 5000),
            new Account("Aarav", 3000),
            new Account("Dhruv")
        };

        accounts[0].deposit(2000);
        accounts[0].withdraw(3000);
        boolean result = accounts[0].withdraw(10000);

        System.out.println("\nAccount Details:");
        for (Account account : accounts) {
            System.out.println(account);
        }

        System.out.println("Large withdrawal successful: " + result);

        Account a1 = new Account("Riya", 4000);
        Account a2 = new Account(a1);

        System.out.println("\nPractical 3:");
        System.out.println(a1);
        System.out.println("Accounts equal: " + a1.equals(a2));
        System.out.println("Is Account: " + (a1 instanceof Account));

        Customer customer = new Customer("Riya", "riya@gmail.com", "9876543210");
        customer.setAddress(new Customer.Address("University Road", "Anand", "388001"));
        System.out.println("Customer address: " + customer.getAddress().getCity());
        System.out.println("Customer cloned: " + customer.clone().getName());

        System.out.println("\nPractical 4: Validation");

        System.out.println("Valid mobile: " + Validator.isValidMobile("9876543210"));
        System.out.println("Invalid mobile: " + Validator.isValidMobile("12345"));
        System.out.println("Valid email: " + Validator.isValidEmail("abc@gmail.com"));
        System.out.println("Invalid email: " + Validator.isValidEmail("abc"));
        System.out.println("Valid PAN: " + Validator.isValidPan("ABCDE1234F"));
        System.out.println("Invalid PAN: " + Validator.isValidPan("12345"));
        System.out.println("Valid IFSC: " + Validator.isValidIfsc("SBIN0001234"));
        System.out.println("Invalid IFSC: " + Validator.isValidIfsc("123"));

        Command command = CommandParser.parse("DEPOSIT AC0001 500");
        System.out.println("Command type: " + command.type());
        System.out.println("Account number: " + command.accountNumber());
        System.out.println("Amount: " + command.amount());

        System.out.println("\n" + StatementFormatter.buildStatement(accounts[0]));

        int choice = 0;

        while (choice != 5) {
            System.out.println("\n1. Open Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            if (!sc.hasNextInt()) {
                System.out.println("Enter a number from 1 to 5.");
                sc.next();
                continue;
            }

            choice = sc.nextInt();

            MenuOption option = switch (choice) {
                case 1 -> MenuOption.OPEN_ACCOUNT;
                case 2 -> MenuOption.DEPOSIT;
                case 3 -> MenuOption.WITHDRAW;
                case 4 -> MenuOption.TRANSFER;
                case 5 -> MenuOption.EXIT;
                default -> null;
            };

            if (option == null) {
                System.out.println("Invalid choice.");
                continue;
            }

            String message = switch (option) {
                case OPEN_ACCOUNT -> "Open Account selected.";
                case DEPOSIT -> "Deposit selected.";
                case WITHDRAW -> "Withdraw selected.";
                case TRANSFER -> "Transfer selected.";
                case EXIT -> "Thank you for using MiniBank!";
            };

            System.out.println(message);
        }

        sc.close();
    }
}
