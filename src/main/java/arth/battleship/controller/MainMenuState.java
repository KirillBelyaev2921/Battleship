package arth.battleship.controller;

import arth.battleship.gui.main_panel.MainMenuPanel;
import arth.battleship.socket.HostPlayerSocket;
import arth.battleship.socket.PlayerSocket;

public class MainMenuState extends FrameState {
    public MainMenuState(BattleshipController controller) {
        super(controller);
        getController().setMainPanel(new MainMenuPanel(controller));
    }

    @Override
    public void createLobby() {
        BattleshipController controller = getController();
        new HostPlayerSocket();
        controller.setConnection(new PlayerSocket(controller));
        controller.setState(controller.getPlaceBattleshipsState());
    }

    @Override
    public void joinLobby() {
        BattleshipController controller = getController();
        controller.setConnection(new PlayerSocket(controller));
        controller.setState(controller.getPlaceBattleshipsState());
    }

    @Override
    public void exit() {
        System.exit(1);
    }
}
