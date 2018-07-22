package ru.job4j.pingpong;

import javafx.scene.shape.Rectangle;

/**
 * @author Medoev Ruslan (mr.r.m3@icloud.com).
 * @version $Id$.
 * @since 0.1.
 */

public class RectangleMove implements Runnable {
    private final Rectangle rect;

    public RectangleMove(Rectangle rect) {
        this.rect = rect;
    }

    @Override
    public void run() {
        while(true) {
            try {
                while (this.rect.getX() != 290) {
                    this.rect.setX(this.rect.getX() + 1);
                    Thread.sleep(50);
                }
                while (this.rect.getX() != 0) {
                    this.rect.setX(this.rect.getX() - 1);
                    Thread.sleep(50);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
