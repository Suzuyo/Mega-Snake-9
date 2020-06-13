package com.suzuyo;

import com.suzuyo.actors.apple.Apple;
import com.suzuyo.actors.apple.AppleService;
import com.suzuyo.actors.snake.SnakeService;
import com.suzuyo.actors.tunnel.Tunnel;
import com.suzuyo.actors.tunnel.TunnelProvider;
import com.suzuyo.common.Direction;
import com.suzuyo.common.Location2D;
import com.suzuyo.actors.snake.Snake;
import com.suzuyo.actors.snake.SnakeElement;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.LinkedList;

import static com.suzuyo.gui.BoardPanel.*;

@Getter
public class GameMode {
    private static GameMode INSTANCE;
    private static final GameState GAME_STATE = GameState.getInstance();
    private static final SnakeService SNAKE_SERVICE = SnakeService.getInstance();
    private static final AppleService APPLE_SERVICE = AppleService.getInstance();
    private Snake snake;
    private Tunnel tunnel;
    private Apple apple;
    private int currentTunnel = 1;
    private int speed;
    private LocalDateTime beginPlayTime;
    private LocalDateTime endPlayTime;
    private Direction lastDirectionPressed;

    public void beginPlay() {
        this.snake = new Snake();
        this.apple = new Apple();
        GAME_STATE.setScore(0);
        SnakeElement firstSnakeElement = this.snake.getSnakeElements().getFirst();
        this.lastDirectionPressed = firstSnakeElement.getDirection();
        beginPlayTime = LocalDateTime.now();
        tunnel = nextTunnel();
        putAppleInRandomPlace();
        speed = 200;
    }

    public void tick() {
        snake.changeDirection(lastDirectionPressed);
        snake.move();
        checkCollisionWithTunnel();
        checkSnakeIsOutsideOfBoard();
        checkSnakeCollisionWithSelf();
        checkSnakeCollisionWithApple();
    }

    public void pressUp() {
        lastDirectionPressed = Direction.UP;
    }

    public void pressDown() {
        lastDirectionPressed = Direction.DOWN;
    }

    public void pressLeft() {
        lastDirectionPressed = Direction.LEFT;
    }

    public void pressRight() {
        lastDirectionPressed = Direction.RIGHT;
    }

    private void putAppleInRandomPlace() {
        apple = APPLE_SERVICE.createAppleByRandomFromRange(FIELD_COUNT, FIELD_COUNT);
    }

    private void checkSnakeCollisionWithApple() {
        if (apple != null && SNAKE_SERVICE.isCollisionWithApple(snake, apple)) {
            apple = null;
            snake.addTail();
            speed--;
            tunnel = nextTunnel();
            GAME_STATE.addScore(1);
            if (tunnel == null) {
                putAppleInRandomPlace();
            }
        }
    }

    private void checkSnakeIsOutsideOfBoard() {
        if (SNAKE_SERVICE.isOutsideOfBoard(snake)) {
            finishGame(GameState.State.FAIL_FINISH);
        }
    }

    private void checkSnakeCollisionWithSelf() {
        if (SNAKE_SERVICE.isCollisionWithSelf(snake)) {
            finishGame(GameState.State.FAIL_FINISH);
        }
    }

    private void checkCollisionWithTunnel() {
        LinkedList<SnakeElement> snakeElements = snake.getSnakeElements();
        SnakeElement firstSnakeElement = snakeElements.getFirst();
        if (tunnel != null) {
            Location2D currentStepLocation = tunnel.getCurrentStepLocation();
            if (firstSnakeElement.getLocation().equals(currentStepLocation)) {
                tunnel.nextStep();
                GAME_STATE.addScore(1);
                if (tunnel.getStep() == tunnel.getElements().length) {
                    currentTunnel++;
                    tunnel = nextTunnel();
                    if (tunnel == null) {
                        putAppleInRandomPlace();
                    }
                }
            } else if (tunnel.getStep() != 0) {
                currentTunnel++;
                tunnel = nextTunnel();
                if (tunnel == null) {
                    putAppleInRandomPlace();
                }
            }
        }
    }

    private Tunnel nextTunnel() {
        return TunnelProvider.getTunnel(snake.getSnakeElements().size(), currentTunnel);
    }

    private void finishGame(GameState.State state) {
        endPlayTime = LocalDateTime.now();
        GAME_STATE.setState(state);
        WindowController.getInstance().hidePlayPanel();
        WindowController.getInstance().showFinishGame();
    }

    public static synchronized GameMode getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new GameMode();
        }
        return INSTANCE;
    }
}
