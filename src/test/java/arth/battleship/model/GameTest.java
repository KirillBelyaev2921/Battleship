package arth.battleship.model;

import arth.battleship.constants.CellStatus;
import arth.battleship.constants.ShotResult;
import arth.battleship.controller.BattleshipsBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    private List<Battleship> playerBattleships;
    private List<Battleship> enemyBattleships;

    @BeforeEach
    public void setBattleships() {
        BattleshipsBuilder builder = new BattleshipsBuilder();
        builder.addCell(0, 0);
        builder.addCell(0, 1);
        builder.addCell(0, 2);
        builder.addCell(0, 3);
        builder.addCell(0, 7);
        builder.addCell(0, 8);
        builder.addCell(0, 9);
        builder.addCell(2, 0);
        builder.addCell(2, 1);
        builder.addCell(2, 2);
        builder.addCell(2, 4);
        builder.addCell(2, 5);
        builder.addCell(2, 7);
        builder.addCell(2, 8);
        builder.addCell(4, 4);
        builder.addCell(4, 5);
        builder.addCell(6, 0);
        builder.addCell(6, 2);
        builder.addCell(6, 4);
        builder.addCell(6, 6);
        playerBattleships = builder.build();
        enemyBattleships = builder.build();
    }


    @Test
    void setShotEnemyResult() {
        EnemyBoard enemyBoard = new EnemyBoard();

        PlayerBoard enemyPlayerBoard = new PlayerBoard(enemyBattleships);

        Cell playerShot = new Cell(0, 0);
        ShotResult shotResult = enemyPlayerBoard.getShotResult(playerShot);
        enemyPlayerBoard.shotCell(shotResult, playerShot);

        assertEquals(enemyPlayerBoard.getCell(playerShot).getStatus(), CellStatus.HIT);

        enemyBoard.shotCell(shotResult, playerShot);

        assertEquals(enemyPlayerBoard.getCell(playerShot).getStatus(), CellStatus.HIT);;
    }

    @Test
    void setShotPlayerResult() {
        PlayerBoard playerBoard = new PlayerBoard(playerBattleships);

        Cell enemyShot = new Cell(6, 2);

        ShotResult shotResult = playerBoard.getShotResult(enemyShot);
        playerBoard.shotCell(shotResult, enemyShot);

        assertEquals(playerBoard.getCell(enemyShot).getStatus(), CellStatus.KILL);

        assertEquals(playerBoard.getCell(new Cell(6, 1)).getStatus(), CellStatus.MISS);;


    }
}