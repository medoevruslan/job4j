package ru.job4j.tictactoe;

public class Logic3T {
    private final Figure3T[][] table;

    public Logic3T(Figure3T[][] table) {
        this.table = table;
    }

    public boolean isWinnerX() {
        if ((table[0][0].hasMarkX() && table[1][1].hasMarkX() && table[2][2].hasMarkX())
                || (table[0][2].hasMarkX() && table[1][1].hasMarkX() && table[2][0].hasMarkX())) {
            return true;
        }
        for (int row = 0; row < table.length; row++) {
            if (table[row][0].hasMarkX() && table[row][1].hasMarkX() && table[row][2].hasMarkX()) {
                return true;
            }
        }
        for (int col = 0; col < table.length; col++) {
            if (table[0][col].hasMarkX() && table[1][col].hasMarkX() && table[2][col].hasMarkX()) {
                return true;
            }
        }
        return false;
    }


    public boolean isWinnerO() {
        if ((table[0][0].hasMarkO() && table[1][1].hasMarkO() && table[2][2].hasMarkO())
                || (table[0][2].hasMarkO() && table[1][1].hasMarkO() && table[2][0].hasMarkO())) {
            return true;
        }
        for (int row = 0; row < table.length; row++) {
            if (table[row][0].hasMarkO() && table[row][1].hasMarkO() && table[row][2].hasMarkO()) {
                return true;
            }
        }
        for (int col = 0; col < table.length; col++) {
            if (table[0][col].hasMarkO() && table[1][col].hasMarkO() && table[2][col].hasMarkO()) {
                return true;
            }
        }
        return false;
    }

    public boolean hasGap() {
        for (int col = 0; col < table.length; col++) {
            for (int row = 0; row < table.length; row++) {
                if (!table[col][row].hasMarkO() & !table[col][row].hasMarkX()) {
                    return true;
                }
            }
        }
        return false;
    }
}


