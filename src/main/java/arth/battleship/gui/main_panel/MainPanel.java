package arth.battleship.gui.main_panel;

import javax.swing.*;

public abstract class MainPanel extends JPanel {
    public MainPanel() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100));
    }

    public abstract void displayMessage(String[] message);
}
