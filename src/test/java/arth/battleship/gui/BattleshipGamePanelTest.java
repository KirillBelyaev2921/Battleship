package arth.battleship.gui;

import arth.battleship.model.Battleship;
import arth.battleship.model.Lobby;
import arth.battleship.model.Player;
import arth.battleship.socket.HostPlayerConnection;
import arth.battleship.socket.PlayerConnection;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.event.InputEvent;
import java.util.List;

class BattleshipGamePanelTest {

    @Test
    public void test() throws AWTException, InterruptedException {
        Lobby lobby = new Lobby("");
        List<Battleship> battleships1 = List.of(new Battleship("A1", "A2", "A3", "A4"),
                new Battleship("A8", "A9", "A10"),
                new Battleship("C1", "C2", "C3"),
                new Battleship("C5", "C6"),
                new Battleship("C8", "C9"),
                new Battleship("E5", "E6"),
                new Battleship("J1"),
                new Battleship("J3"),
                new Battleship("J5"),
                new Battleship("J6")
        );
        List<Battleship> battleships2 = List.of(new Battleship("A1", "A2", "A3", "A4"),
                new Battleship("A6", "A7", "A8"),
                new Battleship("C1", "C2", "C3"),
                new Battleship("C5", "C6"),
                new Battleship("C8", "C9"),
                new Battleship("E5", "E6"),
                new Battleship("J1"),
                new Battleship("J3"),
                new Battleship("J5"),
                new Battleship("J6")
        );
        Player player1 = new Player("Arthead", battleships1);
        Player player2 = new Player("Aska2", battleships2);


        HostPlayerConnection hostPlayerConnection = new HostPlayerConnection(lobby);
        PlayerConnection connection = new PlayerConnection(player1);
        PlayerConnection connection2 = new PlayerConnection(player2);



        BattleshipFrame.getInstance().setMainPanel(new BattleshipGamePanel(connection));

        Robot robot = new Robot();
        robot.mouseMove(600, 620);
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
        robot.mouseMove(600, 580);
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_MASK);

        Thread.sleep(2000);
    }
}