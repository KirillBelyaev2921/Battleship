package arth.battleship.controller;

import arth.battleship.exception.DiagonalCellPlacedException;
import arth.battleship.exception.InvalidBattleshipSizeException;
import arth.battleship.exception.InvalidNumberOfShipsOfOneSizeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BattleshipsBuilderTest {

    @Test
    void addCellTest() {
        BattleshipsBuilder builder = new BattleshipsBuilder();
        int result = builder.addCell(0, 0);
        assertEquals(result, 1);
    }

    @Test
    void addTwoCellsTest() {
        BattleshipsBuilder builder = new BattleshipsBuilder();
        builder.addCell(0, 0);
        int result = builder.addCell(0, 1);
        assertEquals(result, 2);
    }

    @Test
    void addThreeCellsTest() {
        BattleshipsBuilder builder = new BattleshipsBuilder();
        int result;
        builder.addCell(0, 0);
        builder.addCell(0, 1);
        result = builder.addCell(0, 2);
        assertEquals(result, 3);
    }

    @Test
    void addFourCellsTest() {
        BattleshipsBuilder builder = new BattleshipsBuilder();
        int result;
        builder.addCell(0, 0);
        builder.addCell(0, 1);
        builder.addCell(0, 2);
        result = builder.addCell(0, 3);
        assertEquals(result, 4);
    }

    @Test
    void addFiveCellsTest() {
        BattleshipsBuilder builder = new BattleshipsBuilder();
        builder.addCell(0, 0);
        builder.addCell(0, 1);
        builder.addCell(0, 2);
        builder.addCell(0, 3);
        try {
            builder.addCell(0, 4);
            fail();
        } catch (InvalidBattleshipSizeException ignored) {}
    }


    @Test
    void addManyCellsTest() {
        BattleshipsBuilder builder = new BattleshipsBuilder();
        int result;
        builder.addCell(0, 0);
        builder.addCell(0, 1);
        builder.addCell(0, 2);
        builder.addCell(1, 4);
        builder.addCell(1, 5);
        builder.addCell(1, 6);
        builder.addCell(2, 1);
        builder.addCell(3, 1);
        try {
            builder.addCell(4, 1);
            fail();
        } catch (InvalidNumberOfShipsOfOneSizeException ignored) {}
        result =  builder.addCell(5, 1);
        assertEquals(result, 4);
    }

    @Test
    void addTwoDiagonalTest() {
        BattleshipsBuilder builder = new BattleshipsBuilder();
        builder.addCell(0, 0);
        try {
            builder.addCell(1, 1);
            fail();
        } catch (DiagonalCellPlacedException ignored) {}
    }

}