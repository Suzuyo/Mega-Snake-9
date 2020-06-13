package com.suzuyo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GameState {
    private static GameState INSTANCE;
    private State state;
    private int score;

    private GameState() {
        this.state = State.NONE;
    }

    public enum State {
        NONE, DURING, SUCCESS_FINISH, FAIL_FINISH
    }

    public void addScore(int score) {
        this.score = this.score + score;
    }

    public static synchronized GameState getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new GameState();
        }
        return INSTANCE;
    }
}
