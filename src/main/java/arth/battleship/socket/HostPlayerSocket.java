package arth.battleship.socket;

import arth.battleship.constants.ShotResult;
import arth.battleship.model.Cell;
import arth.battleship.constants.CommandLine;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static arth.battleship.constants.CommandLine.*;

public class HostPlayerSocket {
    private final ExecutorService executorService;
    private List<String> addresses = new ArrayList<>();
    private List<ObjectOutputStream> writers = new ArrayList<>();
    private int playersReadyCounter;


    public HostPlayerSocket() {
        executorService = Executors.newCachedThreadPool();
        executorService.submit(new ServerHandler());
    }

    private void startGame() {
        int firstTurn = new Random().nextInt(2);
        int i = 0;
        try {
            for (ObjectOutputStream writer : writers) {
                writer.writeObject(GAME_START);
                writer.writeObject(i == firstTurn);
                i++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void shoot(String address, Cell cell) {
        try {
            int writerIndexToShot = addresses.indexOf(address) == 0 ? 1 : 0;
            ObjectOutputStream writer = writers.get(writerIndexToShot);
            writer.writeObject(SHOT_PLAYER);
            writer.writeObject(cell);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void showShotResult(String address, ShotResult shotResult) {
        try {
            int writerIndexToShot = addresses.indexOf(address) == 0 ? 1 : 0;
            ObjectOutputStream writer = writers.get(writerIndexToShot);
            writer.writeObject(SHOT_RESULT);
            writer.writeObject(shotResult);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    class ServerHandler implements Runnable {

        @Override
        public void run() {
            try (ServerSocketChannel serverSocketChannel = ServerSocketChannel.open()) {
                serverSocketChannel.bind(new InetSocketAddress(5000));

                while (serverSocketChannel.isOpen()) {
                    SocketChannel clientChannel = serverSocketChannel.accept();
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
                ObjectOutputStream writer = new ObjectOutputStream(clientSocket.socket().getOutputStream());
                address = clientSocket.getRemoteAddress().toString();
                addresses.add(address);
                writers.add(writer);
                reader = new ObjectInputStream(clientSocket.socket().getInputStream());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public void run() {
            try {
                Object message;
                while ((message = reader.readObject()) != null) {
                    CommandLine command = (CommandLine) message;
                    switch (command) {
                        case READY -> {
                            playersReadyCounter++;
                            checkPlayersIsReady();
                        }
                        case NOT_READY -> playersReadyCounter--;
                        case SHOT -> {
                            Cell cell = (Cell) reader.readObject();
                            shoot(address, cell);
                        }
                        case SHOT_PLAYER_RESULT -> showShotResult(address, (ShotResult) reader.readObject());
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                System.out.println("Wrong client version!");
                throw new RuntimeException(e);
            }
        }

        private void checkPlayersIsReady() {
            if (playersReadyCounter == 2) {
                startGame();
            }
        }

    }
}
