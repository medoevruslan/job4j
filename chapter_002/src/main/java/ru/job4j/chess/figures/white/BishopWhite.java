package ru.job4j.chess.figures.white;

import ru.job4j.chess.exceptions.ImpossibleMoveException;
import ru.job4j.chess.figures.Cell;
import ru.job4j.chess.figures.Figure;

/**
 * @author Medoev Ruslan (mr.r.m3@icloud.com).
 * @version $Id$.
 * @since 0.1.
 */
public class BishopWhite extends Figure {
    public BishopWhite(Cell position) {
        super(position);
    }

    @Override
    public Cell[] way(Cell position, Cell dest) {
        if (Math.abs(dest.x - position.x) != Math.abs(dest.y - position.y)) {
            throw new ImpossibleMoveException();
        }
        return new Cell[] {dest};
    }
    @Override
    public Figure copy(Cell dest) {
        return new BishopWhite(dest);
    }
}

