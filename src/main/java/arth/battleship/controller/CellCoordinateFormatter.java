package arth.battleship.controller;

import java.util.List;

public class CellCoordinateFormatter {

    public static String numericToString(int i, int j) {
        return Character.toString((char) i + 'A') + (j + 1);
    }

    public static List<Integer> stringToNumericList(String s) {
        return List.of((int) s.charAt(0) - 'A', Integer.parseInt(s.substring(1)));
    }
}
