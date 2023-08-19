package arth.battleship.controller;

import arth.battleship.socket.HostPlayerSocket;
import arth.battleship.gui.PlaceBattleshipsPanel;

public class CreateLobbyController extends BattleshipController {
    public void createLobby(String name) {
        HostPlayerSocket host = new HostPlayerSocket();
        setMainPanel(new PlaceBattleshipsPanel());
    }
}
