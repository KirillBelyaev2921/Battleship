package arth.battleship.gui.main_panel;

import arth.battleship.constants.ShotResult;
import arth.battleship.gui.board.EnemyBoardPanel;
import arth.battleship.gui.board.PlayerBoardPanel;
import arth.battleship.model.Cell;
import arth.battleship.socket.PlayerSocket;
import arth.battleship.controller.BattleshipGameController;

import javax.swing.*;

public class BattleshipGamePanel extends MainPanel {
    private BattleshipGameController controller;
    private JLabel gameLabel;
    private JLabel shotResult;
    private JLabel isTurn;
    private JPanel boards;
    private PlayerBoardPanel playerBoardPanel;
    private EnemyBoardPanel enemyBoardPanel;
    private JButton shootButton;

    public BattleshipGamePanel(PlayerSocket connection) {
        controller = new BattleshipGameController(connection, this);

        setUpComponents(connection);
        addComponentsToPanel();
    }

    private void setUpComponents(PlayerSocket connection) {
        gameLabel = new JLabel("Game");
        shotResult = new JLabel(" ");
        isTurn = new JLabel(" ");
        boards = new JPanel();

        playerBoardPanel = new PlayerBoardPanel();
        playerBoardPanel.placeBattleships(connection.getPlayer().getBattleships());
        boards.add(playerBoardPanel);

        enemyBoardPanel = new EnemyBoardPanel();
        boards.add(enemyBoardPanel);

        shootButton = new JButton("Shoot");
        shootButton.addActionListener(e -> shootShip());
    }

    private void addComponentsToPanel() {
        this.add(gameLabel);
        this.add(shotResult);
        this.add(isTurn);
        this.add(boards);
        this.add(shootButton);
    }

    private void shootShip() {
        Cell cellToShoot = enemyBoardPanel.getCellToShoot();
        if (cellToShoot != null)
            controller.shootShip(cellToShoot);
    }

    public void displayResult(String readLine) {
        shotResult.setText(readLine);
    }

    public void setTurn(boolean isPlayerTurn) {
        enemyBoardPanel.setTurn(isPlayerTurn);
        isTurn.setText(isPlayerTurn ? "It is your turn" : "It is enemy's turn");
    }

    public void setEnemyCell(ShotResult result) {
        enemyBoardPanel.setCell(result);
        repaint();
    }

    public void setMyCell(ShotResult result, Cell cell) {
        playerBoardPanel.setCell(result, cell);
        repaint();
    }
}