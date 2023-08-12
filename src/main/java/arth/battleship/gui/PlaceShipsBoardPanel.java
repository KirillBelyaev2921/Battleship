package arth.battleship.gui;

import arth.battleship.constants.CommandLines;
import arth.battleship.controller.BoardController;
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

    public PlaceShipsBoardPanel(JCheckBox checkBox) {
        this();
        this.checkBox = checkBox;
    }

    public PlaceShipsBoardPanel() {
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

    public List<Battleship> getBattleships() {
        return controller.getBattleships();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        Rectangle2D border = new Rectangle2D.Double(0, 0, getWidth() - 0.1, getHeight() - 1);
        g2d.draw(border);
    }

    public void setReady(boolean b) {
        this.isReady = b;
    }

    private class ShipPlaceListener implements MouseListener {

        @Override
        public void mousePressed(MouseEvent e) {
            if (!isReady) {
                checkBox.setEnabled(false);
                CellPanel cellPanel = (CellPanel) e.getComponent();
                cellPanel.setShip(!cellPanel.isShip());

                String response = controller.updatePlayerBattleships(cellPanel.isShip(), cellPanel.getI(), cellPanel.getJ());
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
