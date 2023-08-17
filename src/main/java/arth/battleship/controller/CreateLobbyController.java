package arth.battleship.controller;

import arth.battleship.socket.HostPlayerSocket;
import arth.battleship.gui.PlaceBattleshipsPanel;
import arth.battleship.model.Lobby;

public class CreateLobbyController extends BattleshipController {
    public void createLobby(String name) {
        Lobby lobby = new Lobby(name);
        HostPlayerSocket host = new HostPlayerSocket(lobby);
        setMainPanel(new PlaceBattleshipsPanel());
    }
}
