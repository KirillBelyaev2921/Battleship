package arth.battleship.controller;

import arth.battleship.exception.DiagonalCellPlacedException;
import arth.battleship.exception.InvalidBattleshipSizeException;
import arth.battleship.exception.InvalidNumberOfShipsOfOneSizeException;
import arth.battleship.gui.main_panel.BattleshipGamePanel;
import arth.battleship.gui.main_panel.PlaceBattleshipsPanel;
import arth.battleship.model.Cell;


public class PlaceBattleshipsState extends FrameState {
    private final BattleshipsBuilder builder;

    public PlaceBattleshipsState(BattleshipController controller) {
        super(controller);
        this.builder = new BattleshipsBuilder();
        controller.setMainPanel(new PlaceBattleshipsPanel(controller));
    }

    @Override
    public boolean updateBattleshipCell(boolean isShip, Cell cell) {
        try {
            if (isShip) {
                builder.addCell(cell);
            } else {
                builder.removeCell(cell);
            }
        } catch (DiagonalCellPlacedException e) {
            getController().displayMessage("Cannot place ships on diagonals or wrong shape");
            return false;
        } catch (InvalidBattleshipSizeException e) {
            getController().displayMessage("Ship cannot be length 5 and more");
            return false;
        } catch (InvalidNumberOfShipsOfOneSizeException e) {
            getController().displayMessage("Too many number of ships of one size");
            return false;
        }
        getController().displayMessage("Not Ready");
        return builder.countShips();
    }

    @Override
    public void setReady(boolean isReady) {
        getController().getConnection().setReady(isReady);

        getController().displayMessage(isReady ? "Ready" : "Not Ready");
    }

    @Override
    public void startGame(boolean isTurn) {
        getController().setState(getController().getGameState(builder.build()));
        getController().setMainPanel(new BattleshipGamePanel(getController()));
        getController().setBattleships(isTurn);
    }
}
