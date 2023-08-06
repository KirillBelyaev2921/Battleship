package arth.battleship.controller;

import arth.battleship.gui.BattleshipFrame;
import arth.battleship.gui.MainMenuPanel;

public class BattleshipController {
    BattleshipFrame frame;

    public void start() {
        frame = BattleshipFrame.getInstance();
        frame.setMainPanel(new MainMenuPanel());
    }

}
