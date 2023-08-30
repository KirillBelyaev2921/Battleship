package arth.battleship.controller;

import arth.battleship.constants.CellStatus;
import arth.battleship.constants.ShotResult;
import arth.battleship.model.*;
import arth.battleship.observers.EnemyBoardObserver;
import arth.battleship.observers.PlayerBoardObserver;

import java.util.List;

public class GameState extends FrameState {
    private final Game game;
    private final List<Battleship> battleships;
    private Cell selectedCell;

    public GameState(BattleshipController controller, List<Battleship> battleships) {
        super(controller);
        Board playerBoard = new PlayerBoard();
        Board enemyBoard = new EnemyBoard();
        this.battleships = battleships;
        this.game = new Game(playerBoard, enemyBoard);

    }

    @Override
    public void setBattleships(boolean isTurn) {
        game.setBattleships(battleships);
        setTurn("Game Start", isTurn);
    }

    @Override
    public boolean selectEnemyCell(Cell cell) {
        if (!game.isPlayerTurn() || game.getEnemyCellStatus(cell) != CellStatus.EMPTY) {
            return false;
        }
        if (selectedCell != null) {
            game.setEnemyCellStatus(selectedCell, CellStatus.EMPTY);
        }
        selectedCell = cell;
        game.setEnemyCellStatus(selectedCell, CellStatus.SELECTED);
        return true;
    }

    @Override
    public void shotShip() {
        if (selectedCell != null) {
            getController().getConnection().shootShip(selectedCell);
        }
    }

    @Override
    public ShotResult shotPlayer(Cell cell) {
        ShotResult result = game.getResultAndShotPlayer(cell);
        if (result == ShotResult.END) {
            setTurn("You Lose", false);
            return result;
        }
        setTurn("Enemy " + result.toString(), result == ShotResult.MISS);
        return result;
    }

    @Override
    public void shotEnemyResult(ShotResult result) {
        game.setShotEnemyResult(result, selectedCell);
        if (result != ShotResult.END) {
            setTurn("You " + result.toString(), result != ShotResult.MISS);
        } else {
            setTurn("You Win", false);
        }
        selectedCell = null;
    }

    @Override
    public void registerObserver(PlayerBoardObserver o) {
        game.registerObserver(o);
    }

    @Override
    public void registerObserver(EnemyBoardObserver o) {
        game.registerObserver(o);
    }

    private void setTurn(String message, boolean isTurn) {
        String turn = isTurn ? "It is your turn" : "It is enemy's turn";
        if (message.equals("You Win") || message.equals("You Lose")) {
            turn = "Game End";
        }
        getController().displayMessage(message, turn);
        game.setPlayerTurn(isTurn);
    }
}
