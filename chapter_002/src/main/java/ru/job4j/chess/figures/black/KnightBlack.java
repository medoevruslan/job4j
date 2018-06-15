package ru.job4j.chess.figures.black;

import ru.job4j.chess.exceptions.ImpossibleMoveException;
import ru.job4j.chess.figures.Cell;
import ru.job4j.chess.figures.Figure;

/**
 * @author Medoev Ruslan (mr.r.m3@icloud.com).
 * @version $Id$.
 * @since 0.1.
 */
public class KnightBlack extends Figure {

    public KnightBlack(Cell position) {
        super(position);
    }

    private boolean isValidMove(Cell position, Cell dest) {
        boolean valid = false;
        if ((dest.x == position.x + 2 || dest.x == position.x - 2)
                && (dest.y == position.y + 1 || dest.y == position.y - 1)
                || (dest.y == position.y + 2 || dest.y == position.y - 2)
                && (dest.x == position.x + 1 || dest.x == position.x - 1)) {
            valid = true;
        }
        return valid;
    }

    @Override
    public Cell[] way(Cell position, Cell dest) {
        if (!isValidMove(position, dest)) {
            throw  new ImpossibleMoveException("That figure can't move that way");
        }
        return new Cell[] {dest};
    }

    @Override
    public Figure copy(Cell dest) {
        return new KnightBlack(dest);
    }
}
