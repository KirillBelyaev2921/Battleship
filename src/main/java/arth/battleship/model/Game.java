package arth.battleship.model;

import arth.battleship.constants.CellStatus;
import arth.battleship.constants.ShotResult;
import arth.battleship.observers.EnemyBoardObserver;
import arth.battleship.observers.PlayerBoardObserver;

import java.util.List;

public class Game {
    private boolean isPlayerTurn;
    private final Board playerBoard;
    private final Board enemyBoard;

    public Game(Board playerBoard, Board enemyBoard) {
        this.playerBoard = playerBoard;
        this.enemyBoard = enemyBoard;
    }

    public void shotEnemyCell(ShotResult result, Cell cell) {
        enemyBoard.shotCell(result, cell);
    }

    public ShotResult shotPlayerCell(Cell cell) {
        ShotResult result = playerBoard.getShotResult(cell);
        playerBoard.shotCell(result, cell);
        return result;
    }

    public void setEnemyCellStatus(Cell cell, CellStatus status) {
        enemyBoard.setCellStatus(cell, status);
    }

    public CellStatus getEnemyCellStatus(Cell cell) {
        return enemyBoard.getCellStatus(cell);
    }

    public boolean isPlayerTurn() {
        return isPlayerTurn;
    }

    public void setPlayerTurn(boolean playerTurn) {
        isPlayerTurn = playerTurn;
    }

    public void registerObserver(PlayerBoardObserver o) {
        playerBoard.registerObserver(o);
    }

    public void registerObserver(EnemyBoardObserver o) {
        enemyBoard.registerObserver(o);
    }

    public void setPlayerBattleships(List<Battleship> battleships) {
        playerBoard.setBattleships(battleships);
    }
}
