package arth.battleship.controller;

import arth.battleship.connection.HostPlayerConnection;
import arth.battleship.connection.PlayerConnection;
import arth.battleship.model.Player;

import java.util.ArrayList;

public class PlaceShipsController {
    public PlaceShipsController() {
    }

    public String ready(boolean selected, String name, String ships) {
        if (selected) {
            Player player = new Player(name, new ArrayList<>());
            PlayerConnection playerConnection = new PlayerConnection(player);
        }

        return selected ? "Ready" : "Not ready";
    }
}
