package arth.battleship.controller;

import arth.battleship.gui.BattleshipFrame;
import arth.battleship.gui.CreateLobbyPanel;
import arth.battleship.gui.PlaceShipsPanel;

public class MainMenuController {
    BattleshipFrame frame;

    public MainMenuController() {
        this.frame = BattleshipFrame.getInstance();
    }

    public void createLobby() {
        frame.setMainPanel(new CreateLobbyPanel());
    }

    public void chooseLobby() {
        frame.setMainPanel(new PlaceShipsPanel());
    }

    public void exit() {
        System.exit(1);
    }
}
