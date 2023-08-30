package arth.battleship.observers;

import arth.battleship.constants.CellStatus;
import arth.battleship.model.Cell;

public interface EnemyBoardObserver {
    void update(Cell cell, CellStatus cellStatus);
}
