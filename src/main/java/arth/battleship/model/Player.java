package arth.battleship.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Player implements Serializable {
    private String playerName;

    private List<Battleship> battleships;

    public Player() {
        this.battleships = new ArrayList<>();
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

    public void removeBattleship(Battleship battleship) {
        battleships.remove(battleship);
    }
}
