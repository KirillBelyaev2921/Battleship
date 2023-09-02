package arth.battleship.socket;

import arth.battleship.constants.CommandLine;
import arth.battleship.constants.ShotResult;
import arth.battleship.controller.BattleshipController;
import arth.battleship.controller.EnemyConnectionController;
import arth.battleship.model.Cell;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static arth.battleship.constants.CommandLine.*;

public class PlayerSocket implements EnemyConnectionController {

    private BattleshipController controller;
    private ObjectInputStream reader;
    private ObjectOutputStream writer;

    public PlayerSocket(BattleshipController controller) {
        this.controller = controller;
        setUpNetworking();
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(new IncomingReader());
    }

    private void setUpNetworking() {
        try {
            InetSocketAddress serverAddress = new InetSocketAddress("192.168.0.230", 5000);
            SocketChannel socketChannel = SocketChannel.open(serverAddress);

            writer = new ObjectOutputStream(socketChannel.socket().getOutputStream());
            reader = new ObjectInputStream(socketChannel.socket().getInputStream());

            System.out.println("Network established!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setReady(boolean isReady) {
        try {
            writer.writeObject(isReady ? READY : NOT_READY);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void shootShip(Cell cell) {
        try {
            writer.writeObject(SHOT);
            writer.writeObject(cell);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void setShotResult(ShotResult shotResult) {
        try {
            writer.writeObject(SHOT_PLAYER_RESULT);
            writer.writeObject(shotResult);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public class IncomingReader implements Runnable {
        public void run() {
            try {
                Object message;
                while ((message = reader.readObject()) != null) {
                    CommandLine command = (CommandLine) message;
                    switch (command) {
                        case GAME_START -> startGame();
                        case SHOT_PLAYER -> setShotResult(controller.shotPlayer((Cell) reader.readObject()));
                        case SHOT_RESULT -> controller.shotEnemyResult((ShotResult) reader.readObject());
                    }
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

    }

    private void startGame() throws IOException, ClassNotFoundException {
        controller.startGame((boolean) reader.readObject());
    }

}
