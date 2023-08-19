package arth.battleship.constants;

public enum CommandLine {
    SHOT, READY, NOT_READY, GAME_START, GET_SHOT_RESULT, SET_SHOT_RESULT, SHOT_RESULT;

    @Override
    public String toString() {
        return switch (this) {

            case SHOT -> "Shot";
            case READY -> "Ready";
            case NOT_READY -> "Not Ready";
            case GAME_START -> "Game start";
            case GET_SHOT_RESULT -> "Get Shot Result";
            case SET_SHOT_RESULT -> "Set Shot Result";
            case SHOT_RESULT -> "Shot Result";
        };
    }
}
