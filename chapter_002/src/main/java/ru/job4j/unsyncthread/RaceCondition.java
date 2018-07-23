package ru.job4j.unsyncthread;

/**
 * @author Medoev Ruslan (mr.r.m3@icloud.com).
 * @version $Id$.
 * @since 0.1.
 */

/**
 * This Class describes when "Race condition" occurs
 */

public class RaceCondition implements Runnable  {
    private int value = 0;    // More than one thread try to access to this variable

    public void increment() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.value++;
    }

    public void decrement() {
        this.value--;
    }

    public int getValue() {
        return this.value;
    }

    @Override
    public void run() {
        this.increment();
        System.out.println("Value after increment in " +
                Thread.currentThread().getName() + " " + this.getValue());
        this.decrement();
        System.out.println("Value after decrement in " +
                Thread.currentThread().getName() + " " + this.getValue());
    }

    public static void main(String[] args) {
        RaceCondition race = new RaceCondition();
        Thread tOne = new Thread(race);
        Thread tTwo = new Thread(race);
        Thread tThree = new Thread(race);
        tOne.start();
        tTwo.start();
        tThree.start();
    }
}
