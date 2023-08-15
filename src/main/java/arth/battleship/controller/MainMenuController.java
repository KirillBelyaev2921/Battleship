package arth.battleship.controller;

import arth.battleship.gui.CreateLobbyPanel;
import arth.battleship.gui.PlaceBattleshipsPanel;

public class MainMenuController extends BattleshipController {

    public void createLobby() {
        setMainPanel(new CreateLobbyPanel());
    }

    public void chooseLobby() {
        setMainPanel(new PlaceBattleshipsPanel());
    }

    public void exit() {
        System.exit(1);
    }
}
