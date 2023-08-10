package arth.battleship.connection;

import arth.battleship.database.SessionFactoryProvider;
import arth.battleship.model.Lobby;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

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
    private ExecutorService executorService = Executors.newCachedThreadPool();
    private int port = 5000;
    private Lobby lobby;
    private List<PrintWriter> clientWriters = new ArrayList<>();

    public HostPlayerConnection(String lobbyName) {
        this.lobby = new Lobby(lobbyName, port);

        Thread thread = new Thread(new ServerHandler());
        thread.start();
    }
    private void saveLobbyToDatabase() {
        SessionFactory sessionFactory = SessionFactoryProvider.provideSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.save(lobby);
        transaction.commit();

        sessionFactory.close();
    }

    private void deleteLobbyFromDatabase() {
        SessionFactory sessionFactory = SessionFactoryProvider.provideSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.remove(lobby);
        transaction.commit();

        sessionFactory.close();
    }

    private void tellEveryone(String message) {
        for (PrintWriter writer : clientWriters) {
            writer.println(message);
            writer.flush();
        }
    }

    public Lobby getLobby() {
        return lobby;
    }
    class ServerHandler implements Runnable {
        @Override
        public void run() {
            saveLobbyToDatabase();
            try (ServerSocketChannel serverSocketChannel = ServerSocketChannel.open()) {
                serverSocketChannel.bind(new InetSocketAddress(port));

                while (serverSocketChannel.isOpen()) {
                    SocketChannel clientChannel = serverSocketChannel.accept();
                    PrintWriter writer = new PrintWriter(Channels.newWriter(clientChannel, StandardCharsets.UTF_8));
                    clientWriters.add(writer);
                    executorService.submit(new ClientHandler(clientChannel));
                    if (Thread.currentThread().isInterrupted()) {
                        deleteLobbyFromDatabase();
                    }
                }
                deleteLobbyFromDatabase();
            } catch (IOException e) {
                port += 1;
                lobby.setPort(port);
                executorService.submit(new ServerHandler());
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

    public int getPort() {
        return port;
    }
}
