package arth.battleship.controller;

import arth.battleship.model.Cell;

public interface EnemyConnectionController {
    void setReady(boolean isReady);
    void shootShip(Cell cell);
}
