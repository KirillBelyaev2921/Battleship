package arth.battleship.controller;

public class PlaceShipsController {
    public String ready(boolean selected) {
        return selected ? "Ready" : "Not Ready";
    }
}
