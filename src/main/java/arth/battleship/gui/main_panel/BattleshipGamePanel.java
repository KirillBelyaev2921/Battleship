package arth.battleship.gui.main_panel;

import arth.battleship.controller.BattleshipController;
import arth.battleship.gui.board.BoardPanel;
import arth.battleship.gui.board.EnemyBoardPanel;
import arth.battleship.gui.board.PlayerBoardPanel;

import javax.swing.*;

public class BattleshipGamePanel extends MainPanel {
    private final BattleshipController controller;
    private JLabel gameLabel;
    private JLabel shotResult;
    private JLabel isTurn;
    private JPanel boards;
    private BoardPanel playerBoardPanel;
    private BoardPanel enemyBoardPanel;
    private JButton shootButton;

    public BattleshipGamePanel(BattleshipController controller) {
        this.controller = controller;

        setUpComponents();
        addComponentsToPanel();
    }

    private void setUpComponents() {
        gameLabel = new JLabel("Game");
        shotResult = new JLabel(" ");
        isTurn = new JLabel(" ");
        boards = new JPanel();

        playerBoardPanel = new PlayerBoardPanel(controller);
        boards.add(playerBoardPanel);

        enemyBoardPanel = new EnemyBoardPanel(controller);
        boards.add(enemyBoardPanel);

        shootButton = new JButton("Shoot");
        shootButton.addActionListener(e -> controller.shotEnemyCell());
    }

    private void addComponentsToPanel() {
        this.add(gameLabel);
        this.add(shotResult);
        this.add(isTurn);
        this.add(boards);
        this.add(shootButton);
    }

    @Override
    public void displayMessage(String[] message) {
        shotResult.setText(message[0]);
        isTurn.setText(message[1]);
    }
}