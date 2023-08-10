package arth.battleship.connection;

import arth.battleship.model.Player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.nio.channels.Channels;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PlayerConnection {

    private BufferedReader reader;
    private PrintWriter writer;

    public PlayerConnection(Player player) {
        setUpNetworking();
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(new IncomingReader());
        sendMessage("123");
    }

    private void setUpNetworking() {
        try {
            InetSocketAddress serverAddress = new InetSocketAddress("192.168.0.230", 5000);
            SocketChannel socketChannel = SocketChannel.open(serverAddress);

            writer = new PrintWriter(Channels.newWriter(socketChannel, StandardCharsets.UTF_8));
            reader = new BufferedReader(Channels.newReader(socketChannel, StandardCharsets.UTF_8));

            System.out.println("Network established!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void sendMessage(String s) {
        writer.println(s);
        writer.flush();
    }

    public class IncomingReader implements Runnable {
        public void run() {
            String message;
            try {
                while ((message = reader.readLine()) != null) {
                    System.out.println("read " + message);
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
