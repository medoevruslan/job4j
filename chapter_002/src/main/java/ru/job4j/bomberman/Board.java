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
        private Cell position;
        private Cell destination;

        /**
         * Method search for possible direction of object movement.
         * @param source current source of object.
         * @return direction for move.
         */

        private Cell moveDirection(Cell source) {
            boolean pass = false;
            Cell result = source;
            int[] moves = {-1, 0, 1};
            while (!pass) {
                int x = (int) (Math.random() * 3);
                int y = (int) (Math.random() * 3);
                for (Cell cell : Cell.values()) {
                    if (source.x + moves[x] == cell.x && source.y + moves[y] == cell.y) {
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
         * @param source current source of object.
         * @param dest destination to move object.
         * @return true
         */

        public boolean move(Cell source, Cell dest) {
            boolean pass = false;
            ReentrantLock lock = board[source.x][source.y];
            lock.lock();
            try {
                do {
                    this.destination = this.moveDirection(source);
                    pass = board[this.destination.x][this.destination.y].tryLock(500, TimeUnit.MILLISECONDS);
                } while (!pass);
            } catch (InterruptedException ie) {
                System.out.println(Thread.currentThread().getName() + " is interrupted");
            } finally {
                lock.unlock();
                position = destination;
            }
            return pass;
        }

        @Override
        public void run() {
            move(Cell.A1, destination);
            while (true) {
                move(position, destination);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ie) {
                    ie.printStackTrace();
                }
            }
        }
    }
}
