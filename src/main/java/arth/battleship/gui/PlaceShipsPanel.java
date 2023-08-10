package arth.battleship.gui;

import arth.battleship.connection.PlayerConnection;
import arth.battleship.controller.PlaceShipsController;
import arth.battleship.model.Lobby;

import javax.swing.*;

public class PlaceShipsPanel extends JPanel {
    PlaceShipsController controller;
    JLabel playerNameLabel;
    JTextField playerName;
    JTextField texting;
    JCheckBox isReady;

    public PlaceShipsPanel(Lobby lobby, PlayerConnection playerConnection) {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100));
        controller = new PlaceShipsController(lobby, playerConnection);

        playerNameLabel = new JLabel("Enter name");
        playerName = new JTextField();
        texting = new JTextField("Just message");
        isReady = new JCheckBox("Not ready");
        isReady.addActionListener(e -> isReady.setText(controller.ready(isReady.isSelected(),
                playerName.getText(),
                texting.getText())));

        this.add(playerNameLabel);
        this.add(playerName);
        this.add(texting);
        this.add(isReady);
    }
}
