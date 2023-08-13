package arth.battleship.gui;

import arth.battleship.constants.CommandLines;
import arth.battleship.controller.BoardController;
import arth.battleship.controller.CellCoordinateFormatter;
import arth.battleship.model.Battleship;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

public class PlaceShipsBoardPanel extends JPanel {
    private BoardController controller;
    private List<List<CellPanel>> cells;
    private JCheckBox checkBox;
    private boolean isReady;

    public PlaceShipsBoardPanel(boolean isReady) {
        this();
        this.isReady = isReady;
    }

    public PlaceShipsBoardPanel(JCheckBox checkBox) {
        this();
        this.checkBox = checkBox;
    }

    public PlaceShipsBoardPanel() {
        this.setPreferredSize(new Dimension(400, 400));
        this.setLayout(new GridLayout(11, 11));
        controller = new BoardController();

        cells = new ArrayList<>();
        List<CellPanel> cellsRow = new ArrayList<>();
        CellPanel cellPanel = new LabelCellPanel("");
        cellsRow.add(cellPanel);
        this.add(cellPanel);
        for (int i = 0; i < 10; i++) {
            cellPanel = new LabelCellPanel(Integer.toString(i + 1));
            cellsRow.add(cellPanel);
            this.add(cellPanel);
        }
        cells.add(cellsRow);
        for (int i = 0; i < 10; i++) {
            cellsRow = new ArrayList<>();
            cellPanel = new LabelCellPanel(CellCoordinateFormatter.numericToString(i, 0).substring(0, 1));;
            cellsRow.add(cellPanel);
            this.add(cellPanel);
            for (int j = 0; j < 10; j++) {
                ShipCellPanel cell = new ShipCellPanel(i, j);
                cell.addMouseListener(new ShipPlaceListener());
                cellsRow.add(cell);
                this.add(cell);
            }
            cells.add(cellsRow);
        }
    }

    public void placeBattleships(List<Battleship> battleships) {
        battleships.forEach(battleship ->
                battleship.getShipCells()
                        .forEach(s -> {
                            List<Integer> cellCoordinate = CellCoordinateFormatter.stringToNumericList(s);
                            cells.get(cellCoordinate.get(0) + 1).get(cellCoordinate.get(1)).setStatus(CellPanel.CellStatus.HIT);
                        }));
    }

    public List<Battleship> getBattleships() {
        return controller.getBattleships();
    }

    public void setReady(boolean b) {
        this.isReady = b;
    }

    public void setCell(String result, String cell) {
        List<Integer> cellCoordinate = CellCoordinateFormatter.stringToNumericList(cell);
        if (result.equals("Miss"))
            cells.get(cellCoordinate.get(0) + 1).get(cellCoordinate.get(1)).setStatus(CellPanel.CellStatus.MISS);
    }

    private class ShipPlaceListener implements MouseListener {

        @Override
        public void mousePressed(MouseEvent e) {
            if (!isReady) {
                checkBox.setEnabled(false);
                ShipCellPanel cellPanel = (ShipCellPanel) e.getComponent();
                if (cellPanel.notEmpty())
                    cellPanel.setStatus(CellPanel.CellStatus.EMPTY);
                else
                    cellPanel.setStatus(CellPanel.CellStatus.HIT);

                String response = controller.updatePlayerBattleships(cellPanel.notEmpty(), cellPanel.getI(), cellPanel.getJ());
                checkBox.setText(response.equals(CommandLines.READY) ? CommandLines.NOT_READY : response);
                checkBox.setEnabled(response.equals(CommandLines.READY));
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
