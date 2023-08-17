package arth.battleship.gui.BoardPanel;

import arth.battleship.constants.BattleshipCellPanelType;
import arth.battleship.constants.CommandLine;
import arth.battleship.controller.PlaceBattleshipsController;
import arth.battleship.gui.CellPanel.BattleshipCellPanel;
import arth.battleship.gui.CellPanel.PlaceBattleshipCellPanel;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class PlaceBattleshipsBoardPanel extends BoardPanel {
    private PlaceBattleshipsController controller;
    private JCheckBox isReadyCheckBox;

    public PlaceBattleshipsBoardPanel(JCheckBox isReadyCheckBox, PlaceBattleshipsController controller) {
        super(BattleshipCellPanelType.PLACE_BATTLESHIP_CELL_PANEL_TYPE);
        this.controller = controller;
        this.isReadyCheckBox = isReadyCheckBox;
        getCells()
                .forEach(cellsRow -> cellsRow
                        .forEach(cell -> cell.addMouseListener(new ShipPlaceListener())));
    }


    /*
    public void setCell(String result, String cell) {
        List<Integer> cellCoordinate = CellCoordinateFormatter.stringToNumericList(cell);
        if (result.equals("Miss"))
            cells.get(cellCoordinate.get(0) + 1).get(cellCoordinate.get(1)).setStatus(CellPanel.CellStatus.MISS);
    }*/

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
                isReadyCheckBox.setText(response.equals(CommandLine.READY.toString()) ? CommandLine.NOT_READY.toString() : response);
                isReadyCheckBox.setEnabled(response.equals(CommandLine.READY.toString()));
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
