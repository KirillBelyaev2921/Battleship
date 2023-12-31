package arth.battleship.controller;

import arth.battleship.constants.ShotResult;
import arth.battleship.gui.BattleshipFrame;
import arth.battleship.gui.main_panel.MainPanel;
import arth.battleship.model.Battleship;
import arth.battleship.model.Cell;
import arth.battleship.observers.EnemyBoardObserver;
import arth.battleship.observers.PlayerBoardObserver;

import java.util.List;

public class BattleshipController {
    private final BattleshipFrame frame;
    private FrameState state;
    private EnemyConnectionController connection;

    public BattleshipController() {
        this.frame = new BattleshipFrame(this);
        state = getMainMenuState();
    }

    public void createLobby() {
        state.createLobby();
    }

    public void joinLobby() {
        state.joinLobby();
    }

    public void exit() {
        state.exit();
    }
    public boolean updateBattleshipCell(boolean isShip, Cell cell) {
        return state.updateBattleshipCell(isShip, cell);
    }

    public void setReady(boolean isReady) {
        state.setReady(isReady);
    }
    public void shotEnemyCell() {
        state.shotEnemyCell();
    }
    /* TODO
        return boolean, remove SELECTED state. Observer will not control selecting cell, only view (or controller).
     */
    public void selectEnemyCell(Cell cell) {
        state.selectEnemyCell(cell);
    }
    public void goBack() {
        state.goBack();
    }

    public void startGame(boolean isTurn) {
        state.startGame(isTurn);
    }

    public void setBattleships(boolean isTurn) {
        state.setBattleships(isTurn);
    }

    public ShotResult shotPlayer(Cell cell) {
        return state.shotPlayer(cell);
    }

    public void shotEnemyResult(ShotResult result) {
        state.shotEnemyResult(result);
    }

    public void displayMessage(String... message) {
        frame.displayMessage(message);
    }

    public void setMainPanel(MainPanel panel) {
        frame.setMainPanel(panel);
    }

    public void setState(FrameState state) {
        this.state = state;
    }

    public MainMenuState getMainMenuState() {
        return new MainMenuState(this);
    }

    public PlaceBattleshipsState getPlaceBattleshipsState() {
        return new PlaceBattleshipsState(this);
    }

    public GameState getGameState(List<Battleship> battleships) {
        return new GameState(this, battleships);
    }

    public EnemyConnectionController getConnection() {
        return connection;
    }

    public void setConnection(EnemyConnectionController connection) {
        this.connection = connection;
    }

    public void registerObserver(PlayerBoardObserver o) {
        state.registerObserver(o);
    }

    public void registerObserver(EnemyBoardObserver o) {
        state.registerObserver(o);
    }
}
