package ru.job4j.pool;

import java.util.concurrent.*;

/**
 * @author Medoev Ruslan (mr.r.m3@icloud.com)
 * @version $Id$
 * @since 0.1
 */

public class EmailNotification {
    private final ExecutorService pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    public void emailTo(User user) {
        Future<String[]> result = pool.submit(new Callable<String[]>() {
            @Override
            public String[] call() throws Exception {
                String[] message = new String[2];
                message[0] = String.format("Notification for %s to email %s ", user.name, user.email);
                message[1] = String.format("New event for %s", user.name);
                return message;
            }
        });
        pool.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    String[] mail = result.get();
                    send(mail[0], mail[1], user.email);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void send(String subject, String body, String email) { }

    public void close() {
        pool.shutdown();
        while (!pool.isTerminated()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private class User {
        private String name;
        private String email;

        public User(String name, String email) {
            this.name = name;
            this.email = email;
        }
    }
}
