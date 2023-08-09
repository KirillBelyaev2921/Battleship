package arth.battleship.controller;

import arth.battleship.connection.HostPlayerConnection;
import arth.battleship.connection.PlayerConnection;
import arth.battleship.gui.BattleshipFrame;
import arth.battleship.gui.PlaceShipsPanel;

public class CreateLobbyController {
    BattleshipFrame frame;

    public CreateLobbyController() {
        this.frame = BattleshipFrame.getInstance();
    }

    public void createLobby(String text) {
        HostPlayerConnection host = new HostPlayerConnection();
        PlayerConnection player = new PlayerConnection();
        frame.setMainPanel(new PlaceShipsPanel());
    }
}
