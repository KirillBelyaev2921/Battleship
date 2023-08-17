package arth.battleship.controller;

import arth.battleship.constants.CommandLine;
import arth.battleship.exception.DiagonalCellPlacedException;
import arth.battleship.exception.InvalidBattleshipSizeException;
import arth.battleship.exception.InvalidNumberOfShipsOfOneSizeException;
import arth.battleship.socket.PlayerSocket;
import arth.battleship.model.Battleship;
import arth.battleship.model.Player;

import java.util.List;


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
            playerSocket.setPlayerName(CommandLine.READY, name);
        } else {
            playerSocket.setPlayerName(CommandLine.NOT_READY, name);
        }

        return selected ? CommandLine.READY.toString() : CommandLine.NOT_READY.toString();
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
            return CommandLine.READY.toString();
        }
        return CommandLine.NOT_READY.toString();
    }
}
