package arth.battleship.gui;

import arth.battleship.constants.CommandLines;
import arth.battleship.controller.PlaceShipsController;

import javax.swing.*;

public class PlaceShipsPanel extends JPanel {
    PlaceShipsController controller;
    JLabel playerNameLabel;
    JTextField playerName;
    PlaceShipsBoardPanel board;
    JCheckBox isReady;

    public PlaceShipsPanel() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100));
        controller = new PlaceShipsController();

        playerNameLabel = new JLabel("Enter name");
        playerName = new JTextField();
        isReady = new JCheckBox(CommandLines.NOT_READY);
        isReady.addActionListener(e -> ready());
        isReady.setEnabled(false);
        board = new PlaceShipsBoardPanel(isReady);

        this.add(playerNameLabel);
        this.add(playerName);
        this.add(board);
        this.add(isReady);
    }

    private void ready() {
        boolean isPlayerReady = isReady.isSelected();
        isReady.setText(controller.ready(isPlayerReady,
                playerName.getText(),
                board.getBattleships()));
        playerName.setEnabled(!isPlayerReady);
        board.setReady(isPlayerReady);
    }
}
