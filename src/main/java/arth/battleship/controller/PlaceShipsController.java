package arth.battleship.controller;

import arth.battleship.connection.PlayerConnection;
import arth.battleship.model.Battleship;
import arth.battleship.model.Player;

import java.util.ArrayList;
import java.util.List;


public class PlaceShipsController {
    private Player player;
    private PlayerConnection playerConnection;

    public PlaceShipsController() {
        player = new Player();
        playerConnection = new PlayerConnection(player);
    }

    public String ready(boolean selected, String name, String ships) {
        if (selected) {
            Battleship battleship = new Battleship(ships.split(" "));
            playerConnection.setReady(name, List.of(battleship));
        } else {
            playerConnection.sendMessage("Not Ready");
        }

        return selected ? "Ready" : "Not ready";
    }
}
