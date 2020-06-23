package com.suzuyo.snake.actors.snake;

import com.suzuyo.snake.actors.apple.Apple;
import com.suzuyo.snake.actors.board.Board;

import java.util.Iterator;
import java.util.LinkedList;

public class SnakeService {
    private static SnakeService INSTANCE;

    public void move(Snake snake) {
        snake.getSnakeElements().removeLast();
        snake.addHead();
    }

    public boolean isCollisionWithApple(Snake snake, Apple apple) {
        SnakeElement snakeHead = snake.getHead();
        return snakeHead.getLocation().equals(apple.getLocation());
    }

    public boolean isOutsideOfBoard(Snake snake, Board board) {
        if (board.getRows() == 0 || board.getColumns() == 0) {
            return false;
        }
        SnakeElement snakeHead = snake.getHead();
        return snakeHead.getLocation().isOutside(0, board.getColumns() - 1, 0, board.getRows() - 1);
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
