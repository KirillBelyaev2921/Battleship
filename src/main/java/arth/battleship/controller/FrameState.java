package arth.battleship.controller;

import arth.battleship.constants.ShotResult;
import arth.battleship.model.Cell;
import arth.battleship.observers.EnemyBoardObserver;
import arth.battleship.observers.PlayerBoardObserver;

public abstract class FrameState {
    private final BattleshipController controller;

    public FrameState(BattleshipController controller) {
        this.controller = controller;
    }

    public final BattleshipController getController() {
        return controller;
    }

    public void createLobby() {
    }

    public void joinLobby() {
    }

    public void exit() {
    }

    public boolean updateBattleshipCell(boolean isShip, Cell cell) {
        return false;
    }

    public void setReady(boolean isReady) {
    }
    public void startGame(boolean isTurn) {
    }

    public ShotResult shotPlayer(Cell cell) {
        return null;
    }

    public void shotEnemyResult(ShotResult readObject) {
    }

    public void registerObserver(PlayerBoardObserver o) {
    }

    public void registerObserver(EnemyBoardObserver o) {
    }

    public boolean selectEnemyCell(Cell cell) {
        return false;
    }

    public void shotShip() {
    }

    public void goBack() {
    }

    public void setBattleships(boolean isTurn) {
    }
}
