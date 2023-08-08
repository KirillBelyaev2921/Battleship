package arth.battleship;

import arth.battleship.controller.BattleshipController;

public class BattleshipApplication {
    public static void main(String[] args) {
        BattleshipController controller = new BattleshipController();
        controller.start();
    }
}