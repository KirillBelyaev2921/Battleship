package arth.battleship.gui.CellPanel;

import arth.battleship.model.Cell;

import java.awt.*;

public abstract class BattleshipCellPanel extends CellPanel {
    private final Cell cell;
    private CellStatus status;

    public BattleshipCellPanel(Cell cell) {
        this.cell = cell;
        status = CellStatus.EMPTY;
        this.setPreferredSize(new Dimension(CELL_SIZE, CELL_SIZE));
    }

    public Cell getCell() {
        return cell;
    }

    public void setStatus(CellStatus status) {
        this.status = status;
    }

    public CellStatus getStatus() {
        return status;
    }

    public boolean notEmpty() {
        return status == CellStatus.HIT || status == CellStatus.MISS;
    }

    public enum CellStatus {
        EMPTY, SHIP, HIT, KILL, MISS, SELECTED
    }

}
