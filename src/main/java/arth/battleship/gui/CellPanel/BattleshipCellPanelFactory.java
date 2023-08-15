package arth.battleship.gui.CellPanel;

import arth.battleship.constants.BattleshipCellPanelType;
import arth.battleship.model.Cell;

public class BattleshipCellPanelFactory {
    public BattleshipCellPanel createBattleshipCellPanel(BattleshipCellPanelType type, Cell cell) {
        return switch (type) {
            case PLACE_BATTLESHIP_CELL_PANEL_TYPE -> new PlaceBattleshipCellPanel(cell);
            case PLAYER_BATTLESHIP_CELL_PANEL_TYPE -> new PlayerBattleshipCellPanel(cell);
            case ENEMY_BATTLESHIP_CELL_PANEL_TYPE -> new EnemyBattleshipCellPanel(cell);
        };
    }
}
