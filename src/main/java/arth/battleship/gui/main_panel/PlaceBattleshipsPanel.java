package arth.battleship.gui.main_panel;

import arth.battleship.controller.BattleshipController;
import arth.battleship.gui.board.BoardPanel;
import arth.battleship.gui.board.PlaceBattleshipsBoardPanel;

import javax.swing.*;

import static arth.battleship.constants.CommandLine.NOT_READY;

public class PlaceBattleshipsPanel extends MainPanel {
    private final BattleshipController controller;
    private JLabel placeBattleships;
    private BoardPanel board;
    private JCheckBox isReady;

    public PlaceBattleshipsPanel(BattleshipController controller) {
        this.controller = controller;

        setUpComponents();
        addComponentsToPanel();
    }

    private void setUpComponents() {
        placeBattleships = new JLabel("Place Battleships");
        isReady = new JCheckBox(NOT_READY.toString());
        isReady.addActionListener(e -> ready());
        isReady.setEnabled(false);
        board = new PlaceBattleshipsBoardPanel(isReady, controller);
    }

    private void addComponentsToPanel() {
        this.add(placeBattleships);
        this.add(board);
        this.add(isReady);
    }

    private void ready() {
        boolean isPlayerReady = isReady.isSelected();
        controller.setReady(isPlayerReady);
    }

    @Override
    public void displayMessage(String[] message) {
        isReady.setText(message[0]);
    }
}
