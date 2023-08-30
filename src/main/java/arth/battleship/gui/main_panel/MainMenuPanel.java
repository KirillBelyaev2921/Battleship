package arth.battleship.gui.main_panel;

import arth.battleship.controller.BattleshipController;

import javax.swing.*;

public class MainMenuPanel extends MainPanel {
    private final BattleshipController controller;
    private JButton createLobby;
    private JButton joinLobby;
    private JButton exit;

    public MainMenuPanel(BattleshipController controller) {
        this.controller = controller;
        setUpComponents();
        addComponentsToPanel();
    }

    private void setUpComponents() {
        createLobby = new JButton("Create lobby");
        createLobby.addActionListener(e -> controller.createLobby());

        joinLobby = new JButton("Join lobby");
        joinLobby.addActionListener(e -> controller.joinLobby());

        exit = new JButton("Exit");
        exit.addActionListener(e -> controller.exit());
    }

    private void addComponentsToPanel() {
        this.add(createLobby);
        this.add(joinLobby);
        this.add(exit);
    }

    @Override
    public void displayMessage(String[] message) {
        throw new UnsupportedOperationException();
    }
}
