package arth.battleship.gui.main_panel;

import arth.battleship.controller.CreateLobbyController;

import javax.swing.*;

public class CreateLobbyPanel extends MainPanel {
    private CreateLobbyController controller;
    private JLabel lobbyNameLabel;
    private JTextField lobbyName;
    private JButton createLobby;

    public CreateLobbyPanel() {
        controller = new CreateLobbyController();

        setUpComponents();
        addComponentsToPanel();
    }

    private void setUpComponents() {
        lobbyNameLabel = new JLabel("Enter lobby name: ");
        lobbyName = new JTextField();

        createLobby = new JButton("Create lobby");
        createLobby.addActionListener(e -> controller.createLobby());
    }

    private void addComponentsToPanel() {
        this.add(lobbyName);
        this.add(createLobby);
    }
}
