package arth.battleship.gui.main_panel;

import arth.battleship.controller.PlaceBattleshipsController;
import arth.battleship.gui.board.BoardPanel;
import arth.battleship.gui.board.PlaceBattleshipsBoardPanel;

import javax.swing.*;

import static arth.battleship.constants.CommandLine.NOT_READY;

public class PlaceBattleshipsPanel extends MainPanel {
    private PlaceBattleshipsController controller;
    private JLabel placeBattleships;
    private BoardPanel board;
    private JCheckBox isReady;

    public PlaceBattleshipsPanel() {
        controller = new PlaceBattleshipsController();

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
        isReady.setText(controller.ready(isPlayerReady));
    }
}
