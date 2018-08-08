package ru.job4j.pool;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Medoev Ruslan (mr.r.m3@icloud.com)
 * @version $Id$
 * @since 0.1
 */

public class EmailNotification {
    private final ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    private boolean readyTo = false;
    private String subject;
    private String body;

    public void emailTo(User user) {
        this.subject  = "Notification for " + user.name + " to email " + user.email;
        this.body = "New event for " + user.name;
    }

    public void send(String subject, String body, String email) { }

    public void mailTo(User user) {
        executor.submit(new Runnable() {
            @Override
            public void run() {
                emailTo(user);
                readyTo = true;
                this.notifyAll();
            }
        });
        executor.submit(new Runnable() {
            @Override
            public void run() {
                while (!readyTo) {
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                send(subject, body, user.email);
                readyTo = false;
            }
        });
    }

    public void shutdown() {
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
