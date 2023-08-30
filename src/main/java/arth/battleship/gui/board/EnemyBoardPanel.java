package arth.battleship.gui.board;

import arth.battleship.constants.CellStatus;
import arth.battleship.controller.BattleshipController;
import arth.battleship.gui.cell.BattleshipCellPanel;
import arth.battleship.gui.cell.EnemyBattleshipCellPanel;
import arth.battleship.model.Cell;
import arth.battleship.observers.EnemyBoardObserver;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class EnemyBoardPanel extends BoardPanel implements EnemyBoardObserver {
    private final BattleshipController controller;

    public EnemyBoardPanel(BattleshipController controller) {
        this.controller = controller;
        controller.registerObserver(this);
    }

    @Override
    protected BattleshipCellPanel createCellPanel(Cell cell) {
        return new EnemyBattleshipCellPanel(cell);
    }

    @Override
    protected MouseListener createMouseListener() {
        return new ShipShootListener();
    }

    @Override
    public void update(Cell cell, CellStatus cellStatus) {
        getBattleshipCellPanelByCell(cell).setStatus(cellStatus);
        repaint();
    }

    private class ShipShootListener implements MouseListener {
        @Override
        public void mousePressed(MouseEvent e) {
            EnemyBattleshipCellPanel cellPanel = (EnemyBattleshipCellPanel) e.getComponent();
            controller.selectEnemyCell(cellPanel.getCell());
        }

        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }
    }
}
