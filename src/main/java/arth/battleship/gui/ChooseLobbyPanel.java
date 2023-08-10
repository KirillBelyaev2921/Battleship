package arth.battleship.gui;

import arth.battleship.controller.ChooseLobbyController;
import arth.battleship.model.Lobby;

import javax.swing.*;
import java.util.List;
import java.util.Vector;

public class ChooseLobbyPanel extends JPanel {
    ChooseLobbyController controller;
    private JLabel chooseLobbyLabel;
    private Lobby[] listVector;
    private JList<Lobby> lobbyList;

    public ChooseLobbyPanel() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100));
        controller = new ChooseLobbyController();

        chooseLobbyLabel = new JLabel("Choose lobby from the list");

        lobbyList = new JList<>();
        lobbyList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                Lobby selected  = lobbyList.getSelectedValue();
                if (selected != null) {
                    controller.chooseLobby(selected);
                }
            }
        });
        lobbyList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane theList = new JScrollPane(lobbyList);


        showLobbyList();
        lobbyList.setListData(listVector);

        this.add(chooseLobbyLabel);
        this.add(theList);
    }

    private void showLobbyList() {
        listVector = controller.showLobbyList();
    }
}
