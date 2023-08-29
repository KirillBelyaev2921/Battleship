package arth.battleship.model;

import arth.battleship.constants.CellStatus;

public class BattleshipCell extends Cell {
    private CellStatus status;
    public BattleshipCell(int i, int j) throws IndexOutOfBoundsException, NumberFormatException {
        super(i, j);
        status = CellStatus.EMPTY;
    }

    public CellStatus getStatus() {
        return status;
    }

    public void setStatus(CellStatus status) {
        this.status = status;
    }
}
