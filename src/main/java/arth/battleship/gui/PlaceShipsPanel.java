package arth.battleship.gui;

import arth.battleship.controller.PlaceShipsController;

import javax.swing.*;

public class PlaceShipsPanel extends JPanel {
    PlaceShipsController controller;
    JLabel playerNameLabel;
    JTextField playerName;
    JTextField texting;
    JCheckBox isReady;

    public PlaceShipsPanel() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100));
        controller = new PlaceShipsController();

        playerNameLabel = new JLabel("Enter name");
        playerName = new JTextField();
        texting = new JTextField("Just message");
        isReady = new JCheckBox("Ready");

    }
}
