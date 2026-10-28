
package service;

public class DeadlockFix {
    private static final Object lockA = new Object();
    private static final Object lockB = new Object();

    public static void transfer(boolean reverse) {
        Object first = lockA;
        Object second = lockB;

        synchronized (first) {
            synchronized (second) {
                System.out.println(
                        Thread.currentThread().getName()
                                + " completed transfer");
            }
        }
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(
                () -> transfer(false), "Thread-1");

        Thread t2 = new Thread(
                () -> transfer(true), "Thread-2");

        t1.start();
        t2.start();
    }
}
