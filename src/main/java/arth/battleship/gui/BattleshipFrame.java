package arth.battleship.gui;

import arth.battleship.controller.BattleshipController;
import arth.battleship.gui.main_panel.MainPanel;

import javax.swing.*;

public class BattleshipFrame extends JFrame {
    private final BattleshipController controller;
    private MainPanel mainPanel;

    public BattleshipFrame(BattleshipController controller) {
        setUpFrame();
        this.controller = controller;
    }

    public void setUpFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 500);
        this.setVisible(true);
    }


    public void setMainPanel(MainPanel panel) {
        mainPanel = panel;
        this.getContentPane().removeAll();
        this.getContentPane().add(panel);
        this.pack();
    }

    public void displayMessage(String[] message) {
        mainPanel.displayMessage(message);
    }



}
