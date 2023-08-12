package arth.battleship.socket;

import arth.battleship.constants.CommandLines;
import arth.battleship.controller.GameController;
import arth.battleship.gui.BattleshipFrame;
import arth.battleship.gui.BattleshipGamePanel;
import arth.battleship.model.Battleship;
import arth.battleship.model.Player;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.channels.Channels;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PlayerConnection {

    private Player player;
    private GameController controller;
    private BufferedReader reader;
    private ObjectOutputStream writer;

    public PlayerConnection(Player player) {
        this.player = player;
        setUpNetworking();
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(new IncomingReader());
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    private void setUpNetworking() {
        try {
            InetSocketAddress serverAddress = new InetSocketAddress("192.168.0.230", 5000);
            SocketChannel socketChannel = SocketChannel.open(serverAddress);

            writer = new ObjectOutputStream(Channels.newOutputStream(socketChannel));
            reader = new BufferedReader(Channels.newReader(socketChannel, StandardCharsets.UTF_8));

            System.out.println("Network established!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendMessage(String s) {
        try {
            writer.writeObject(s);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setReady(String name, List<Battleship> battleships) {
        try {
            writer.writeObject(CommandLines.READY);
            writer.writeObject(name);
            writer.writeObject(battleships);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void shootShip(String text) {
        try {
            writer.writeObject(CommandLines.SHOOT);
            writer.writeObject(text);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setController(GameController gameController) {
        this.controller = gameController;
    }

    public class IncomingReader implements Runnable {
        public void run() {
            String message;
            try {
                while ((message = reader.readLine()) != null) {
                    switch (message) {
                        case CommandLines.GAME_START -> startGame();
                        case CommandLines.SHOT_RESULT -> controller.displayResult(reader.readLine());
                        case CommandLines.SET_PLAYERS -> setReady(player.getPlayerName(), player.getBattleships());
                    }
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void startGame() {
        BattleshipFrame.getInstance().setMainPanel(new BattleshipGamePanel(this));
    }
}
