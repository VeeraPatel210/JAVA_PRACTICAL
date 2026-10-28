
package service;

public class Practical10Demo {
    public static void main(String[] args) {
        System.out.println("Practical 10: Multithreading");

        TransactionProcessor processor = new TransactionProcessor();

        for (int i = 1; i <= 10; i++) {
            int id = i;

            processor.submit(() -> {
                System.out.println(
                        Thread.currentThread().getName()
                                + " processing transaction " + id);
            });
        }

        processor.stop();

        System.out.println("\nProducer-Consumer Demo");

        TransactionBuffer buffer = new TransactionBuffer();

        Thread producer = new Thread(() -> {
            try {
                for (int i = 1; i <= 5; i++) {
                    buffer.produce(i);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread consumer = new Thread(() -> {
            try {
                for (int i = 1; i <= 5; i++) {
                    buffer.consume();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        producer.start();
        consumer.start();

        try {
            producer.join();
            consumer.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("\nDeadlock-free Demo");

        Thread t1 = new Thread(
                () -> DeadlockFix.transfer(false), "Thread-1");

        Thread t2 = new Thread(
                () -> DeadlockFix.transfer(true), "Thread-2");

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Practical 10 completed.");
    }
}

