package arth.battleship.connection;

import arth.battleship.model.Lobby;

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

        public ClientHandler(SocketChannel clientSocket) {
            try {
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
                        case "Ready" -> {
                            playersReadyCounter++;
                            checkPlayersIsReady();
                        }
                        case "Not Ready" -> {
                            playersReadyCounter--;
                            checkPlayersIsReady();
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

        private void checkPlayersIsReady() {
            if (playersReadyCounter == 2) {
                tellEveryone("Game ready");
            }
        }
    }
}
