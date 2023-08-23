package arth.battleship.gui.cell;

import javax.swing.*;
import java.awt.*;

public class LabelCellPanel extends CellPanel {
    public LabelCellPanel(String label) {
        this.setPreferredSize(new Dimension(CELL_SIZE, CELL_SIZE));
        this.setLayout(new BorderLayout());

        JLabel jLabel = new JLabel(label);
        jLabel.setHorizontalAlignment(JLabel.CENTER);
        this.add(BorderLayout.CENTER, jLabel);
    }
}
