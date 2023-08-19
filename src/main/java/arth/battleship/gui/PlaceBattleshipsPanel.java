package arth.battleship.gui;

import arth.battleship.controller.PlaceBattleshipsController;
import arth.battleship.gui.BoardPanel.PlaceBattleshipsBoardPanel;

import javax.swing.*;

import static arth.battleship.constants.CommandLine.NOT_READY;

public class PlaceBattleshipsPanel extends JPanel {
    private PlaceBattleshipsController controller;
    private JLabel playerNameLabel;
    private JTextField playerName;
    private PlaceBattleshipsBoardPanel board;
    private JCheckBox isReady;

    public PlaceBattleshipsPanel() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100));
        controller = new PlaceBattleshipsController();

        playerNameLabel = new JLabel("Enter name");
        playerName = new JTextField();
        isReady = new JCheckBox(NOT_READY.toString());
        isReady.addActionListener(e -> ready());
        isReady.setEnabled(false);
        board = new PlaceBattleshipsBoardPanel(isReady, controller);

        this.add(playerNameLabel);
        this.add(playerName);
        this.add(board);
        this.add(isReady);
    }

    private void ready() {
        boolean isPlayerReady = isReady.isSelected();
        isReady.setText(controller.ready(isPlayerReady,
                playerName.getText()));
        playerName.setEnabled(!isPlayerReady);
    }
}
