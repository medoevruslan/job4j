package ru.job4j.bomberman;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Medoev Ruslan (mr.r.m3@icloud.com)
 * @version $Id$
 * @since 0.1
 */

public class Board {
    private final ReentrantLock[][] board = new ReentrantLock[10][10];

    private Runnable bomber = new Bomberman();
    private ExecutorService service = Executors.newSingleThreadExecutor();

    public void init() {
        service.submit(bomber);
    }

    private class Bomberman implements Runnable {
        private Cell destination;

        /**
         * Method search for possible direction of object movement.
         * @param position current position of object.
         * @return direction for move.
         */

        private Cell moveDirection(Cell position) {
            boolean pass = false;
            Cell result = position;
            int[] moves = {-1, 0, 1};
            while (!pass) {
                int x = (int) (Math.random() * 3);
                int y = (int) (Math.random() * 3);
                for (Cell cell : Cell.values()) {
                    if (position.x + moves[x] == cell.x && position.y + moves[y] == cell.y) {
                        result = cell;
                        pass = true;
                        break;
                    }
                }
            }
            return result;
        }

        /**
         * Move object on empty cell.
         * @param position current position of object.
         * @return true
         */

        public boolean move(Cell position) {
            boolean pass = false;
            ReentrantLock lock = board[position.x][position.y];
            if (!lock.isHeldByCurrentThread()) {
                lock.lock();
            }
            try {
                do {
                    this.destination = this.moveDirection(position);
                    pass = board[this.destination.x][this.destination.y].tryLock(500, TimeUnit.MILLISECONDS);
                } while (!pass);
            } catch (InterruptedException ie) {
                System.out.println(Thread.currentThread().getName() + " is interrupted");
            } finally {
                lock.unlock();
            }
            return pass;
        }

        @Override
        public void run() {
            move(Cell.A1);
            while (true) {
                move(destination);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ie) {
                    ie.printStackTrace();
                }
            }
        }
    }
}
