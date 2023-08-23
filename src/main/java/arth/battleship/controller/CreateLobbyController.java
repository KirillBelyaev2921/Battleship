package arth.battleship.controller;

import arth.battleship.socket.HostPlayerSocket;
import arth.battleship.gui.main_panel.PlaceBattleshipsPanel;

public class CreateLobbyController extends BattleshipController {
    public void createLobby() {
        HostPlayerSocket host = new HostPlayerSocket();
        setMainPanel(new PlaceBattleshipsPanel());
    }
}
