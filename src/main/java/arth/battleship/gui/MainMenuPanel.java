package arth.battleship.gui;

import arth.battleship.controller.MainMenuController;

import javax.swing.*;

public class MainMenuPanel extends JPanel {

    MainMenuController controller;
    JButton createLobby;
    JButton joinLobby;
    JButton exit;

    public MainMenuPanel() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
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
