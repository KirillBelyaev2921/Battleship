package arth.battleship.model;

import arth.battleship.constants.CellStatus;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BattleshipCellTest {

    @Test
    public void getDefaultStatus() {
        BattleshipCell battleshipCell = new BattleshipCell(0, 0);

        assertEquals(battleshipCell.getStatus(), CellStatus.EMPTY);
    }

    @Test
    public void setStatus() {
        BattleshipCell battleshipCell = new BattleshipCell(0, 0);
        battleshipCell.setStatus(CellStatus.MISS);

        assertEquals(battleshipCell.getStatus(), CellStatus.MISS);
    }
}