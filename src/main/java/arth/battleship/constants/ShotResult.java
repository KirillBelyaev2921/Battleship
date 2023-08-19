package arth.battleship.constants;

public enum ShotResult {
    MISS, HIT, KILL, WIN;

    @Override
    public String toString() {
        return switch (this) {
            case MISS -> "Miss";
            case HIT -> "Hit";
            case KILL -> "Kill";
            case WIN -> "Win";
        };
    }
}
