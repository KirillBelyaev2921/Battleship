package arth.battleship.gui;

import javax.swing.*;

public class CellPanel extends JPanel {
    private CellStatus status;

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
        EMPTY, HIT, MISS, SELECTED
    }

}
