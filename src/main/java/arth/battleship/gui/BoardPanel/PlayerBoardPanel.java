package arth.battleship.gui.BoardPanel;

import arth.battleship.constants.BattleshipCellPanelType;
import arth.battleship.gui.CellPanel.BattleshipCellPanel;
import arth.battleship.model.Battleship;
import arth.battleship.model.Cell;

import java.util.List;

public class PlayerBoardPanel extends BoardPanel {
    public PlayerBoardPanel() {
        super(BattleshipCellPanelType.PLAYER_BATTLESHIP_CELL_PANEL_TYPE);
    }

    public void placeBattleships(List<Battleship> battleships) {
        battleships.forEach(battleship -> battleship.getShipCells()
                .forEach(cell -> getBattleshipCellPanelByCell(cell).setStatus(BattleshipCellPanel.CellStatus.SHIP)));
    }

    public void setCell(String result, String cell) {
        Cell cellCoordinate = new Cell(cell);
        if (result.equals("Miss"))
            getBattleshipCellPanelByCell(cellCoordinate).setStatus(BattleshipCellPanel.CellStatus.MISS);
    }
}
