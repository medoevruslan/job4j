package ru.job4j.chess.figures.black;

import ru.job4j.chess.exceptions.ImpossibleMoveException;
import ru.job4j.chess.figures.Cell;
import ru.job4j.chess.figures.Figure;

/**
 * @author Medoev Ruslan (mr.r.m3@icloud.com).
 * @version $Id$.
 * @since 0.1.
 */
public class RookBlack extends Figure {


    public RookBlack(Cell position) {
        super(position);
    }

    private Cell moveTo(Cell position, Cell dest) {
        Cell rst = position;
        int x = (int) Math.signum(dest.x - position.x);
        int y = (int) Math.signum(dest.y - position.y);
        for (Cell cell : Cell.values()) {
            if (rst.x + x == cell.x && rst.y + y == cell.y) {
                rst = cell;
                break;
            }
        }
        return rst;
    }

    @Override
    public Cell[] way(Cell source, Cell dest) {
        if (dest.x != position.x && dest.y != position.y) {
            throw new ImpossibleMoveException("That figure can't move that way");
        }
        Cell[] cells = new Cell[Math.abs(dest.x - position.x)];
        Cell nextCell = moveTo(position, dest);
        for (int index = 0; index < cells.length ; index++) {
            cells[index] = nextCell;
            if (!nextCell.equals(dest)) {
                nextCell = moveTo(nextCell, dest);
            }
        }
        return cells;
    }

    @Override
    public Figure copy(Cell dest) {
        return new RookBlack(dest);
    }
}
