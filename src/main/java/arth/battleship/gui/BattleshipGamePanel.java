package arth.battleship.gui;

import arth.battleship.connection.PlayerConnection;
import arth.battleship.controller.GameController;

import javax.swing.*;

public class BattleshipGamePanel extends JPanel {
    GameController controller;
    JLabel gameLabel;
    JTextArea turns;
    JTextField shoot;
    JButton shootButton;

    public BattleshipGamePanel(PlayerConnection connection) {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100));
        controller = new GameController(connection, this);

        gameLabel = new JLabel("Game");
        turns = new JTextArea(10, 20);
        turns.setLineWrap(true);
        turns.setWrapStyleWord(true);
        turns.setEditable(false);
        shoot = new JTextField();
        shootButton = new JButton("Shoot");
        shootButton.addActionListener(e -> controller.shootShip(shoot.getText()));

        this.add(gameLabel);
        this.add(turns);
        this.add(shoot);
        this.add(shootButton);
    }

    public void displayResult(String readLine) {
        turns.append(readLine + "\n");
    }
}