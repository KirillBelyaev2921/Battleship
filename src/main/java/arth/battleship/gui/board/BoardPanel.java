package arth.battleship.gui.board;

import arth.battleship.gui.cell.BattleshipCellPanel;
import arth.battleship.gui.cell.LabelCellPanel;
import arth.battleship.model.Cell;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import static arth.battleship.constants.BattleshipGameSettings.BOARD_SIZE;
import static arth.battleship.gui.cell.BattleshipCellPanel.CellStatus.*;

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

    public List<List<BattleshipCellPanel>> getCells() {
        return cells;
    }

    public BattleshipCellPanel getBattleshipCellPanelByCell(Cell cell) {
        return cells.get(cell.getI()).get(cell.getJ());
    }

    public void sinkBattleship(int i, int j) {
        if (i < 0 || j < 0 || i >= BOARD_SIZE || j >= BOARD_SIZE)
            return;
        BattleshipCellPanel cell = getBattleshipCellPanelByCell(new Cell(i, j));
        if (cell.getStatus() == EMPTY) {
            cell.setStatus(MISS);
            return;
        }
        if (cell.getStatus() != HIT) {
            return;
        }
        cell.setStatus(KILL);
        sinkBattleship(i - 1, j - 1);
        sinkBattleship(i - 1, j);
        sinkBattleship(i - 1, j + 1);
        sinkBattleship(i, j - 1);
        sinkBattleship(i, j + 1);
        sinkBattleship(i + 1, j - 1);
        sinkBattleship(i + 1, j);
        sinkBattleship(i + 1, j + 1);
    }

    protected abstract BattleshipCellPanel createCellPanel(Cell cell);

    protected abstract MouseListener createMouseListener();
}
