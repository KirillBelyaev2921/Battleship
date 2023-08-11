package arth.battleship.model;

import arth.battleship.connection.PlayerConnection;

import java.util.ArrayList;
import java.util.List;

public class Lobby {
    private final String lobbyName;
    private final List<PlayerConnection> players;

    public Lobby(String lobbyName) {
        this.lobbyName = lobbyName;
        players = new ArrayList<>();
    }

    public void addPlayerConnection(PlayerConnection playerConnection) {
        players.add(playerConnection);
    }

    public String getLobbyName() {
        return lobbyName;
    }

    public List<PlayerConnection> getPlayers() {
        return players;
    }

    @Override
    public String toString() {
        return "Lobby: " + lobbyName;
    }
}
