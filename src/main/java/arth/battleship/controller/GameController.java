package arth.battleship.controller;

import arth.battleship.connection.PlayerConnection;
import arth.battleship.gui.BattleshipGamePanel;

public class GameController {

    PlayerConnection connection;
    BattleshipGamePanel panel;

    public GameController(PlayerConnection connection, BattleshipGamePanel panel) {
        this.panel = panel;
        this.connection = connection;
        connection.setController(this);
    }

    public void shootShip(String text) {
        connection.shootShip(text);
    }

    public void displayResult(String readLine) {
        panel.displayResult(readLine);
    }
}
