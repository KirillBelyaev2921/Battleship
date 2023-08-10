package arth.battleship.model;

import arth.battleship.connection.HostPlayerConnection;
import arth.battleship.connection.PlayerConnection;

import javax.persistence.*;

@Entity(name = "lobby")
public class Lobby {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "lobby_name")
    private String lobbyName;
    @Column(name = "port")
    private int port;
    @Transient
    private PlayerConnection playerOne;
    @Transient
    private PlayerConnection playerTwo;
    @Transient
    private HostPlayerConnection hostPlayerConnection;

    public Lobby() {
    }

    public Lobby(String name, HostPlayerConnection hostPlayerConnection, int port) {
        this.lobbyName = name;
        this.hostPlayerConnection = hostPlayerConnection;
        this.port = port;
    }

    public Lobby(String name, HostPlayerConnection hostPlayerConnection, PlayerConnection playerConnection, int port) {
        this.lobbyName = name;
        this.hostPlayerConnection = hostPlayerConnection;
        this.playerOne = playerConnection;
        this.port = port;
    }

    public void setPlayerTwo(PlayerConnection playerTwo) {
        this.playerTwo = playerTwo;
    }

    public String getLobbyName() {
        return lobbyName;
    }

    public PlayerConnection getPlayerOne() {
        return playerOne;
    }

    public PlayerConnection getPlayerTwo() {
        return playerTwo;
    }

    public HostPlayerConnection getHostPlayerConnection() {
        return hostPlayerConnection;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public int getPort() {
        return port;
    }

    @Override
    public String toString() {
        return "Lobby: " + lobbyName;
    }
}
