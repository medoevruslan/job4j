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
        int x = 1;
        int y = 1;
        while (true) {
            try {
                if (Thread.interrupted()) {
                    break;
                }
                this.rect.setX(this.rect.getX() + x);
                this.rect.setY(this.rect.getY() + y);
                if (this.rect.getX() == 290) {
                    x = -(int) (Math.random() * 3);
                } else if (this.rect.getX() == 0) {
                    x = (int) (Math.random() * 3);
                } else if (this.rect.getY() == 290) {
                    y = -(int) (Math.random() * 3);
                } else if (this.rect.getY() == 0) {
                    y = (int) (Math.random() * 3);
                }
                Thread.sleep(50);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
