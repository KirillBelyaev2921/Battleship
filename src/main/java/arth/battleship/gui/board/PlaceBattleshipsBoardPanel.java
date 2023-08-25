package arth.battleship.gui.board;

import arth.battleship.controller.PlaceBattleshipsController;
import arth.battleship.gui.cell.BattleshipCellPanel;
import arth.battleship.gui.cell.PlaceBattleshipCellPanel;
import arth.battleship.model.Cell;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static arth.battleship.constants.CommandLine.NOT_READY;
import static arth.battleship.constants.CommandLine.READY;

public class PlaceBattleshipsBoardPanel extends BoardPanel {
    private PlaceBattleshipsController controller;
    private JCheckBox isReadyCheckBox;

    public PlaceBattleshipsBoardPanel(JCheckBox isReadyCheckBox, PlaceBattleshipsController controller) {
        super();
        this.controller = controller;
        this.isReadyCheckBox = isReadyCheckBox;
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
                isReadyCheckBox.setEnabled(false);
                PlaceBattleshipCellPanel cellPanel = (PlaceBattleshipCellPanel) e.getComponent();
                if (cellPanel.notEmpty())
                    cellPanel.setStatus(BattleshipCellPanel.CellStatus.EMPTY);
                else
                    cellPanel.setStatus(BattleshipCellPanel.CellStatus.HIT);

                String response = controller.updatePlayerBattleships(cellPanel.notEmpty(), cellPanel.getCell().getI(), cellPanel.getCell().getJ());
                isReadyCheckBox.setText(response.equals(READY.toString()) ? NOT_READY.toString() : response);
                isReadyCheckBox.setEnabled(response.equals(READY.toString()));
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
