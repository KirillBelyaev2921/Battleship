package arth.battleship.controller;

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
        int result;
        builder.addCell(0, 0);
        builder.addCell(0, 1);
        builder.addCell(0, 2);
        builder.addCell(0, 3);
        result = builder.addCell(0, 4);
        assertEquals(result, 5);
    }

}