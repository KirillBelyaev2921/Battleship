package arth.battleship.model;

import java.util.ArrayList;
import java.util.List;

public class Player {
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

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void setBattleships(List<Battleship> battleships) {
        this.battleships = battleships;
    }

    public void addBattleship(Battleship battleship) {
        this.battleships.add(battleship);
    }

}
