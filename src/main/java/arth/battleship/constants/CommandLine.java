package arth.battleship.constants;

public enum CommandLine {
    SHOT, READY, NOT_READY, GAME_START, SHOT_PLAYER, SHOT_PLAYER_RESULT, SHOT_RESULT;

    @Override
    public String toString() {
        return switch (this) {

            case SHOT -> "Shot";
            case READY -> "Ready";
            case NOT_READY -> "Not Ready";
            case GAME_START -> "Game start";
            case SHOT_PLAYER -> "Get Shot Result";
            case SHOT_PLAYER_RESULT -> "Set Shot Result";
            case SHOT_RESULT -> "Shot Result";
        };
    }
}
