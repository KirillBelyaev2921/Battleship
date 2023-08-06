package arth.battleship.connection;

import java.io.BufferedReader;
import java.io.IOException;
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
    ExecutorService executorService;

    private List<PrintWriter> clientWriters = new ArrayList<>();

    public HostPlayerConnection() {
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
                    System.out.println("oke");
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    class ClientHandler implements Runnable {
        private final BufferedReader reader;

        public ClientHandler(SocketChannel clientSocket) {
            reader = new BufferedReader(Channels.newReader(clientSocket, StandardCharsets.UTF_8));
        }

        @Override
        public void run() {
            String message;
            try {
                while ((message = reader.readLine()) != null) {
                    System.out.println("Read " + message);
                    tellEveryone(message);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
