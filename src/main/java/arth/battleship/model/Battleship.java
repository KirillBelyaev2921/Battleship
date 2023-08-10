package arth.battleship.model;

import arth.battleship.exception.InvalidBattleshipCellsPlacementException;
import arth.battleship.exception.InvalidBattleshipSizeException;

import java.util.Arrays;
import java.util.List;

public class Battleship {
    public static final int BATTLESHIP_MAX_SIZE = 4;
    private final List<String> shipCells;
    public static final int BOARD_SIZE = 10;

    public Battleship(String... shipCells) {
        if (!isCorrectSize(shipCells))
            throw new InvalidBattleshipSizeException();
        if (!isCorrectPlacement(shipCells)) {
            throw new InvalidBattleshipCellsPlacementException();
        }
        this.shipCells = List.of(shipCells);
    }

    private boolean isCorrectPlacement(String[] shipCells) {
        int length = shipCells.length;

        List<Integer> vertical;
        List<Integer> horizontal;
        try {
            vertical = Arrays.stream(shipCells)
                    .map(s -> (int) s.toUpperCase().charAt(0) - (int) 'A' + 1)
                    .toList();

            horizontal = Arrays.stream(shipCells)
                    .map(s -> Integer.parseInt(s.substring(1)))
                    .toList();
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            return false;
        }

        if (!isCorrectPlacementCells(vertical) || !isCorrectPlacementCells(horizontal))
            return false;

        if (length == 1)
            return true;

        Direction direction = findDirection(vertical, horizontal, length);

        if (direction == Direction.HORIZONTAL_DIRECTION) {
            return isDirectionPlacementCorrect(horizontal);
        } else if (direction == Direction.VERTICAL_DIRECTION) {
            return isDirectionPlacementCorrect(vertical);
        } else {
            return false;
        }
    }

    private boolean isCorrectPlacementCells(List<Integer> cellCoordinates) {
        return cellCoordinates.stream()
                .noneMatch(s -> s <= 0 || s > BOARD_SIZE);
    }

    private boolean isDirectionPlacementCorrect(List<Integer> horizontal) {
        int length = horizontal.size();
        Integer[] integers = horizontal.stream()
                .sorted()
                .toArray(Integer[]::new);
        return integers[length - 1] - integers[0] == length - 1;
    }

    private Direction findDirection(List<Integer> vertical, List<Integer> horizontal, int length) {
        long verticalCount = vertical.stream().distinct().count();
        long horizontalCount = horizontal.stream().distinct().count();

        if (verticalCount == 1 && horizontalCount == length)
            return Direction.HORIZONTAL_DIRECTION;
        else if (verticalCount == length && horizontalCount == 1)
            return Direction.VERTICAL_DIRECTION;
        else return Direction.INCORRECT_DIRECTION;
    }


    private boolean isCorrectSize(String[] shipCells) {
        return shipCells.length > 0 && shipCells.length <= BATTLESHIP_MAX_SIZE;
    }

    public List<String> getShipCells() {
        return shipCells;
    }

    public int getSize() {
        return shipCells.size();
    }

    private enum Direction {
        HORIZONTAL_DIRECTION, VERTICAL_DIRECTION, INCORRECT_DIRECTION
    }
}
