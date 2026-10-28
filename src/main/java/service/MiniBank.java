
package service;

import model.*;

public class MiniBank {
    public static void main(String[] args) {
        Account account = new SavingsAccount("Dhruv", 1000, 100);

        System.out.println("MiniBank - Practical 9");
        System.out.println("Initial balance: " + account.getBalance());

        int threadCount = 10;
        int times = 1000;
        long amount = 10;

        Thread[] threads = new Thread[threadCount];

        for (int i = 0; i < threadCount; i++) {
            AccountWorker worker =
                    new AccountWorker(account, times, amount);

            threads[i] = new Thread(worker, "Worker-" + (i + 1));
            threads[i].start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Thread interrupted.");
                return;
            }
        }

        System.out.println("All threads completed.");
        System.out.println("Expected balance: "
                + (1000 + (long) threadCount * times * amount));
        System.out.println("Final balance: " + account.getBalance());
    }
}
