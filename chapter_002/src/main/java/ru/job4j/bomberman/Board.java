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
    private ExecutorService service = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    private int enemies = 1;
    private int difficult = 10;
    private final ReentrantLock[][] board = new ReentrantLock[difficult][difficult];

    public Board(int enemies, int difficult) {
        this.enemies = enemies;
        this.difficult = difficult;
        }

    public Board() { }

    /**
     * method initializing enemies
     */

    public void init() {
        for (int i = 0; i < enemies; i++) {
            service.submit(new Enemie(Cell.values()[i]));
        }
    }

    private class Enemie implements Runnable {
        private Cell position;
        private Cell destination;

        public Enemie(Cell startCell) {
            this.position = startCell;
        }

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
                for (int idx = 0; idx < Math.pow(difficult, 2); idx++) {
                    if (source.x + moves[x] == Cell.values()[idx].x && source.y + moves[y] == Cell.values()[idx].y) {
                        result = Cell.values()[idx];
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

    private class Bomberman {

        /**
         * Player move
         * @param source current source of object.
         * @param dest destination to move object.
         * @return boolean result of move
         */

        public boolean move(Cell source, Cell dest) {
            boolean result = false;
            ReentrantLock lock =  board[source.x][source.y];
            lock.lock();
            try {
               if (board[dest.x][dest.y].tryLock(500, TimeUnit.MILLISECONDS)) {
                   lock.unlock();
                   result = true;
               }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return result;
        }
    }
}
