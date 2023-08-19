package arth.battleship.gui.CellPanel;

import arth.battleship.model.Cell;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

import static arth.battleship.gui.CellPanel.BattleshipCellPanel.CellStatus.*;

public class PlayerBattleshipCellPanel extends BattleshipCellPanel {

    public PlayerBattleshipCellPanel(Cell cell) {
        super(cell);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        Rectangle2D cell = new Rectangle2D.Double(0, 0, CELL_SIZE, CELL_SIZE);

        if (getStatus() == SHIP || getStatus() == HIT || getStatus() == KILL)
            g2d.setColor(Color.BLUE);
        else
            g2d.setColor(Color.WHITE);
        g2d.fill(cell);

        g2d.setColor(Color.BLACK);
        g2d.draw(cell);

        if (getStatus() == HIT || getStatus() == KILL) {
            g2d.setStroke(new BasicStroke(2));
            Line2D cross1 = new Line2D.Double(0, 0, CELL_SIZE, CELL_SIZE);
            Line2D cross2 = new Line2D.Double(0, CELL_SIZE, CELL_SIZE, 0);
            g2d.draw(cross1);
            g2d.draw(cross2);
        }
        if (getStatus() == MISS) {
            Ellipse2D dot = new Ellipse2D.Double(CELL_SIZE / 2 - 5, CELL_SIZE / 2 - 5, 10, 10);
            g2d.fill(dot);
        }
    }
}