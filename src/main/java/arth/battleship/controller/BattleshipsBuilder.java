package arth.battleship.controller;

import java.util.*;

public class BattleshipsBuilder {
    private Map<Integer, List<List<List<Integer>>>> battleshipsSizesCount;
    private CellStatus[][] board;

    public BattleshipsBuilder() {
        this.battleshipsSizesCount = new HashMap<>();
        battleshipsSizesCount.put(1, new ArrayList<>());
        battleshipsSizesCount.put(2, new ArrayList<>());
        battleshipsSizesCount.put(3, new ArrayList<>());
        battleshipsSizesCount.put(4, new ArrayList<>());
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

    public int addCell(int i, int j) {
        if (board[i][j] == CellStatus.LOCKED)
            return -1;
        board[i][j] = CellStatus.SHIP;
        List<List<Integer>> cells = new ArrayList<>();
        placeShip(i, j, cells);
        return fillShip(cells);
    }

    private int fillShip(List<List<Integer>> cells) {
        cells.forEach(cell -> {
            int i = cell.get(0);
            int j = cell.get(1);
            board[i][j] = CellStatus.SHIP;
            lock(i + 1, j + 1);
            lock(i + 1, j - 1);
            lock(i - 1, j + 1);
            lock(i - 1, j - 1);
        });
        if (cells.size() < 5)
            battleshipsSizesCount.computeIfAbsent(cells.size(), (e) -> battleshipsSizesCount.get(e)).add(cells);
        return cells.size();
    }

    private void lock(int i, int j) {
        if (i < 0 || j < 0 || i >= 10 || j >= 10)
            return;
        board[i][j] = CellStatus.LOCKED;
    }

    private void recount() {


    }

    private void placeShip(int i, int j, List<List<Integer>> cells) {
        if (i < 0 || j < 0 || i >= 10 || j >= 10)
            return;
        if (board[i][j] == CellStatus.LOCKED || board[i][j] == CellStatus.EMPTY)
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

    public boolean isCompleted() {
        return battleshipsSizesCount.get(1).size() == 4 &&
                battleshipsSizesCount.get(2).size() == 3 &&
                battleshipsSizesCount.get(3).size() == 2 &&
                battleshipsSizesCount.get(4).size() == 1;
    }

    private enum CellStatus {
        EMPTY, LOCKED, UNAVAILABLE, SHIP, OVER_SIZED
    }

}
