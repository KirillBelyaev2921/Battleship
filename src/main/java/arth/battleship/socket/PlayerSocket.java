package arth.battleship.socket;

import arth.battleship.constants.CommandLine;
import arth.battleship.constants.ShotResult;
import arth.battleship.controller.GameController;
import arth.battleship.gui.BattleshipFrame;
import arth.battleship.gui.BattleshipGamePanel;
import arth.battleship.model.Cell;
import arth.battleship.model.Player;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.channels.Channels;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static arth.battleship.constants.CommandLine.*;

public class PlayerSocket {

    private Player player;
    private GameController controller;
    private ObjectInputStream reader;
    private ObjectOutputStream writer;

    public PlayerSocket(Player player) {
        this.player = player;
        setUpNetworking();
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(new IncomingReader());
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }


    private void setUpNetworking() {
        try {
            InetSocketAddress serverAddress = new InetSocketAddress("192.168.0.230", 5000);
            SocketChannel socketChannel = SocketChannel.open(serverAddress);

            writer = new ObjectOutputStream(Channels.newOutputStream(socketChannel));
            reader = new ObjectInputStream(Channels.newInputStream(socketChannel));

            System.out.println("Network established!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setReady() {
        try {
            writer.writeObject("asd");
            writer.writeObject("azxc");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void setNotReady() {
        try {
            writer.writeObject("asd");
            System.out.println("asd");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void shootShip(Cell cell) {
        try {
            writer.writeChars(SHOT.name());
            writer.writeObject(cell);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void setShotResult(ShotResult shotResult) {
        try {
            writer.writeChars(SET_SHOT_RESULT.name());
            writer.writeObject(shotResult);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setController(GameController gameController) {
        this.controller = gameController;
    }
    public class IncomingReader implements Runnable {
        public void run() {
            Object message;
            try {
                while ((message = reader.readObject()) != null) {
                    CommandLine command = CommandLine.valueOf((String) message);
                    switch (command) {
                        case GAME_START -> startGame();
                        case GET_SHOT_RESULT -> setShotResult(controller.getShotResult((Cell) reader.readObject()));
                        case SHOT_RESULT ->
                                controller.shotResult((ShotResult) reader.readObject());
                    }
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

    }

    private void startGame() throws IOException {
        BattleshipFrame.getInstance().setMainPanel(new BattleshipGamePanel(this));
        controller.displayMessage("Game started!\n");
        showTurn(reader.readBoolean());
    }

    private void showTurn(boolean isPlayerTurn) {
        if (isPlayerTurn) {
            controller.displayMessage("It is your turn");
            controller.setTurn(true);
        } else {
            controller.displayMessage("This is your opponent's turn");
            controller.setTurn(false);
        }
    }
}
