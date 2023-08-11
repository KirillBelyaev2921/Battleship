package arth.battleship.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Lobby {
    private final String lobbyName;
    private final Map<String, Player> players;

    public Lobby(String lobbyName) {
        this.lobbyName = lobbyName;
        players = new HashMap<>();
    }

    public void addPlayer(String address,Player player) {
        players.put(address, player);
    }

    public String getLobbyName() {
        return lobbyName;
    }

    public Player getPlayer(String address) {
        return players.get(address);
    }

    public Map<String, Player> getPlayers() {
        return players;
    }

    @Override
    public String toString() {
        return "Lobby: " + lobbyName;
    }
}
