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

    public String ready(boolean selected, String name, String ships) {
        if (selected) {
            // TODO
            //  create list of battleships from user and send it to the server
            Battleship battleship = new Battleship(ships.split(" "));
            playerConnection.setReady(name, List.of(battleship));
        } else {
            playerConnection.sendMessage(CommandLines.NOT_READY);
        }

        return selected ? CommandLines.READY : CommandLines.NOT_READY;
    }
}
