package arth.battleship.gui;

import arth.battleship.controller.MainMenuController;

import javax.swing.*;

public class MainMenuPanel extends JPanel {
    private MainMenuController controller;
    private JButton createLobby;
    private JButton joinLobby;
    private JButton exit;

    public MainMenuPanel() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100));
        controller = new MainMenuController();

        createLobby = new JButton("Create lobby");
        createLobby.addActionListener(e -> controller.createLobby());

        joinLobby = new JButton("Join lobby");
        joinLobby.addActionListener(e -> controller.chooseLobby());

        exit = new JButton("Exit");
        exit.addActionListener(e -> controller.exit());

        this.add(createLobby);
        this.add(joinLobby);
        this.add(exit);
    }
}
