package arth.battleship.observers;

import arth.battleship.constants.CellStatus;
import arth.battleship.model.Cell;

public interface PlayerBoardObserver {
    void update(Cell cell, CellStatus cellStatus);
}
