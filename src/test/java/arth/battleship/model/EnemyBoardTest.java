package arth.battleship.model;

import arth.battleship.constants.CellStatus;
import arth.battleship.constants.ShotResult;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EnemyBoardTest {

    @Test
    void shotCellAndHit() {
        EnemyBoard enemyBoard = new EnemyBoard();
        Cell cell = new Cell(0, 0);
        ShotResult result = ShotResult.HIT;

        enemyBoard.shotCell(result, cell);

        assertEquals(enemyBoard.getCellStatus(cell), CellStatus.HIT);
    }
    @Test
    void shotCellAndMiss() {
        EnemyBoard enemyBoard = new EnemyBoard();
        Cell cell = new Cell(0, 0);
        ShotResult result = ShotResult.MISS;

        enemyBoard.shotCell(result, cell);

        assertEquals(enemyBoard.getCellStatus(cell), CellStatus.MISS);
    }

    @Test
    void shotCellAndKill() {
        EnemyBoard enemyBoard = new EnemyBoard();
        Cell cell = new Cell(0, 0);
        ShotResult result = ShotResult.KILL;

        enemyBoard.shotCell(result, cell);

        assertEquals(enemyBoard.getCellStatus(cell), CellStatus.KILL);
        assertEquals(enemyBoard.getCellStatus(new Cell(0, 1)), CellStatus.MISS);
        assertEquals(enemyBoard.getCellStatus(new Cell(1, 1)), CellStatus.MISS);
        assertEquals(enemyBoard.getCellStatus(new Cell(1, 0)), CellStatus.MISS);
    }

    @Test
    void shotCellAndWin() {
        EnemyBoard enemyBoard = new EnemyBoard();
        Cell cell = new Cell(0, 0);
        ShotResult result = ShotResult.END;

        enemyBoard.shotCell(result, cell);

        assertEquals(enemyBoard.getCellStatus(cell), CellStatus.KILL);
        assertEquals(enemyBoard.getCellStatus(new Cell(0, 1)), CellStatus.MISS);
        assertEquals(enemyBoard.getCellStatus(new Cell(1, 1)), CellStatus.MISS);
        assertEquals(enemyBoard.getCellStatus(new Cell(1, 0)), CellStatus.MISS);
    }
}