package arth.battleship.constants;

public enum ShotResult {
    MISS, HIT, KILL, END;

    @Override
    public String toString() {
        return switch (this) {
            case MISS -> "Miss";
            case HIT -> "Hit";
            case KILL -> "Kill";
            case END -> "Win";
        };
    }
}
