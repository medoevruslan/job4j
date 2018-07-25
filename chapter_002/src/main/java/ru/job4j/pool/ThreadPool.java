package ru.job4j.pool;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author Medoev Ruslan (mr.r.m3@icloud.com)
 * @version $Id$
 * @since 0.1
 */

public class ThreadPool {
    private final List<Thread> threads = new LinkedList<>();
    private final Queue<Runnable> tasks = new LinkedBlockingQueue<>();
    private boolean isRunning = true;

    public ThreadPool() {
        int size = Runtime.getRuntime().availableProcessors();
        for (int i = 0; i < size; i++) {
            this.threads.add(new Thread(new Worker()));
            threads.get(i).start();
        }
    }

    public void work(Runnable job) {
        if (this.isRunning) {
            this.tasks.offer(job);
            this.notifyAll();
        }
    }

    public void shutdown() {
           this.isRunning = false;
    }

    private class Worker implements Runnable {

        @Override
        public void run() {
            while (isRunning) {
                while (tasks.isEmpty()) {
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                tasks.poll();
            }
        }
    }
}
