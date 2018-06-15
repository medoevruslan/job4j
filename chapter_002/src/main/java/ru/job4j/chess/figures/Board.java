package ru.job4j.chess.figures;

import ru.job4j.chess.exceptions.FigureNotFoundException;
import ru.job4j.chess.exceptions.ImpossibleMoveException;
import ru.job4j.chess.exceptions.OccupiedWayException;

public class Board {
    private final Figure[] figures = new Figure[32];
    private int index = 0;

    private boolean isValidWay(Cell[] cells) {
        boolean rst = true;
        for (Cell cell : cells) {
            if (findBy(cell) != -1) {
                rst = false;
                break;
            }
        }
        return rst;
    }

    public boolean move(Cell position, Cell dest) throws FigureNotFoundException, ImpossibleMoveException, OccupiedWayException {
        boolean rst = false;
        int index = this.findBy(position);
        if (index == -1) {
            throw new FigureNotFoundException("There is no figure to move");
        }
        Cell[] steps = this.figures[index].way(position, dest);
        if (!this.isValidWay(steps)) {
            throw new OccupiedWayException("Way is occupied");
        }
        if (steps.length > 0 && steps[steps.length - 1].equals(dest)) {
            rst = true;
            this.figures[index] = this.figures[index].copy(dest);
        }
        return rst;
    }

    private int findBy(Cell position) {
        int rst = -1;
        for (int index = 0; index < this.figures.length; index++) {
            if (this.figures[index] != null && this.figures[index].position.equals(position)) {
                rst = index;
                break;
            }
        }
        return rst;
    }

    public void clean() {
        for (int position = 0; position != this.figures.length; position++) {
            this.figures[position] = null;
        }
        this.index = 0;
    }

    public void add(Figure figure) {
        this.figures[this.index++] = figure;
    }
}
