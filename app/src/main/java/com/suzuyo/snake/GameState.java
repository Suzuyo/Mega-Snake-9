package com.suzuyo.snake;

import com.suzuyo.snake.utils.DurationUtils;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class GameState {
    private static GameState INSTANCE;
    private State state;
    private int snakeLength;
    private Date beginPlayTime;
    private Date endPlayTime;

    private GameState() {
        this.state = State.NONE;
    }

    public String getDurationTime() {
        long seconds = DurationUtils.between(beginPlayTime, endPlayTime);
        return DurationUtils.toString(seconds);
    }

    public enum State {
        NONE, DURING, SUCCESS_FINISH, FAIL_FINISH
    }

    public static synchronized GameState getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new GameState();
        }
        return INSTANCE;
    }
}
