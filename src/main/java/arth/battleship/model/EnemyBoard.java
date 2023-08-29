package arth.battleship.model;

import arth.battleship.constants.CellStatus;
import arth.battleship.constants.ShotResult;


public class EnemyBoard extends Board {
    @Override
    public void shotCell(ShotResult result, Cell cell) {
        if (result == ShotResult.MISS)
            getCell(cell).setStatus(CellStatus.MISS);
        else
            getCell(cell).setStatus(CellStatus.HIT);
        if (result == ShotResult.KILL || result == ShotResult.END)
            sinkBattleship(cell.getI(), cell.getJ());
    }
}
