package arth.battleship.controller;

import arth.battleship.constants.CommandLines;
import arth.battleship.socket.PlayerConnection;
import arth.battleship.model.Battleship;
import arth.battleship.model.Player;

import java.util.List;


public class PlaceShipsController {
    private Player player;
    private PlayerConnection playerConnection;

    public PlaceShipsController() {
        player = new Player();
        playerConnection = new PlayerConnection(player);
    }

    public String ready(boolean selected, String name, List<Battleship> battleships) {
        if (selected) {
            playerConnection.setPlayer(new Player(name, battleships));
            playerConnection.setPlayerName(CommandLines.READY, name);
        } else {
            playerConnection.setPlayerName(CommandLines.NOT_READY, name);
        }

        return selected ? CommandLines.READY : CommandLines.NOT_READY;
    }
}
