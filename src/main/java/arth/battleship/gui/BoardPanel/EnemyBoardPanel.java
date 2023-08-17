package arth.battleship.gui.BoardPanel;

import arth.battleship.constants.BattleshipCellPanelType;
import arth.battleship.constants.ShotResult;
import arth.battleship.controller.GameController;
import arth.battleship.gui.CellPanel.BattleshipCellPanel;
import arth.battleship.gui.CellPanel.EnemyBattleshipCellPanel;
import arth.battleship.model.Cell;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class EnemyBoardPanel extends BoardPanel {
    private GameController controller;
    private Cell cellToShoot;
    private boolean isTurn;

    public EnemyBoardPanel(GameController controller) {
        this();
        this.controller = controller;
    }

    public EnemyBoardPanel() {
        super(BattleshipCellPanelType.ENEMY_BATTLESHIP_CELL_PANEL_TYPE);

        getCells()
                .forEach(cellsRow -> cellsRow
                        .forEach(cell -> cell.addMouseListener(new ShipShootListener())));

    }

    public void setTurn(boolean b) {
        this.isTurn = b;
    }

    public void setCell(ShotResult result) {
        if (result == ShotResult.MISS)
            getBattleshipCellPanelByCell(cellToShoot).setStatus(BattleshipCellPanel.CellStatus.MISS);
        else
            getBattleshipCellPanelByCell(cellToShoot).setStatus(BattleshipCellPanel.CellStatus.HIT);
        cellToShoot = null;
    }

    public Cell getCellToShoot() {
        return cellToShoot;
    }


    private class ShipShootListener implements MouseListener {
        @Override
        public void mousePressed(MouseEvent e) {
            if (isTurn) {
                EnemyBattleshipCellPanel cellPanel = (EnemyBattleshipCellPanel) e.getComponent();
                if (!cellPanel.notEmpty()) {

                    if (cellToShoot != null) {
                        BattleshipCellPanel cellPanel1 = getBattleshipCellPanelByCell(cellToShoot);
                        cellPanel1.setStatus(BattleshipCellPanel.CellStatus.EMPTY);
                    }
                    cellToShoot = cellPanel.getCell();
                    cellPanel.setStatus(EnemyBattleshipCellPanel.CellStatus.SELECTED);
                    repaint();
                }
            }
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
