package arth.battleship.gui.board;

import arth.battleship.constants.CellStatus;
import arth.battleship.controller.BattleshipController;
import arth.battleship.gui.cell.BattleshipCellPanel;
import arth.battleship.gui.cell.PlayerBattleshipCellPanel;
import arth.battleship.model.Cell;
import arth.battleship.observers.PlayerBoardObserver;

import java.awt.event.MouseListener;

public class PlayerBoardPanel extends BoardPanel implements PlayerBoardObserver {
    public PlayerBoardPanel(BattleshipController controller) {
        super();
        controller.registerObserver(this);
    }

    @Override
    protected BattleshipCellPanel createCellPanel(Cell cell) {
        return new PlayerBattleshipCellPanel(cell);
    }

    @Override
    protected MouseListener createMouseListener() {
        return null;
    }

    @Override
    public void update(Cell cell, CellStatus cellStatus) {
        getBattleshipCellPanelByCell(cell).setStatus(cellStatus);
        repaint();
    }
}
