
package util;

import model.Account;
import java.io.*;
import java.util.ArrayList;

public class StatePersister {

    private static final String FILE_NAME = "accounts.dat";

    public static void save(ArrayList<Account> accounts) {
        try (ObjectOutputStream out =
                     new ObjectOutputStream(
                         new FileOutputStream(FILE_NAME))) {
            out.writeObject(accounts);
            System.out.println("Accounts saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving accounts: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public static ArrayList<Account> load() {
        File file = new File(FILE_NAME);

        if (!file.exists()) {
            return new ArrayList<>();
        }

        try (ObjectInputStream in =
                     new ObjectInputStream(new FileInputStream(file))) {
            return (ArrayList<Account>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading accounts: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
