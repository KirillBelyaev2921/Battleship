package arth.battleship.gui.main_panel;

import arth.battleship.controller.MainMenuController;

import javax.swing.*;

public class MainMenuPanel extends MainPanel {
    private MainMenuController controller;
    private JButton createLobby;
    private JButton joinLobby;
    private JButton exit;

    public MainMenuPanel() {
        controller = new MainMenuController();

        setUpComponents();
        addComponentsToPanel();
    }

    private void setUpComponents() {
        createLobby = new JButton("Create lobby");
        createLobby.addActionListener(e -> controller.createLobby());

        joinLobby = new JButton("Join lobby");
        joinLobby.addActionListener(e -> controller.chooseLobby());

        exit = new JButton("Exit");
        exit.addActionListener(e -> controller.exit());
    }

    private void addComponentsToPanel() {
        this.add(createLobby);
        this.add(joinLobby);
        this.add(exit);
    }
}
