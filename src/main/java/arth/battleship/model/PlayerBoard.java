package arth.battleship.model;

import arth.battleship.constants.CellStatus;
import arth.battleship.constants.ShotResult;
import arth.battleship.observers.PlayerBoardObserver;

import java.util.List;
import java.util.Optional;

public class PlayerBoard extends Board {
    private List<Battleship> battleships;
    private PlayerBoardObserver observer;

    public PlayerBoard() {
        super();
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
    public ShotResult getShotResult(Cell cell) {
        Optional<Battleship> oBattleship = battleships.stream()
                .filter(battleship -> battleship.shotCell(cell))
                .findAny();
        if (oBattleship.isEmpty()) {
            setCellStatus(cell, CellStatus.MISS);
            return ShotResult.MISS;
        }
        Battleship battleship = oBattleship.get();
        setCellStatus(cell, CellStatus.HIT);
        if (battleship.getSize() != 0) {
            return ShotResult.HIT;
        }
        battleships.remove(battleship);
        if (battleships.size() != 0) {
            sinkBattleship(cell.getI(), cell.getJ());
            return ShotResult.KILL;
        }
        return ShotResult.END;
    }

    @Override
    public void notifyObservers(Cell cell, CellStatus cellStatus) {
        observer.update(cell, cellStatus);
    }

    @Override
    public void registerObserver(PlayerBoardObserver o) {
        this.observer = o;
    }

    public void setBattleships(List<Battleship> battleships) {
        this.battleships = battleships;
        battleships.forEach(battleship -> battleship.getShipCells().forEach(cell -> setCellStatus(cell, CellStatus.SHIP)));
    }
}
