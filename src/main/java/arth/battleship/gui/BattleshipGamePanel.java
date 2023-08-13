package arth.battleship.gui;

import arth.battleship.socket.PlayerConnection;
import arth.battleship.controller.GameController;

import javax.swing.*;

public class BattleshipGamePanel extends JPanel {
    private GameController controller;
    private JLabel gameLabel;
    private JTextArea turns;
    private PlaceShipsBoardPanel placeShipsBoardPanel;
    private EnemyBoardPanel enemyBoardPanel;
    private JPanel boards;
    private JButton shootButton;

    public BattleshipGamePanel(PlayerConnection connection) {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100));
        controller = new GameController(connection, this);

        gameLabel = new JLabel("Game");
        turns = new JTextArea(10, 20);
        turns.setLineWrap(true);
        turns.setWrapStyleWord(true);
        turns.setEditable(false);
        JScrollPane theList = new JScrollPane(turns);
        theList.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        theList.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        theList.setAutoscrolls(true);
        theList.setCorner(ScrollPaneConstants.LOWER_LEFT_CORNER, new JLabel());
        boards = new JPanel();
        placeShipsBoardPanel = new PlaceShipsBoardPanel(true);
        placeShipsBoardPanel.placeBattleships(connection.getPlayer().getBattleships());
        boards.add(placeShipsBoardPanel);
        enemyBoardPanel = new EnemyBoardPanel(controller);
        boards.add(enemyBoardPanel);

        shootButton = new JButton("Shoot");
        shootButton.addActionListener(e -> controller.shootShip());

        this.add(gameLabel);
        this.add(theList);
        this.add(boards);
        this.add(shootButton);
    }

    public void displayResult(String readLine) {
        turns.append(readLine + "\n");
    }

    public void setTurn(boolean isPlayerTurn) {
        enemyBoardPanel.setTurn(isPlayerTurn);
    }

    public void setEnemyCell(String result, String cell) {
        enemyBoardPanel.setCell(result, cell);
        repaint();
    }

    public void setMyCell(String result, String cell) {
        placeShipsBoardPanel.setCell(result, cell);
        repaint();
    }
}