package arth.battleship.gui.board;

import arth.battleship.gui.cell.BattleshipCellPanel;
import arth.battleship.gui.cell.LabelCellPanel;
import arth.battleship.model.Cell;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

public abstract class BoardPanel extends JPanel {
    private List<List<BattleshipCellPanel>> cells;

    public BoardPanel() {
        setUpMainPanel();
        setUpBoard();
    }

    private void setUpMainPanel() {
        this.setPreferredSize(new Dimension(400, 400));
        this.setLayout(new GridLayout(11, 11));
    }

    private void setUpBoard() {
        cells = new ArrayList<>();
        this.add(new LabelCellPanel(""));

        for (int i = 0; i < 10; i++) {
            this.add(new LabelCellPanel(Integer.toString(i + 1)));
        }

        for (int i = 0; i < 10; i++) {
            List<BattleshipCellPanel> cellsRow = new ArrayList<>();
            this.add(new LabelCellPanel(new Cell(i, 0).toString().substring(0, 1)));
            for (int j = 0; j < 10; j++) {
                BattleshipCellPanel cell = createCellPanel(new Cell(i, j));
                cell.addMouseListener(createMouseListener());
                cellsRow.add(cell);
                this.add(cell);
            }
            cells.add(cellsRow);
        }
    }
    public BattleshipCellPanel getBattleshipCellPanelByCell(Cell cell) {
        return cells.get(cell.getI()).get(cell.getJ());
    }

    protected abstract BattleshipCellPanel createCellPanel(Cell cell);

    protected abstract MouseListener createMouseListener();


}
