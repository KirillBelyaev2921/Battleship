package arth.battleship.controller;

import arth.battleship.constants.ShotResult;
import arth.battleship.model.Cell;
import arth.battleship.socket.PlayerSocket;
import arth.battleship.gui.BattleshipGamePanel;

public class GameController extends BattleshipController {

    private final PlayerSocket connection;
    private final BattleshipGamePanel panel;

    public GameController(PlayerSocket connection, BattleshipGamePanel panel) {
        this.panel = panel;
        this.connection = connection;
        connection.setController(this);
    }

    public void shootShip(Cell cellToShoot) {
        connection.shootShip(cellToShoot);
    }

    public void displayMessage(String readLine) {
        panel.displayResult(readLine);
    }

    public void setTurn(boolean isPlayersTurn) {
        panel.setTurn(isPlayersTurn);
    }

    public void shotResult(ShotResult shotResult) {
        if (shotResult == ShotResult.MISS) {
            displayMessage("This is your opponent's turn");
            setTurn(false);
        } else if (shotResult == ShotResult.KILL || shotResult == ShotResult.HIT) {
            displayMessage("It is your turn");
        } else {
            displayMessage("You Won!");
            setTurn(false);
        }
        panel.setEnemyCell(shotResult);
    }

    public ShotResult getShotResult(Cell cell) {
        ShotResult shotCell = connection.getPlayer().shotCell(cell);
        if (shotCell == ShotResult.MISS) {
            displayMessage("It is your turn");
            setTurn(true);
        } else if (shotCell == ShotResult.KILL || shotCell == ShotResult.HIT) {
            displayMessage("This is your opponent's turn");
        } else {
            displayMessage("You Lose");
            setTurn(false);
        }
        panel.setMyCell(shotCell.toString(), cell);
        return shotCell;
    }
}
