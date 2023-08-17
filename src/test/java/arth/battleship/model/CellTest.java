package arth.battleship.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class CellTest {

    @Test
    public void createCellWithNumbersAndGetNumberCoordinates() {
        Cell cell = new Cell(1, 2);

        assertEquals(cell.getI(), 1);
        assertEquals(cell.getJ(), 2);
    }

    @Test
    public void createCellWithNumbersAndGetStringCoordinates() {
        Cell cell = new Cell(1, 2);

        assertEquals(cell.toString(), "B3");
    }

    @Test
    public void createCellWithStringAndGetNumberCoordinates() {
        Cell cell = new Cell("B3");

        assertEquals(cell.getI(), 1);
        assertEquals(cell.getJ(), 2);
    }

    @Test
    public void createCellWithStringAndGetStringCoordinates() {
        Cell cell = new Cell("B3");

        assertEquals(cell.toString(), "B3");
    }

}