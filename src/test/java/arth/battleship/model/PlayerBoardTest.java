package arth.battleship.model;

import arth.battleship.constants.CellStatus;
import arth.battleship.constants.ShotResult;
import arth.battleship.controller.BattleshipsBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlayerBoardTest {
    private List<Battleship> battleships;

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
        battleships = builder.build();
    }

    @Test
    public void setCellStatus() {
        PlayerBoard playerBoard = new PlayerBoard(battleships);
        Cell cell = new Cell("A1");
        CellStatus cellStatus = CellStatus.HIT;

        assertEquals(playerBoard.getCellStatus(cell), CellStatus.SHIP);

        playerBoard.setCellStatus(cell, cellStatus);

        assertEquals(playerBoard.getCellStatus(cell), cellStatus);
    }

    @Test
    void shotOneCellAndHit() {
        PlayerBoard playerBoard = new PlayerBoard(battleships);
        Cell cell = new Cell("A1");
        ShotResult result = playerBoard.getShotResult(cell);
        playerBoard.shotCell(result, cell);

        assertEquals(playerBoard.getCellStatus(cell), CellStatus.HIT);

    }

    @Test
    void shotOneCellAndKill() {
        PlayerBoard playerBoard = new PlayerBoard(battleships);
        Cell cell = new Cell(6, 2);
        ShotResult result = playerBoard.getShotResult(cell);
        playerBoard.shotCell(result, cell);

        assertEquals(playerBoard.getCellStatus(cell), CellStatus.KILL);
        List<Cell> missCells = List.of(
                new Cell(cell.getI() - 1, cell.getJ() - 1),
                new Cell(cell.getI() - 1, cell.getJ()),
                new Cell(cell.getI() - 1, cell.getJ() + 1),
                new Cell(cell.getI(), cell.getJ() - 1),
                new Cell(cell.getI(), cell.getJ() + 1),
                new Cell(cell.getI() + 1, cell.getJ() - 1),
                new Cell(cell.getI() + 1, cell.getJ()),
                new Cell(cell.getI() + 1, cell.getJ() + 1)
                );
        missCells.forEach(cell1 -> assertEquals(playerBoard.getCellStatus(cell1), CellStatus.MISS));
        assertEquals(playerBoard.getCellStatus(new Cell(6, 4)), CellStatus.SHIP);
        assertEquals(playerBoard.getCellStatus(new Cell(6, 5)), CellStatus.EMPTY);
    }
}