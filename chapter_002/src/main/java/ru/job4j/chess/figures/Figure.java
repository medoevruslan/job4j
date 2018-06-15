package ru.job4j.chess.figures;

import ru.job4j.chess.exceptions.ImpossibleMoveException;

public abstract class Figure {
    public final Cell position;

    public Figure(Cell position) {
        this.position = position;
    }

    protected abstract Cell[] way(Cell position, Cell dest);

    public String icon() {
        return String.format(
                "%s.png", this.getClass().getSimpleName()
        );

    }

    protected abstract Figure copy(Cell dest);

}

