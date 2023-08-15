package arth.battleship.model;

import java.io.Serializable;

public class Cell implements Serializable {
    private final int i;
    private final int j;

    public Cell(int i, int j) {
        this.i = i;
        this.j = j;
    }

    public Cell(String cell) throws IndexOutOfBoundsException, NumberFormatException {
        this((int) cell.toUpperCase().charAt(0) - 'A', Integer.parseInt(cell.substring(1)) - 1);
    }

    public String getStringCell() {
        return Character.toString((char) i + 'A') + (j + 1);
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

}
