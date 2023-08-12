package arth.battleship.controller;

import arth.battleship.exception.DiagonalCellPlacedException;
import arth.battleship.exception.InvalidBattleshipSizeException;
import arth.battleship.exception.InvalidNumberOfShipsOfOneSizeException;
import arth.battleship.model.Battleship;

import java.util.*;

public class BattleshipsBuilder {
    private Map<Integer, Integer> battleshipsSizesCount;
    private CellStatus[][] board;

    public BattleshipsBuilder() {
        this.battleshipsSizesCount = new HashMap<>();
        board = new CellStatus[10][10];
        fillBoard();
    }

    private void fillBoard() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                board[i][j] = CellStatus.EMPTY;
            }
        }
    }

    public int addCell(int i, int j) throws InvalidNumberOfShipsOfOneSizeException,
            InvalidBattleshipSizeException,
            DiagonalCellPlacedException {
        board[i][j] = CellStatus.SHIP;
        List<List<Integer>> cells = new ArrayList<>();
        placeShip(i, j, cells);
        return fillShip(cells);
    }

    public void removeCell(int i, int j) throws InvalidNumberOfShipsOfOneSizeException,
            InvalidBattleshipSizeException,
            DiagonalCellPlacedException {
        board[i][j] = CellStatus.EMPTY;
        recount();
    }

    private int fillShip(List<List<Integer>> cells) throws InvalidNumberOfShipsOfOneSizeException,
            InvalidBattleshipSizeException,
            DiagonalCellPlacedException {
        cells.forEach(cell -> board[cell.get(0)][cell.get(1)] = CellStatus.SHIP);
        if (cells.size() > 4) {
            throw new InvalidBattleshipSizeException();
        }
        recount();
        return cells.size();
    }

    private void diagonalShipCheck(int i, int j) throws DiagonalCellPlacedException {
        if (i < 0 || j < 0 || i >= 10 || j >= 10)
            return;
        if (board[i][j] == CellStatus.SHIP) {
            throw new DiagonalCellPlacedException();
        }
    }

    private void recount() throws InvalidNumberOfShipsOfOneSizeException, DiagonalCellPlacedException, InvalidBattleshipSizeException {

        boolean isOverSized = false;

        battleshipsSizesCount.put(1, 0);
        battleshipsSizesCount.put(2, 0);
        battleshipsSizesCount.put(3, 0);
        battleshipsSizesCount.put(4, 0);
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                int count = recountShip(i, j, 0);
                if (count > 0 && count < 5) {
                    battleshipsSizesCount.put(count, battleshipsSizesCount.get(count) + 1);
                } else if (count >= 5) {
                    isOverSized = true;
                }
            }
        }

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (board[i][j] == CellStatus.SHIP_COUNTED)
                    board[i][j] = CellStatus.SHIP;
            }
        }

        if (isOverSized)
            throw new InvalidBattleshipSizeException();

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (board[i][j] == CellStatus.SHIP) {
                    diagonalShipCheck(i + 1, j + 1);
                    diagonalShipCheck(i - 1, j + 1);
                    diagonalShipCheck(i + 1, j - 1);
                    diagonalShipCheck(i - 1, j - 1);
                }
            }
        }

        countShips();
    }

    private int recountShip(int i, int j, int count) {
        if (i < 0 || j < 0 || i >= 10 || j >= 10)
            return 0;
        if (board[i][j] == CellStatus.EMPTY || board[i][j] == CellStatus.SHIP_COUNTED)
            return 0;

        board[i][j] = CellStatus.SHIP_COUNTED;

        count += recountShip(i + 1, j, count);
        count += recountShip(i - 1, j, count);
        count += recountShip(i, j + 1, count);
        count += recountShip(i, j - 1, count);

        count += 1;
        return count;
    }

    private void placeShip(int i, int j, List<List<Integer>> cells) {
        if (i < 0 || j < 0 || i >= 10 || j >= 10)
            return;
        if (board[i][j] == CellStatus.EMPTY)
            return;

        if (board[i][j] == CellStatus.SHIP) {
            cells.add(List.of(i, j));
            board[i][j] = CellStatus.EMPTY;
        }

        placeShip(i - 1, j, cells);
        placeShip(i + 1, j, cells);
        placeShip(i, j - 1, cells);
        placeShip(i, j + 1, cells);
    }

    public boolean countShips() throws InvalidNumberOfShipsOfOneSizeException {
        if (battleshipsSizesCount.get(1) > 4 ||
                battleshipsSizesCount.get(2) > 3 ||
                battleshipsSizesCount.get(3) > 2 ||
                battleshipsSizesCount.get(4) > 1) {
            throw new InvalidNumberOfShipsOfOneSizeException();
        }
        return battleshipsSizesCount.get(1) == 4 &&
                battleshipsSizesCount.get(2) == 3 &&
                battleshipsSizesCount.get(3) == 2 &&
                battleshipsSizesCount.get(4) == 1;
    }

    private enum CellStatus {
        EMPTY, SHIP, SHIP_COUNTED
    }

    public List<Battleship> build() {
        return null;
    }
}
