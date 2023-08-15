package arth.battleship.controller;

import arth.battleship.gui.BattleshipFrame;
import arth.battleship.gui.MainMenuPanel;

import javax.swing.*;

public class BattleshipController {
    private final BattleshipFrame frame;

    public BattleshipController() {
        frame = BattleshipFrame.getInstance();
    }

    public void setMainPanel(JPanel panel) {
        frame.setMainPanel(panel);
    }

}
