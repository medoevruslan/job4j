package ru.job4j.chess.figures.black;

import ru.job4j.chess.exceptions.ImpossibleMoveException;
import ru.job4j.chess.figures.Cell;
import ru.job4j.chess.figures.Figure;

/**
 * @author Medoev Ruslan (mr.r.m3@icloud.com).
 * @version $Id$.
 * @since 0.1.
 */
public class KingBlack extends Figure {

    public KingBlack(Cell position) {
        super(position);
    }

    @Override
    public Cell[] way(Cell position, Cell dest) {
        if (Math.pow((dest.x - position.x), 2) + Math.pow((dest.y - position.y), 2) > 2) {
            throw new ImpossibleMoveException("That figure can't move that way");
        }
        return new Cell[] {dest};
    }

    @Override
    public Figure copy(Cell dest) {
        return new KingBlack(dest);
    }
}
