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
        texting = new JTextField();
        isReady = new JCheckBox("Not ready");
        isReady.addActionListener(e -> ready());

        this.add(playerNameLabel);
        this.add(playerName);
        this.add(texting);
        this.add(isReady);
    }

    private void ready() {
        boolean isPlayerReady = isReady.isSelected();
        isReady.setText(
                controller.ready(isPlayerReady,
                        playerName.getText(),
                        texting.getText()));

        playerName.setEnabled(!isPlayerReady);
        texting.setEnabled(!isPlayerReady);
    }
}
