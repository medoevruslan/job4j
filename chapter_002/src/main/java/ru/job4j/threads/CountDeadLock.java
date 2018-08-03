package ru.job4j.threads;
import java.util.concurrent.CountDownLatch;

/**
 * @author Medoev Ruslan (mr.r.m3@icloud.com).
 * @version $Id$.
 * @since 0.1.
 */

public class CountDeadLock extends Thread {
    private final CountDownLatch latch;
    private final Object firstLock;
    private final Object secondLock;

    private CountDeadLock(Object first, Object second, CountDownLatch latch) {
        this.firstLock = first;
        this.secondLock = second;
        this.latch = latch;
    }

    @Override
    public void run() {
        synchronized (firstLock) {
            latch.countDown();
            System.out.println(Thread.currentThread().getName() + " count down");
            try {
                System.out.println(Thread.currentThread().getName() + " await");
                latch.await();
            } catch (InterruptedException e) {
                throw new RuntimeException();
            }
            synchronized (secondLock) {
                System.out.println("Thread finished");
            }
        }

    }

    public static void main(String[] args) {
        final Object firstLock = new Object();
        final Object secondLock = new Object();
        final CountDownLatch latch = new CountDownLatch(2);

        new CountDeadLock(firstLock, secondLock, latch).start();
        new CountDeadLock(secondLock, firstLock, latch).start();

    }
}
