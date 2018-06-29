package ru.job4j.chess.figures.black;

import ru.job4j.chess.exceptions.ImpossibleMoveException;
import ru.job4j.chess.figures.Cell;
import ru.job4j.chess.figures.Figure;

/**
 * @author Medoev Ruslan (mr.r.m3@icloud.com).
 * @version $Id$.
 * @since 0.1.
 */

public class BishopBlack extends Figure {

    public BishopBlack(Cell position) {
        super(position);
    }

    private Cell moveTo(Cell position, Cell dest) {
        int x = (int) Math.signum(dest.x - position.x);
        int y = (int) Math.signum(dest.y - position.y);
        for (Cell cell : Cell.values()) {
            if (position.x + x == cell.x && position.y + y == cell.y) {
                position = cell;
                break;
            }
        }
        return position;
    }

    @Override
    public Cell[] way(Cell position, Cell dest) throws ImpossibleMoveException {
        if (Math.abs(dest.x - position.x) != Math.abs(dest.y - position.y)) {
            throw new ImpossibleMoveException("That figure can't move that way");
        }
        Cell[] cells = new Cell[Math.abs(dest.x - position.x)];
        int mov = 0;
        for (int index = 0; index < cells.length; index++) {
            if (index != 0) {
                cells[index] = moveTo(cells[mov++], dest);
            }
           cells[index] = moveTo(position, dest);
        }
        return cells;
    }
    @Override
    public Figure copy(Cell dest) {
        return new BishopBlack(dest);
    }
}