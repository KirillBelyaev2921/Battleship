package arth.battleship.controller;

import arth.battleship.socket.PlayerConnection;
import arth.battleship.gui.BattleshipGamePanel;

public class GameController {

    PlayerConnection connection;
    BattleshipGamePanel panel;
    private String cell;

    public GameController(PlayerConnection connection, BattleshipGamePanel panel) {
        this.panel = panel;
        this.connection = connection;
        connection.setController(this);
    }

    public void shootShip() {
        if (cell != null) {
            connection.shootShip(cell);
            cell = null;
        }
    }

    public void displayResult(String readLine) {
        panel.displayResult(readLine);
    }

    public void setCellToShot(int i, int j) {
        cell = CellCoordinateFormatter.numericToString(i, j);
    }

    public String getCell() {
        return cell;
    }
}
