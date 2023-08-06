package arth.battleship.controller;

import arth.battleship.connection.HostPlayerConnection;
import arth.battleship.connection.PlayerConnection;

public class MainMenuController {
    public void createLobby() {
        HostPlayerConnection host = new HostPlayerConnection();
    }

    public void chooseLobby() {
        PlayerConnection player = new PlayerConnection();
    }

    public void exit() {
        System.exit(1);
    }
}
