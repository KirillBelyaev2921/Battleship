package arth.battleship.model;

import arth.battleship.constants.CellStatus;
import arth.battleship.constants.ShotResult;
import arth.battleship.observers.EnemyBoardObserver;


public class EnemyBoard extends Board {
    private EnemyBoardObserver observer;
    @Override
    public void shotCell(ShotResult result, Cell cell) {
        if (result == ShotResult.MISS)
            setCellStatus(cell, CellStatus.MISS);
        else
            setCellStatus(cell, CellStatus.HIT);
        if (result == ShotResult.KILL || result == ShotResult.END)
            sinkBattleship(cell.getI(), cell.getJ());
    }

    @Override
    public void notifyObservers(Cell cell, CellStatus cellStatus) {
        observer.update(cell, cellStatus);
    }

    @Override
    public void registerObserver(EnemyBoardObserver o) {
        this.observer = o;
    }
}
