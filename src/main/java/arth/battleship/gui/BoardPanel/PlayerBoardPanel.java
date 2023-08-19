package arth.battleship.gui.BoardPanel;

import arth.battleship.constants.BattleshipCellPanelType;
import arth.battleship.constants.ShotResult;
import arth.battleship.gui.CellPanel.BattleshipCellPanel;
import arth.battleship.model.Battleship;
import arth.battleship.model.Cell;

import java.util.List;

import static arth.battleship.gui.CellPanel.BattleshipCellPanel.CellStatus.HIT;
import static arth.battleship.gui.CellPanel.BattleshipCellPanel.CellStatus.MISS;

public class PlayerBoardPanel extends BoardPanel {
    public PlayerBoardPanel() {
        super(BattleshipCellPanelType.PLAYER_BATTLESHIP_CELL_PANEL_TYPE);
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
}
