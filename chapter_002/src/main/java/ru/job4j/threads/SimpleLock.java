package ru.job4j.threads;

/**
 * @author Medoev Ruslan (mr.r.m3@icloud.com)
 * @version $Id$
 * @since 0.1
 */

public class SimpleLock {
    private final Object sync = new Object();
    private Thread lockedBy = new Thread();
    private boolean isLocked;
    private int count = 0;


    public void lock() throws InterruptedException {
        synchronized (sync) {
            while (this.isLocked && Thread.currentThread() != lockedBy) {
                this.wait();
            }
            this.isLocked = true;
            this.lockedBy = Thread.currentThread();
            this.count++;
        }
    }

    public void unlock() {
        synchronized (sync) {
            if (this.isLocked || Thread.currentThread() == lockedBy) {
                this.count--;
                if (this.count == 0) {
                    this.isLocked = false;
                    this.notifyAll();
                }
            }
        }
    }
}
