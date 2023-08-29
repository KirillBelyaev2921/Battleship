package arth.battleship.model;

import arth.battleship.constants.CellStatus;
import arth.battleship.constants.ShotResult;

import java.util.List;
import java.util.Optional;

public class PlayerBoard extends Board {
    private final List<Battleship> battleships;

    public PlayerBoard(List<Battleship> battleships) {
        super();
        this.battleships = battleships;
        battleships.forEach(battleship -> battleship.getShipCells().forEach(cell -> setCellStatus(cell, CellStatus.SHIP)));
    }

    @Override
    public void shotCell(ShotResult result, Cell cell) {
        if (result == ShotResult.MISS)
            getCell(cell).setStatus(CellStatus.MISS);
        else
            getCell(cell).setStatus(CellStatus.HIT);
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

}
