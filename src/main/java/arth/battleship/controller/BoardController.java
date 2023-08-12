package arth.battleship.controller;

import arth.battleship.constants.CommandLines;
import arth.battleship.exception.DiagonalCellPlacedException;
import arth.battleship.exception.InvalidBattleshipSizeException;
import arth.battleship.exception.InvalidNumberOfShipsOfOneSizeException;

public class BoardController {
    private BattleshipsBuilder builder;

    public BoardController() {
        this.builder = new BattleshipsBuilder();
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
            return CommandLines.READY;
        }
        return CommandLines.NOT_READY;
    }
}
