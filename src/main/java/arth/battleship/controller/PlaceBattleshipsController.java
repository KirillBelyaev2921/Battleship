package arth.battleship.controller;

import arth.battleship.constants.CommandLine;
import arth.battleship.exception.DiagonalCellPlacedException;
import arth.battleship.exception.InvalidBattleshipSizeException;
import arth.battleship.exception.InvalidNumberOfShipsOfOneSizeException;
import arth.battleship.socket.PlayerSocket;
import arth.battleship.model.Battleship;
import arth.battleship.model.Player;

import java.util.List;

import static arth.battleship.constants.CommandLine.NOT_READY;
import static arth.battleship.constants.CommandLine.READY;


public class PlaceBattleshipsController extends BattleshipController {
    private Player player;
    private PlayerSocket playerSocket;
    private BattleshipsBuilder builder;
    private List<Battleship> battleships;

    public PlaceBattleshipsController() {
        player = new Player();
        playerSocket = new PlayerSocket(player);
        this.builder = new BattleshipsBuilder();
    }

    public String ready(boolean selected, String name) {
        if (selected) {
            playerSocket.setPlayer(new Player(name, battleships));
            playerSocket.setReady();
        } else {
            playerSocket.setNotReady();
        }

        return selected ? READY.toString() : NOT_READY.toString();
    }


    public String updatePlayerBattleships(boolean isShip, int i, int j) {
        try {
            if (isShip) {
                builder.addCell(i, j);
            } else {
                builder.removeCell(i, j);
            }
        } catch (DiagonalCellPlacedException e) {
            return "Cannot place ships on diagonals or wrong shape";
        } catch (InvalidBattleshipSizeException e) {
            return "Ship cannot be length 5 and more";
        } catch (InvalidNumberOfShipsOfOneSizeException e) {
            return "Too many number of ships of one size";
        }
        if (builder.countShips()) {
            battleships = builder.build();
            return READY.toString();
        }
        return NOT_READY.toString();
    }
}
