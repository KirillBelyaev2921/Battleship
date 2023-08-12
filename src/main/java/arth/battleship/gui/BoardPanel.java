package arth.battleship.gui;

import arth.battleship.controller.BoardController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

public class BoardPanel extends JPanel {
    private BoardController controller;
    private List<List<CellPanel>> cells;

    public BoardPanel() {
        this.setLayout(new GridLayout(10, 10));
        controller = new BoardController();

        cells = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            List<CellPanel> cellsRow = new ArrayList<>();
            for (int j = 0; j < 10; j++) {
                CellPanel cell = new CellPanel(i, j);
                cell.addMouseListener(new ShipPlaceListener());
                cellsRow.add(cell);
                this.add(cell);
            }
            cells.add(cellsRow);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        Rectangle2D border = new Rectangle2D.Double(0, 0, getWidth() - 0.1, getHeight() - 1);
        g2d.draw(border);
    }

    private class ShipPlaceListener implements MouseListener {

        @Override
        public void mousePressed(MouseEvent e) {
            CellPanel cellPanel = (CellPanel) e.getComponent();
            cellPanel.setShip(!cellPanel.isShip());

            controller.updatePlayerBattleships(cellPanel.isShip(), cellPanel.getI(), cellPanel.getJ());
            repaint();
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
