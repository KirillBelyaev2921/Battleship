package arth.battleship.constants;

public enum CommandLine {
    SHOOT, READY, NOT_READY, GAME_START, SHOT_RESULT, SET_PLAYERS;

    @Override
    public String toString() {
        return switch (this) {
            case SHOOT -> "Shot";
            case READY -> "Ready";
            case NOT_READY -> "Not Ready";
            case GAME_START -> "Game start";
            case SHOT_RESULT -> "Shot Result";
            case SET_PLAYERS -> "Set players";
        };
    }

}
