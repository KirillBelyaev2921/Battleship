package arth.battleship.gui.board;

import arth.battleship.constants.ShotResult;
import arth.battleship.gui.cell.BattleshipCellPanel;
import arth.battleship.gui.cell.PlayerBattleshipCellPanel;
import arth.battleship.model.Battleship;
import arth.battleship.model.Cell;

import java.awt.event.MouseListener;
import java.util.List;

import static arth.battleship.gui.cell.BattleshipCellPanel.CellStatus.HIT;
import static arth.battleship.gui.cell.BattleshipCellPanel.CellStatus.MISS;

public class PlayerBoardPanel extends BoardPanel {
    public PlayerBoardPanel() {
        super();
    }

    public void placeBattleships(List<Battleship> battleships) {
        battleships.forEach(battleship -> battleship.getShipCells()
                .forEach(cell -> getBattleshipCellPanelByCell(cell).setStatus(BattleshipCellPanel.CellStatus.SHIP)));
    }

    public void setCell(ShotResult result, Cell cell) {
        if (result == ShotResult.MISS)
            getBattleshipCellPanelByCell(cell).setStatus(MISS);
        else
            getBattleshipCellPanelByCell(cell).setStatus(HIT);
        if (result == ShotResult.KILL || result == ShotResult.WIN)
            sinkBattleship(cell.getI(), cell.getJ());
    }

    @Override
    protected BattleshipCellPanel createCellPanel(Cell cell) {
        return new PlayerBattleshipCellPanel(cell);
    }

    @Override
    protected MouseListener createMouseListener() {
        return null;
    }
}
