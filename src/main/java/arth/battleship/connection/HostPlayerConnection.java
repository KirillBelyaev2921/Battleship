package arth.battleship.connection;

import arth.battleship.model.Battleship;
import arth.battleship.model.Lobby;
import arth.battleship.model.Player;
import arth.battleship.command.CommandLines;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.nio.channels.Channels;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HostPlayerConnection {
    private final ExecutorService executorService;

    private Lobby lobby;

    private List<PrintWriter> clientWriters = new ArrayList<>();

    private int playersReadyCounter;

    public HostPlayerConnection(Lobby lobby) {
        this.lobby = lobby;
        executorService = Executors.newCachedThreadPool();
        executorService.submit(new ServerHandler());
    }

    private void tellEveryone(String message) {
        for (PrintWriter writer : clientWriters) {
            writer.println(message);
            writer.flush();
        }
    }
    private void shoot(String command, String result) {
        for (PrintWriter printWriter : clientWriters) {
            printWriter.println(CommandLines.SHOT_RESULT);
            printWriter.println(result + command);
            printWriter.flush();
        }
    }

    class ServerHandler implements Runnable {
        @Override
        public void run() {
            try (ServerSocketChannel serverSocketChannel = ServerSocketChannel.open()) {
                serverSocketChannel.bind(new InetSocketAddress(5000));

                while (serverSocketChannel.isOpen()) {
                    SocketChannel clientChannel = serverSocketChannel.accept();
                    PrintWriter writer = new PrintWriter(Channels.newWriter(clientChannel, StandardCharsets.UTF_8));
                    clientWriters.add(writer);
                    executorService.submit(new ClientHandler(clientChannel));
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    class ClientHandler implements Runnable {
        private final ObjectInputStream reader;
        private String address;

        public ClientHandler(SocketChannel clientSocket) {
            try {
                address = clientSocket.getRemoteAddress().toString();
                reader = new ObjectInputStream(Channels.newInputStream(clientSocket));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public void run() {
            Object message;
            try {
                while ((message = reader.readObject()) != null) {
                    String command = (String) message;
                    switch (command) {
                        case CommandLines.READY -> {
                            playersReadyCounter++;
                            setPlayer();
                            checkPlayersIsReady();
                        }
                        case CommandLines.NOT_READY -> {
                            playersReadyCounter--;
                            checkPlayersIsReady();
                        }
                        case CommandLines.SHOOT -> {
                            String cell = (String) reader.readObject();
                            Player secondPlayer = lobby.getSecondPlayer(address);
                            String shoot = lobby.getPlayer(address).getPlayerName() +
                                    " shoot " + cell + ". Result: ";
                            if (secondPlayer.getBattleships().stream()
                                    .noneMatch(battleship -> battleship.getShipCells().stream().noneMatch(cell::equals))) {
                                shoot("Hit", shoot);
                            } else {
                                shoot("Miss", shoot);
                            }
                        }
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                System.out.println("Wrong client version!");
                throw new RuntimeException(e);
            }
        }

        private void setPlayer() {
            String name = null;
            List<Battleship> battleships = null;
            try {
                name = (String) reader.readObject();
                battleships = (List<Battleship>) reader.readObject();

            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

            Player player = new Player(name, battleships);
            lobby.addPlayer(address, player);
        }

        private void checkPlayersIsReady() {
            if (playersReadyCounter == 2) {
                tellEveryone(CommandLines.GAME_START);
            }
        }

    }
}
