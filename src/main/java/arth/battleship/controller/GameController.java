package arth.battleship.controller;

import arth.battleship.gui.BattleshipFrame;
import arth.battleship.socket.PlayerConnection;
import arth.battleship.gui.BattleshipGamePanel;

public class GameController {

    private final PlayerConnection connection;
    private final BattleshipGamePanel panel;
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

    public void displayMessage(String readLine) {
        panel.displayResult(readLine);
    }

    public void setCellToShot(int i, int j) {
        cell = CellCoordinateFormatter.numericToString(i, j);
    }

    public String getCell() {
        return cell;
    }

    public void setTurn(boolean isPlayersTurn) {
        panel.setTurn(isPlayersTurn);
    }

    public void shotResult(String command, String name, String cell, String result) {
        panel.displayResult(result + command);
        if (connection.getPlayer().getPlayerName().equals(name)) {
            if (command.equals("Miss")) {
                displayMessage("This is your opponent's turn");
                setTurn(false);
            } else if (command.equals("Kill") || command.equals("Hit")){
                displayMessage("It is your turn");
            } else {
                displayMessage("You Won!");
                setTurn(false);
            }
            panel.setEnemyCell(command, cell);
        } else {
            if (command.equals("Miss")) {
                displayMessage("It is your turn");
                setTurn(true);
            } else if (command.equals("Kill") || command.equals("Hit")){
                displayMessage("This is your opponent's turn");
            } else {
                displayMessage("You Lose");
                setTurn(false);
            }
            panel.setMyCell(command, cell);
        }
    }
}
