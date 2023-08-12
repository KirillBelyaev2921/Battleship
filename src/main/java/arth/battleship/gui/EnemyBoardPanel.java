package arth.battleship.gui;

import arth.battleship.constants.CommandLines;
import arth.battleship.controller.BoardController;
import arth.battleship.controller.CellCoordinateFormatter;
import arth.battleship.controller.GameController;
import arth.battleship.model.Battleship;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

public class EnemyBoardPanel extends JPanel {
    private GameController controller;
    private List<List<CellPanel>> cells;
    private JCheckBox checkBox;
    private boolean isTurn = true;


    public EnemyBoardPanel(GameController controller) {
        this();
        this.controller = controller;
    }

    public EnemyBoardPanel() {
        this.setPreferredSize(new Dimension(400, 400));
        this.setLayout(new GridLayout(11, 11));

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
            cellPanel = new LabelCellPanel(CellCoordinateFormatter.numericToString(i, 0).substring(0, 1));
            cellsRow.add(cellPanel);
            this.add(cellPanel);
            for (int j = 0; j < 10; j++) {
                EnemyCellPanel cell = new EnemyCellPanel(i, j);
                cell.addMouseListener(new ShipShootListener());
                cellsRow.add(cell);
                this.add(cell);
            }
            cells.add(cellsRow);
        }
    }

    public void setReady(boolean b) {
        this.isTurn = b;
    }

    private class ShipShootListener implements MouseListener {

        @Override
        public void mousePressed(MouseEvent e) {
            if (isTurn) {
                EnemyCellPanel cellPanel = (EnemyCellPanel) e.getComponent();
                if (!cellPanel.isShot()) {

                    if (controller.getCell() != null) {
                        List<Integer> cell = CellCoordinateFormatter.stringToNumericList(controller.getCell());
                        EnemyCellPanel cellPanel1 = (EnemyCellPanel) cells.get(cell.get(0) + 2).get(cell.get(1) + 1);
                        cellPanel1.setShip(EnemyCellPanel.CellStatus.EMPTY);
                    }
                    controller.setCellToShot(cellPanel.getI() - 1, cellPanel.getJ() - 1);
                    cellPanel.setShip(EnemyCellPanel.CellStatus.SELECTED);
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
