package arth.battleship.gui;

import arth.battleship.constants.ShotResult;
import arth.battleship.gui.BoardPanel.EnemyBoardPanel;
import arth.battleship.gui.BoardPanel.PlayerBoardPanel;
import arth.battleship.model.Cell;
import arth.battleship.socket.PlayerSocket;
import arth.battleship.controller.GameController;

import javax.swing.*;

public class BattleshipGamePanel extends JPanel {
    private GameController controller;
    private JLabel gameLabel;
    private JTextArea turns;
    private PlayerBoardPanel playerBoardPanel;
    private EnemyBoardPanel enemyBoardPanel;
    private JButton shootButton;

    public BattleshipGamePanel(PlayerSocket connection) {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100));
        controller = new GameController(connection, this);

        gameLabel = new JLabel("Game");
        turns = new JTextArea(10, 20);
        turns.setLineWrap(true);
        turns.setWrapStyleWord(true);
        turns.setEditable(false);
        JPanel boards = new JPanel();
        JScrollPane theList = new JScrollPane(turns);
        theList.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        theList.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        theList.setAutoscrolls(true);
        theList.setCorner(ScrollPaneConstants.LOWER_LEFT_CORNER, new JLabel());
        playerBoardPanel = new PlayerBoardPanel();
        playerBoardPanel.placeBattleships(connection.getPlayer().getBattleships());
        boards.add(playerBoardPanel);
        enemyBoardPanel = new EnemyBoardPanel();
        boards.add(enemyBoardPanel);

        shootButton = new JButton("Shoot");
        shootButton.addActionListener(e -> shootShip());

        this.add(gameLabel);
        this.add(theList);
        this.add(boards);
        this.add(shootButton);
    }

    private void shootShip() {
        Cell cellToShoot = enemyBoardPanel.getCellToShoot();
        if (cellToShoot != null)
            controller.shootShip(cellToShoot);
    }

    public void displayResult(String readLine) {
        turns.append(readLine + "\n");
    }

    public void setTurn(boolean isPlayerTurn) {
        enemyBoardPanel.setTurn(isPlayerTurn);
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