package arth.battleship.model;

import arth.battleship.exception.InvalidBattleshipCellsPlacementException;
import arth.battleship.exception.InvalidBattleshipSizeException;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class BattleshipTest {

    @Test
    void getCorrectOneCellShipTest() {
        Battleship battleship = new Battleship("A10");
        assertIterableEquals(battleship.getShipCells().stream().map(Cell::toString).collect(Collectors.toList()), List.of("A10"));
    }

    @Test
    void getCorrectHorizontalTwoCellShipTest() {
        Battleship battleship = new Battleship("A1", "A2");
        assertIterableEquals(battleship.getShipCells().stream().map(Cell::toString).collect(Collectors.toList()), List.of("A1", "A2"));

    }

    @Test
    void getCorrectHorizontalThreeCellShipTest() {
        Battleship battleship = new Battleship("A1", "A2", "A3");
        assertIterableEquals(battleship.getShipCells().stream().map(Cell::toString).collect(Collectors.toList()), List.of("A1", "A2", "A3"));
    }

    @Test
    void getCorrectHorizontalFourCellShipTest() {
        Battleship battleship = new Battleship("A1", "A2", "A3", "A4");
        assertIterableEquals(battleship.getShipCells().stream().map(Cell::toString).collect(Collectors.toList()), List.of("A1", "A2", "A3", "A4"));
    }
    @Test
    void getCorrectVerticalTwoCellShipTest() {
        Battleship battleship = new Battleship("A1", "B1");
        assertIterableEquals(battleship.getShipCells().stream().map(Cell::toString).collect(Collectors.toList()), List.of("A1", "B1"));

    }

    @Test
    void getCorrectVerticalThreeCellShipTest() {
        Battleship battleship = new Battleship("A1", "B1", "C1");
        assertIterableEquals(battleship.getShipCells().stream().map(Cell::toString).collect(Collectors.toList()), List.of("A1", "B1", "C1"));
    }

    @Test
    void getCorrectVerticalFourCellShipTest() {
        Battleship battleship = new Battleship("A1", "B1", "C1", "D1");
        assertIterableEquals(battleship.getShipCells().stream().map(Cell::toString).collect(Collectors.toList()), List.of("A1", "B1", "C1", "D1"));
    }

    @Test
    void incorrectOneCellNumberShipTest() {
        try {
            Battleship battleship = new Battleship("A1123");
            fail();
        } catch (InvalidBattleshipCellsPlacementException ignored) {}
    }
    @Test
    void incorrectOneCellLetterShipTest() {
        try {
            Battleship battleship = new Battleship("Q2");
            fail();
        } catch (InvalidBattleshipCellsPlacementException ignored) {}
    }
    @Test
    void incorrectOneCellLetterAndNumberShipTest() {
        try {
            Battleship battleship = new Battleship("Q12");
            fail();
        } catch (InvalidBattleshipCellsPlacementException ignored) {}
    }
    @Test
    void incorrectTwoCellShipTest() {
        try {
            Battleship battleship = new Battleship("Q1", "A123");
            fail();
        } catch (InvalidBattleshipCellsPlacementException ignored) {}
    }

    @Test
    void incorrectThreeCellShipTest() {
        try {
            Battleship battleship = new Battleship("A12", "G2", "N31");
            fail();
        } catch (InvalidBattleshipCellsPlacementException ignored) {}
    }

    @Test
    void incorrectFourCellShipTest() {
        try {
            Battleship battleship = new Battleship("W1", "V2", "A1", "C2");
            fail();
        } catch (InvalidBattleshipCellsPlacementException ignored) {}
    }

    @Test
    void getFiveCellShipTest() {
        try {
            Battleship battleship = new Battleship("A1", "A2", "A3", "A4", "A5");
            fail();
        } catch (InvalidBattleshipSizeException ignored) {}
    }
    @Test
    void getSize() {
        Battleship battleship = new Battleship("A1");
        assertEquals(battleship.getSize(), 1);
    }
}