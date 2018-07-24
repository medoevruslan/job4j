package ru.job4j.threads;

/**
 * @author Medoev Ruslan (mr.r.m3@icloud.com)
 * @version $Id$
 * @since 0.1
 */

public class Consumer implements Runnable {

    private SimpleBlockingQueue<Integer> queue;

    public Consumer(SimpleBlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (!this.queue.isEmpty()) {
            try {
                Thread.sleep(300);
                this.queue.poll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
