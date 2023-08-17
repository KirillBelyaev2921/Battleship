package arth.battleship.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public String shotCell(Cell cell) {
        Optional<Battleship> oBattleship = battleships.stream()
                .filter(battleship -> battleship.shotCell(cell))
                .findAny();
        if (oBattleship.isEmpty()) {
            return "Miss";
        }
        Battleship battleship = oBattleship.get();
        if (battleship.getSize() != 0) {
            return "Hit";
        }
        battleships.remove(battleship);
        if (battleships.size() != 0) {
            return "Kill";
        }
        return "Win";
    }

    public void removeBattleship(Battleship battleship) {
        battleships.remove(battleship);
    }
}
