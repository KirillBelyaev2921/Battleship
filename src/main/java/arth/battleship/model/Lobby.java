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

    public Lobby() {
    }

    public Lobby(String name, int port) {
        this.lobbyName = name;
        this.port = port;
    }

    public Lobby(String name, PlayerConnection player, int port) {
        this.lobbyName = name;
        this.playerOne = player;
        this.port = port;
    }

    public void setPlayerOne(PlayerConnection playerOne) {
        this.playerOne = playerOne;
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

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    @Override
    public String toString() {
        return "Lobby: " + lobbyName;
    }
}
