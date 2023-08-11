package arth.battleship.controller;

import arth.battleship.connection.HostPlayerConnection;
import arth.battleship.connection.PlayerConnection;
import arth.battleship.gui.BattleshipFrame;
import arth.battleship.gui.PlaceShipsPanel;
import arth.battleship.model.Lobby;
import arth.battleship.model.Player;

public class CreateLobbyController {
    BattleshipFrame frame;

    public CreateLobbyController() {
        this.frame = BattleshipFrame.getInstance();
    }

    public void createLobby(String name) {
        Lobby lobby = new Lobby(name);
        HostPlayerConnection host = new HostPlayerConnection(lobby);
        frame.setMainPanel(new PlaceShipsPanel());
    }
}
