package arth.battleship.controller;

import arth.battleship.socket.HostPlayerConnection;
import arth.battleship.gui.PlaceBattleshipsPanel;
import arth.battleship.model.Lobby;

public class CreateLobbyController extends BattleshipController {
    public void createLobby(String name) {
        Lobby lobby = new Lobby(name);
        HostPlayerConnection host = new HostPlayerConnection(lobby);
        setMainPanel(new PlaceBattleshipsPanel());
    }
}
