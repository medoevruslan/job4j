package ru.job4j.chess.figures.white;

import ru.job4j.chess.exceptions.ImpossibleMoveException;
import ru.job4j.chess.figures.Cell;
import ru.job4j.chess.figures.Figure;

/**
 * @author Medoev Ruslan (mr.r.m3@icloud.com).
 * @version $Id$.
 * @since 0.1.
 */
public class KingWhite extends Figure {
    public KingWhite(Cell position) {
        super(position);
    }

    private boolean isValidMove(Cell position, Cell dest) {
        int valid = (int) (Math.pow((dest.x - position.x), 2) + Math.pow((dest.y - position.y), 2));
        return (valid == 1 || valid == 2);
    }

    @Override
    public Cell[] way(Cell position, Cell dest) {
        if (!isValidMove(position, dest)) {
            throw new ImpossibleMoveException("That figure can't move that way");
        }
        return new Cell[] {dest};
    }

    @Override
    public Figure copy(Cell dest) {
        return new KingWhite(dest);
    }
}


