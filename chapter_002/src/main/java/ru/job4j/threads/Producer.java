package ru.job4j.threads;

/**
 * @author Medoev Ruslan (mr.r.m3@icloud.com)
 * @version $Id$
 * @since 0.1
 */

public class Producer implements Runnable {

    private SimpleBlockingQueue<Integer> queue;

    public Producer(SimpleBlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 10; i++) {
                Thread.sleep(300);
                this.queue.offer(i);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
