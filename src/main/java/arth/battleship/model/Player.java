package arth.battleship.model;

import java.util.List;

public class Player {
    private String playerName;

    private List<Battleship> battleships;

    public Player() {
    }

    public Player(String playerName, List<Battleship> battleships) {
        this.playerName = playerName.strip();
        this.battleships = battleships;
    }

    public String getPlayerName() {
        return playerName;
    }

    public List<Battleship> getBattleships() {
        return battleships;
    }
}
