package arth.battleship.model;

import arth.battleship.constants.ShotResult;

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

    public ShotResult shotCell(Cell cell) {
        Optional<Battleship> oBattleship = battleships.stream()
                .filter(battleship -> battleship.shotCell(cell))
                .findAny();
        if (oBattleship.isEmpty()) {
            return ShotResult.MISS;
        }
        Battleship battleship = oBattleship.get();
        if (battleship.getSize() != 0) {
            return ShotResult.HIT;
        }
        battleships.remove(battleship);
        if (battleships.size() != 0) {
            return ShotResult.KILL;
        }
        return ShotResult.WIN;
    }

    public void removeBattleship(Battleship battleship) {
        battleships.remove(battleship);
    }
}
