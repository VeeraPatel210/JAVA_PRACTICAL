
package service;

public class DeadlockDemo {
    private static final Object lockA = new Object();
    private static final Object lockB = new Object();

    public static void main(String[] args) {
        System.out.println("Demonstrating deadlock...");

        Thread t1 = new Thread(() -> {
            synchronized (lockA) {
                System.out.println("Thread 1 locked A");

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }

                synchronized (lockB) {
                    System.out.println("Thread 1 locked B");
                }
            }
        });

        Thread t2 = new Thread(() -> {
            synchronized (lockB) {
                System.out.println("Thread 2 locked B");

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }

                synchronized (lockA) {
                    System.out.println("Thread 2 locked A");
                }
            }
        });

        t1.start();
        t2.start();

        System.out.println("Opposite lock order can cause deadlock.");
    }
}
