package ru.job4j.threads;

/**
 * @author Medoev Ruslan (mr.r.m3@icloud.com).
 * @version $Id$.
 * @since 0.1.
 */

public class Switcher {
    private String sample;
    private volatile int flag = 1;

    public Switcher(String sample) {
        this.sample = sample;
    }

    public String getString(int input) {
        sample += input;
        System.out.println(sample);
        return sample;
    }

    static class SwitcherThreadOne extends Thread {
        private final Switcher switcher;

        public SwitcherThreadOne(Switcher cycle) {
            this.switcher = cycle;
        }

        @Override
        public void run() {
            while (true) {
                synchronized (switcher) {
                    while (switcher.flag != 1) {
                        try {
                            switcher.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    for (int i = 0; i < 10; i++) {
                        switcher.getString(1);
                    }
                    switcher.flag = 2;
                    switcher.notify();
                }
            }
        }
    }

    static class SwitcherThreadTwo extends Thread {
        final Switcher switcher;

        public SwitcherThreadTwo(Switcher switcher) {
            this.switcher = switcher;
        }

        @Override
        public void run() {
            while (true) {
                synchronized (switcher) {
                    while (switcher.flag != 2) {
                        try {
                            switcher.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    for (int i = 0; i < 10; i++) {
                        switcher.getString(2);
                    }
                    switcher.flag = 1;
                    switcher.notify();
                }
            }
        }
    }
}