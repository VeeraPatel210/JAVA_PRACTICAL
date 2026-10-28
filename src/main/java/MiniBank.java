
import java.util.Scanner;

public class MiniBank {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        BankInfo bank = new BankInfo("MiniBank", "CHARUSAT");
        System.out.println("Welcome to " + bank.name());
        System.out.println("Branch: " + bank.branch());

        Customer c1 = new Customer("Riya", "riya@gmail.com", "9876543210");
        Customer c2 = new Customer("Aarav", "aarav@gmail.com", "9876543211");

        System.out.println("\nCustomer IDs:");
        System.out.println(c1.getName() + ": " + c1.getCustomerId());
        System.out.println(c2.getName() + ": " + c2.getCustomerId());

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
            System.out.println(account.getAccountNumber() + " | "
                    + account.getOwnerName() + " | Balance: "
                    + account.getBalance());
        }

        System.out.println("Large withdrawal successful: " + result);

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
