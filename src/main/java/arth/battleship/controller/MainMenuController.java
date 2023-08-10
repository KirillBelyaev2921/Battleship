package arth.battleship.controller;

import arth.battleship.connection.HostPlayerConnection;
import arth.battleship.connection.PlayerConnection;
import arth.battleship.gui.BattleshipFrame;
import arth.battleship.gui.ChooseLobbyPanel;
import arth.battleship.gui.CreateLobbyPanel;

public class MainMenuController {
    BattleshipFrame frame;

    public MainMenuController() {
        this.frame = BattleshipFrame.getInstance();
    }

    public void createLobby() {
        frame.setMainPanel(new CreateLobbyPanel());
    }

    public void chooseLobby() {
        frame.setMainPanel(new ChooseLobbyPanel());
    }

    public void exit() {
        System.exit(1);
    }
}
