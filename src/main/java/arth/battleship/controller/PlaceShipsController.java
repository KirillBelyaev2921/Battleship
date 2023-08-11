package arth.battleship.controller;

import arth.battleship.connection.PlayerConnection;
import arth.battleship.model.Player;


public class PlaceShipsController {
    private Player player;
    private PlayerConnection playerConnection;

    public PlaceShipsController() {
        player = new Player();
        playerConnection = new PlayerConnection(player);
    }

    public String ready(boolean selected, String name, String ships) {
        player.setPlayerName(name + " " + ships);
        playerConnection.sendMessage(selected ? "Ready" : "Not Ready");

        return selected ? "Ready" : "Not ready";
    }
}
