package com.suzuyo.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Direction {
    UP, DOWN, LEFT, RIGHT;

    static {
        UP.opposite = DOWN;
        DOWN.opposite = UP;
        LEFT.opposite = RIGHT;
        RIGHT.opposite = LEFT;
    }

    private Direction opposite;
}
