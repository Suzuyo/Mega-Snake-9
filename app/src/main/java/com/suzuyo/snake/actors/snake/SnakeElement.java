package com.suzuyo.snake.actors.snake;

import com.suzuyo.snake.common.Direction;
import com.suzuyo.snake.common.Location2D;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SnakeElement {
    private Location2D location;
    private Direction direction;
    private boolean hasApple;

    public SnakeElement(SnakeElement snakeElement) {
        this.location = new Location2D(snakeElement.location);
        this.direction = snakeElement.direction;
    }

    public SnakeElement(int x, int y, Direction direction) {
        this.location = new Location2D(x, y);
        this.direction = direction;
    }
}
