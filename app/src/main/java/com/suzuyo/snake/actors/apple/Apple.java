package com.suzuyo.snake.actors.apple;

import com.suzuyo.snake.common.Location2D;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class Apple {
    private Location2D location;

    public Apple() {
        this(0, 0);
    }

    public Apple(int x, int y) {
        this.location = new Location2D(x, y);
    }
}
