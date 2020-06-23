package com.suzuyo.snake.actors.snake;

import com.suzuyo.snake.common.Direction;
import com.suzuyo.snake.common.Location2D;
import lombok.Getter;

import java.util.LinkedList;

@Getter
public class Snake {
    private LinkedList<SnakeElement> snakeElements;

    public Snake(int x, int y, Direction direction) {
        snakeElements = new LinkedList<>();
        snakeElements.add(new SnakeElement(x, y, direction));
        addTail();
    }

    public SnakeElement getHead() {
        return snakeElements.getFirst();
    }

    public void eatApple() {
        snakeElements.getFirst().setHasApple(true);
    }

    public void addHead() {
        SnakeElement firstSnakeElement = snakeElements.getFirst();
        SnakeElement newChild = new SnakeElement(firstSnakeElement);
        Location2D newChildLocation = newChild.getLocation();
        if (firstSnakeElement.getDirection() == Direction.RIGHT) {
            newChildLocation.setX(newChildLocation.getX() + 1);
        } else if (firstSnakeElement.getDirection() == Direction.LEFT) {
            newChildLocation.setX(newChildLocation.getX() - 1);
        } else if (firstSnakeElement.getDirection() == Direction.UP) {
            newChildLocation.setY(newChildLocation.getY() - 1);
        } else if (firstSnakeElement.getDirection() == Direction.DOWN) {
            newChildLocation.setY(newChildLocation.getY() + 1);
        }
        snakeElements.addFirst(newChild);
    }

    public void addTail() {
        SnakeElement lastSnakeElement = snakeElements.getLast();
        SnakeElement newChild = new SnakeElement(lastSnakeElement);
        Location2D newChildLocation = newChild.getLocation();
        if (lastSnakeElement.getDirection() == Direction.RIGHT) {
            newChildLocation.setX(newChildLocation.getX() - 1);
        } else if (lastSnakeElement.getDirection() == Direction.LEFT) {
            newChildLocation.setX(newChildLocation.getX() + 1);
        } else if (lastSnakeElement.getDirection() == Direction.UP) {
            newChildLocation.setY(newChildLocation.getY() + 1);
        } else if (lastSnakeElement.getDirection() == Direction.DOWN) {
            newChildLocation.setY(newChildLocation.getY() - 1);
        }
        snakeElements.addLast(newChild);
    }

    public void changeDirection(Direction direction) {
        SnakeElement firstSnakeElement = snakeElements.getFirst();
        if (firstSnakeElement.getDirection() != direction.getOpposite()) {
            firstSnakeElement.setDirection(direction);
        }
    }
}
