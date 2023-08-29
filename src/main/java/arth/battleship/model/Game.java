package arth.battleship.model;

import arth.battleship.constants.ShotResult;

public class Game {
    private boolean isPlayerTurn;
    private final Board playerBoard;
    private final Board enemyBoard;

    public Game(Board playerBoard, Board enemyBoard) {
        this.playerBoard = playerBoard;
        this.enemyBoard = enemyBoard;
    }

    public void setShotEnemyResult(ShotResult result, Cell cell) {
        enemyBoard.shotCell(result, cell);
    }
    public ShotResult setShotPlayerResult(Cell cell) {
        ShotResult result = playerBoard.getShotResult(cell);
        playerBoard.shotCell(result, cell);
        return result;
    }
}
