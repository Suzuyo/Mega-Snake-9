package com.suzuyo.actors.snake;

import com.suzuyo.actors.apple.Apple;
import com.suzuyo.common.Location2D;

import java.awt.*;
import java.util.Iterator;
import java.util.LinkedList;

import static com.suzuyo.gui.BoardPanel.*;

public class SnakeService {
    private static SnakeService INSTANCE;

    public void paint(Graphics2D g2d, Snake snake) {
        for (SnakeElement snakeElement : snake.getSnakeElements()) {
            Location2D snakeElementLocation = snakeElement.getLocation();
            int x = snakeElementLocation.getX() * FIELD_SIZE;
            int y = snakeElementLocation.getY() * FIELD_SIZE;
            g2d.fillRect(x, y, FIELD_SIZE, FIELD_SIZE);
        }
    }

    public boolean isCollisionWithApple(Snake snake, Apple apple) {
        SnakeElement snakeHead = snake.getHead();
        return snakeHead.getLocation().equals(apple.getLocation());
    }

    public boolean isOutsideOfBoard(Snake snake) {
        SnakeElement snakeHead = snake.getHead();
        return snakeHead.getLocation().isOutside(0, FIELD_COUNT - 1, 0, FIELD_COUNT - 1);
    }

    public boolean isCollisionWithSelf(Snake snake) {
        LinkedList<SnakeElement> snakeElements = snake.getSnakeElements();
        SnakeElement firstSnakeElement = snakeElements.getFirst();
        Iterator<SnakeElement> iterator = snakeElements.iterator();
        iterator.next();
        while (iterator.hasNext()) {
            SnakeElement snakeElement = iterator.next();
            if (snakeElement.getLocation().equals(firstSnakeElement.getLocation())) {
                return true;
            }
        }
        return false;
    }

    public static synchronized SnakeService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SnakeService();
        }
        return INSTANCE;
    }
}
