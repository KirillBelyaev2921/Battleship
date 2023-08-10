package arth.battleship.controller;

import arth.battleship.connection.HostPlayerConnection;
import arth.battleship.connection.PlayerConnection;
import arth.battleship.gui.BattleshipFrame;
import arth.battleship.gui.PlaceShipsPanel;
import arth.battleship.model.Player;

public class CreateLobbyController {
    BattleshipFrame frame;

    public CreateLobbyController() {
        this.frame = BattleshipFrame.getInstance();
    }

    public void createLobby(String lobbyName) {
        HostPlayerConnection host = new HostPlayerConnection(lobbyName);
        Player player = new Player();
        PlayerConnection playerConnection = new PlayerConnection(player, host.getPort());
        frame.setMainPanel(new PlaceShipsPanel(host.getLobby(), playerConnection));
    }

}
