package arth.battleship.gui;

import arth.battleship.controller.ChooseLobbyController;
import arth.battleship.controller.PlaceShipsController;

import javax.swing.*;

public class ChooseLobbyPanel extends JPanel {
    ChooseLobbyController controller;
    public ChooseLobbyPanel() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100));
        controller = new ChooseLobbyController();

    }
}
