package arth.battleship.gui.board;

import arth.battleship.constants.CellStatus;
import arth.battleship.controller.BattleshipController;
import arth.battleship.gui.cell.BattleshipCellPanel;
import arth.battleship.gui.cell.PlaceBattleshipCellPanel;
import arth.battleship.model.Cell;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class PlaceBattleshipsBoardPanel extends BoardPanel {
    private BattleshipController controller;
    private JCheckBox isReadyCheckBox;

    public PlaceBattleshipsBoardPanel(JCheckBox isReadyCheckBox, BattleshipController controller) {
        super();
        this.isReadyCheckBox = isReadyCheckBox;
        this.controller = controller;
    }

    @Override
    protected BattleshipCellPanel createCellPanel(Cell cell) {
        return new PlaceBattleshipCellPanel(cell);
    }

    @Override
    protected MouseListener createMouseListener() {
        return new ShipPlaceListener();
    }

    private class ShipPlaceListener implements MouseListener {

        @Override
        public void mousePressed(MouseEvent e) {
            if (!isReadyCheckBox.isSelected()) {
                PlaceBattleshipCellPanel cellPanel = (PlaceBattleshipCellPanel) e.getComponent();
                if (cellPanel.notEmpty())
                    cellPanel.setStatus(CellStatus.EMPTY);
                else
                    cellPanel.setStatus(CellStatus.HIT);
                boolean response = controller.updateBattleshipCell(cellPanel.notEmpty(), cellPanel.getCell());
                isReadyCheckBox.setEnabled(response);
                repaint();
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
