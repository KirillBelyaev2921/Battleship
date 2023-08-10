package arth.battleship.controller;

import arth.battleship.connection.PlayerConnection;
import arth.battleship.model.Lobby;

public class PlaceShipsController {
    private Lobby lobby;
    private PlayerConnection playerConnection;
    public PlaceShipsController(Lobby lobby, PlayerConnection playerConnection) {
        this.lobby = lobby;
        this.playerConnection = playerConnection;
    }

    public String ready(boolean selected, String name, String message) {
        if (selected) {
            playerConnection.sendMessage("Name: " + name + ", message: " + message);
            return "Ready";
        }
        return "Not ready";
    }
}
