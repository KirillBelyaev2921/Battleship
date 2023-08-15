package arth.battleship;

import arth.battleship.controller.BattleshipController;
import arth.battleship.gui.MainMenuPanel;

public class BattleshipApplication {
    public static void main(String[] args) {
        new BattleshipController().setMainPanel(new MainMenuPanel());
    }
}