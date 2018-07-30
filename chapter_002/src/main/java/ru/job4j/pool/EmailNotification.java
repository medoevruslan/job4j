package ru.job4j.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Medoev Ruslan (mr.r.m3@icloud.com)
 * @version $Id$
 * @since 0.1
 */

public class EmailNotification {
    private final ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    private String subject;
    private String body;

    public void emailTo(User user) {
        this.subject  = "Notification for " + user.name + " to email " + user.email;
        this.body = "New event for " + user.name;
    }

    public void send(String subject, String body, String email) { }

    public void init(User user) {
        executor.submit(new Runnable() {
            @Override
            public void run() {
                emailTo(user);
                this.notifyAll();
            }
        });
        executor.submit(new Runnable() {
            @Override
            public void run() {
                while (subject == null && body == null) {
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                send(subject, body, user.email);
            }
        });
        executor.shutdown();
        while (!executor.isTerminated()) {
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
