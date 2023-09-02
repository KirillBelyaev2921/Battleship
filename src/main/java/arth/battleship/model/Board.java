package arth.battleship.model;

import arth.battleship.constants.CellStatus;
import arth.battleship.constants.ShotResult;
import arth.battleship.observers.EnemyBoardObserver;
import arth.battleship.observers.PlayerBoardObserver;

import java.util.List;

import static arth.battleship.constants.BattleshipGameSettings.BOARD_SIZE;


public abstract class Board {
    private final BattleshipCell[][] cells;

    public Board() {
        this.cells = new BattleshipCell[BOARD_SIZE][BOARD_SIZE];
        fillBoard();
    }

    private void fillBoard() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                cells[i][j] = new BattleshipCell(i, j);
            }
        }
    }

    public void setCellStatus(Cell cell, CellStatus cellStatus) {
        getCell(cell).setStatus(cellStatus);
        notifyObservers(cell, cellStatus);
    }

    public CellStatus getCellStatus(Cell cell) {
        return getCell(cell).getStatus();
    }

    public BattleshipCell getCell(Cell cell) {
        return cells[cell.getI()][cell.getJ()];
    }

    public abstract void shotCell(ShotResult result, Cell cell);

    protected void sinkBattleship(int i, int j) {
        if (i < 0 || j < 0 || i >= BOARD_SIZE || j >= BOARD_SIZE)
            return;
        BattleshipCell cell = getCell(new Cell(i, j));
        if (cell.getStatus() == CellStatus.EMPTY) {
            setCellStatus(cell, CellStatus.MISS);
            return;
        }
        if (cell.getStatus() != CellStatus.HIT) {
            return;
        }
        setCellStatus(cell, CellStatus.KILL);
        sinkBattleship(i - 1, j - 1);
        sinkBattleship(i - 1, j);
        sinkBattleship(i - 1, j + 1);
        sinkBattleship(i, j - 1);
        sinkBattleship(i, j + 1);
        sinkBattleship(i + 1, j - 1);
        sinkBattleship(i + 1, j);
        sinkBattleship(i + 1, j + 1);
    }

    public ShotResult getShotResult(Cell cell) {
        return null;
    }

    public abstract void notifyObservers(Cell cell, CellStatus cellStatus);

    public void registerObserver(PlayerBoardObserver o) {
    }

    public void registerObserver(EnemyBoardObserver o) {
    }

    public void setBattleships(List<Battleship> battleships) {
    }
}
