package com.suzuyo.snake.actors.apple;

import com.suzuyo.snake.actors.board.Board;
import com.suzuyo.snake.actors.snake.Snake;
import com.suzuyo.snake.actors.snake.SnakeElement;
import com.suzuyo.snake.common.Location2D;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AppleService {
    private static AppleService INSTANCE;

    public Apple createAppleOnBoard(Board board, Snake snake) {
        List<Location2D> availableLocations = new ArrayList<>();
        for (int i = 0; i < board.getColumns(); i++) {
            for (int j = 0; j < board.getRows(); j++) {
                boolean found = false;
                for (SnakeElement snakeElement : snake.getSnakeElements()) {
                    Location2D location = snakeElement.getLocation();
                    if (location.getX() == i && location.getY() == j) {
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    availableLocations.add(new Location2D(i, j));
                }
            }
        }
        Random random = new Random();
        int value = (int) (random.nextDouble() * availableLocations.size() - 1);
        return new Apple(availableLocations.get(value));
    }

    public static synchronized AppleService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new AppleService();
        }
        return INSTANCE;
    }
}
