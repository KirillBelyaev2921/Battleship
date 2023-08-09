package arth.battleship.gui;

import arth.battleship.controller.CreateLobbyController;

import javax.swing.*;

public class CreateLobbyPanel extends JPanel {
    CreateLobbyController controller;
    JLabel lobbyNameLabel;
    JTextField lobbyName;
    JButton createLobby;

    public CreateLobbyPanel() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100));
        controller = new CreateLobbyController();

        lobbyNameLabel = new JLabel("Enter lobby name: ");
        lobbyName = new JTextField();

        createLobby = new JButton("Create lobby");
        createLobby.addActionListener(e -> controller.createLobby(lobbyName.getText()));

        this.add(lobbyName);
        this.add(createLobby);
    }
}
