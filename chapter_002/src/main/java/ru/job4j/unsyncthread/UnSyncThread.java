package ru.job4j.unsyncthread;

/**
 * @author Medoev Ruslan (mr.r.m3@icloud.com).
 * @version $Id$.
 * @since 0.1.
 */

/**
 * This Class describes when "Visibility of shared object" occurs
 */

public class UnSyncThread {
    private static boolean ready = false;

    public static void main(String[] args) throws InterruptedException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    if (UnSyncThread.ready) {
                        System.out.println("Main thread changed flag. Finish working.");
                        break;
                    }
                }
            }
        }).start();
        Thread.sleep(3000);
        System.out.println("Main thread - changing flag status...");
        UnSyncThread.ready = true;
    }
}
