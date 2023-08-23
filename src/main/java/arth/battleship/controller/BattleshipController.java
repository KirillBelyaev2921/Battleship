package arth.battleship.controller;

import arth.battleship.gui.BattleshipFrame;
import arth.battleship.gui.main_panel.MainPanel;

public class BattleshipController {
    private final BattleshipFrame frame;

    public BattleshipController() {
        frame = BattleshipFrame.getInstance();
    }

    public void setMainPanel(MainPanel panel) {
        frame.setMainPanel(panel);
    }

}
