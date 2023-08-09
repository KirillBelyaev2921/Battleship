package arth.battleship.gui;

import javax.swing.*;

public class BattleshipFrame extends JFrame {
    private static BattleshipFrame frame;

    private BattleshipFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 500);
        this.setVisible(true);
    }

    public static BattleshipFrame getInstance() {
        if (frame == null) {
            frame = new BattleshipFrame();
        }
        return frame;
    }

    public void setMainPanel(JPanel panel) {
        frame.getContentPane().removeAll();
        frame.getContentPane().add(panel);
        frame.pack();
    }

}
