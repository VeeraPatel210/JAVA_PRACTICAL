package util;
import model.Account;

public class StatementFormatter {
    public static String buildStatement(Account account) {
        StringBuilder statement = new StringBuilder();

        statement.append("MINIBANK ACCOUNT STATEMENT\n");
        statement.append("Account Number: ").append(account.getAccountNumber()).append("\n");
        statement.append("Account Holder: ").append(account.getOwnerName()).append("\n");
        statement.append("Balance: Rs. ").append(account.getBalance()).append("\n");

        return statement.toString();
    }
}
