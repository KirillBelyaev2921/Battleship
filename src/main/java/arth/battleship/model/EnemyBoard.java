package arth.battleship.model;

import arth.battleship.constants.CellStatus;
import arth.battleship.constants.ShotResult;
import arth.battleship.observers.EnemyBoardObserver;

import java.util.ArrayList;
import java.util.List;


public class EnemyBoard extends Board {
    private List<EnemyBoardObserver> observers;

    public EnemyBoard() {
        this.observers = new ArrayList<>();
    }

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
        observers.forEach(o -> o.update(cell, cellStatus));
    }

    @Override
    public void registerObserver(EnemyBoardObserver o) {
        this.observers.add(o);
    }
}
