package ru.job4j.chess.figures.white;

import ru.job4j.chess.exceptions.ImpossibleMoveException;
import ru.job4j.chess.figures.Cell;
import ru.job4j.chess.figures.Figure;

/**
 * @author Medoev Ruslan (mr.r.m3@icloud.com).
 * @version $Id$.
 * @since 0.1.
 */
public class KnightWhite extends Figure {


    public KnightWhite(Cell position) {
        super(position);
    }

    private boolean isValidMove(Cell position, Cell dest) {
        boolean valid = true;
        if ((dest.x != position.x + 2 || dest.x != position.x - 2)
                && (dest.y != position.y + 1 || dest.y != position.y - 1)
                && (dest.y != position.y + 2 || dest.y != position.y - 2)
                && (dest.x != position.x + 1 || dest.x != position.x - 1)) {
            valid = false;
        }
        return valid;
    }


    @Override
    public Cell[] way(Cell source, Cell dest) {
        if (!isValidMove(position, dest)) {
            throw  new ImpossibleMoveException("That figure can't move that way");
        }
        return new Cell[] {dest};
    }

    @Override
    public Figure copy(Cell dest) {
        return new KnightWhite(dest);
    }
}
