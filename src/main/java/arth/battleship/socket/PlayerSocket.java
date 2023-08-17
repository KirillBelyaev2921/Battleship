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

public class PlayerSocket {

    private Player player;
    private GameController controller;
    private BufferedReader reader;
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
            reader = new BufferedReader(Channels.newReader(socketChannel, StandardCharsets.UTF_8));

            System.out.println("Network established!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setPlayerName(String s, String name) {
        try {
            writer.writeObject(s);
            writer.writeObject(name);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setReady(String name, List<Battleship> battleships) {
        try {
            writer.writeObject(CommandLines.SET_PLAYERS);
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
                        case CommandLines.SHOT_RESULT ->
                                controller.shotResult(reader.readLine(), reader.readLine(), reader.readLine());
                    }
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void startGame() throws IOException {
        setReady(player.getPlayerName(), player.getBattleships());
        BattleshipFrame.getInstance().setMainPanel(new BattleshipGamePanel(this));
        controller.displayMessage("Game started!\n");
        String address = reader.readLine();
        showTurn(address);
    }

    private void showTurn(String name) {
        if (this.player.getPlayerName().equals(name)) {
            controller.displayMessage("It is your turn");
            controller.setTurn(true);
        } else {
            controller.displayMessage("This is your opponent's turn");
            controller.setTurn(false);
        }
    }
}
