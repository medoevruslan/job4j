package ru.job4j.threads;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Medoev Ruslan (mr.r.m3@icloud.com)
 * @version $Id$
 * @since 0.1
 */

@ThreadSafe
public class SimpleBlockingQueue<T> {

    private Queue<T> queue = new LinkedList<>();
    private int limit;
    private int size = 0;

    public SimpleBlockingQueue(int limit) {
        this.limit = limit;
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public int getSize() {
        return this.size;
    }

    @GuardedBy("this")
    public synchronized T poll() throws InterruptedException {
        while (this.queue.isEmpty()) {
            System.out.println(Thread.currentThread().getName() + " waiting to offer, queue is empty");
            this.wait();
        }
        T res = this.queue.poll();
        this.size++;
        this.notifyAll();
        System.out.println(Thread.currentThread().getName() + ": poll " + res);
        return res;
    }

    @GuardedBy("this")
    public synchronized void offer(T value) throws InterruptedException {
        while (this.queue.size() == this.limit) {
            System.out.println(Thread.currentThread().getName() + " waiting to poll, queue is full");
            this.wait();
        }
        System.out.println(Thread.currentThread().getName() + ": offer " + value);
        this.queue.offer(value);
        this.size--;
        this.notifyAll();
    }
}