package com.suzuyo.snake.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Direction {
    UP, DOWN, LEFT, RIGHT;

    static {
        UP.set(DOWN, -1);
        DOWN.set(UP, 1);
        LEFT.set(RIGHT, -1);
        RIGHT.set(LEFT, 1);
    }

    private Direction opposite;
    private int value;

    private void set(Direction opposite, int value) {
        this.opposite = opposite;
        this.value = value;
    }
}
