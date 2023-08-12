package arth.battleship.gui;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

public class EnemyCellPanel extends CellPanel {
    private static final int CELL_SIZE = 30;
    private final int i;
    private final int j;
    private CellStatus status;

    public EnemyCellPanel(int i, int j) {
        this.setPreferredSize(new Dimension(CELL_SIZE, CELL_SIZE));
        this.i = i;
        this.j = j;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        Rectangle2D cell = new Rectangle2D.Double(0, 0, CELL_SIZE, CELL_SIZE);

        g2d.setColor(Color.WHITE);
        g2d.fill(cell);


        if (status == CellStatus.SELECTED)
            g2d.setColor(Color.RED);
        else
            g2d.setColor(Color.BLACK);
        g2d.draw(cell);

        if (status == CellStatus.HIT) {
            g2d.setStroke(new BasicStroke(2));
            Line2D cross1 = new Line2D.Double(0, 0, CELL_SIZE, CELL_SIZE);
            Line2D cross2 = new Line2D.Double(0, CELL_SIZE, CELL_SIZE, 0);
            g2d.draw(cross1);
            g2d.draw(cross2);
        }

        if (status == CellStatus.MISS) {
            Ellipse2D dot = new Ellipse2D.Double(CELL_SIZE/2 - 5, CELL_SIZE/2 - 5, 10, 10);
            g2d.fill(dot);
        }
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

    public boolean isShot() {
        return status == CellStatus.HIT || status == CellStatus.MISS;
    }

    public void setShip(CellStatus status) {
        this.status = status;
    }

    public enum CellStatus {
        EMPTY, HIT, MISS, SELECTED
    }
}
