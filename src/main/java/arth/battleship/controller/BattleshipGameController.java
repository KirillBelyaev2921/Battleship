package arth.battleship.controller;

import arth.battleship.constants.ShotResult;
import arth.battleship.model.Cell;
import arth.battleship.socket.PlayerSocket;
import arth.battleship.gui.main_panel.BattleshipGamePanel;

import static arth.battleship.constants.ShotResult.MISS;
import static arth.battleship.constants.ShotResult.WIN;

public class BattleshipGameController extends BattleshipController {

    private final PlayerSocket connection;
    private final BattleshipGamePanel panel;

    public BattleshipGameController(PlayerSocket connection, BattleshipGamePanel panel) {
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
        displayMessage(shotResult.toString());
        if (shotResult == MISS || shotResult == WIN) {
            setTurn(false);
        }
        panel.setEnemyCell(shotResult);
    }

    public ShotResult getShotResult(Cell cell) {
        ShotResult shotResult = connection.getPlayer().shotCell(cell);
        displayMessage(shotResult.toString());
        if (shotResult == MISS) {
            setTurn(true);
        } else if (shotResult == WIN) {
            displayMessage("Lose");
            setTurn(false);
        }
        panel.setMyCell(shotResult, cell);
        return shotResult;
    }
}
