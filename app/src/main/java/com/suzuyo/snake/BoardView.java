package com.suzuyo.snake;

import android.content.Context;
import android.graphics.*;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.RelativeLayout;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.suzuyo.snake.actors.apple.Apple;
import com.suzuyo.snake.actors.apple.AppleService;
import com.suzuyo.snake.actors.board.Board;
import com.suzuyo.snake.actors.snake.Snake;
import com.suzuyo.snake.actors.snake.SnakeElement;
import com.suzuyo.snake.actors.snake.SnakeService;
import com.suzuyo.snake.common.Direction;
import com.suzuyo.snake.common.Location2D;
import com.suzuyo.snake.gui.common.TouchSlideEvent;
import lombok.Setter;

import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;

public class BoardView extends RelativeLayout {
    private static final GameState GAME_STATE = GameState.getInstance();
    private static final SnakeService SNAKE_SERVICE = SnakeService.getInstance();
    private static final AppleService APPLE_SERVICE = AppleService.getInstance();
    @Setter private OnFinishListener onFinishListener;
    private TouchSlideEvent touchSlideEvent = new TouchSlideEvent();
    private Direction lastDirectionPressed = Direction.RIGHT;
    private Board board;
    private Snake snake;
    private Apple apple;
    private Bitmap appleImage;

    public BoardView(Context context) {
        super(context);
    }

    public BoardView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public BoardView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public BoardView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void beginPlay() {
        appleImage = BitmapFactory.decodeResource(getResources(), R.drawable.apple);
        calculateBoard();
        adjustBoardViewToFieldSize();
        this.snake = new Snake(1, 0, lastDirectionPressed);
        GAME_STATE.setSnakeLength(snake.getSnakeElements().size());
        GAME_STATE.setBeginPlayTime(new Date());
        GAME_STATE.setState(GameState.State.DURING);
        putAppleInRandomPlace();
    }

    public void tick() {
        snake.changeDirection(lastDirectionPressed);
        SNAKE_SERVICE.move(snake);
        checkSnakeIsOutsideOfBoard();
        checkSnakeCollisionWithSelf();
        checkSnakeCollisionWithApple();
    }

    private void calculateBoard() {
        int width = getWidth();
        int height = getHeight();
        board = new Board();
        board.setColumns(20);
        board.setFieldSize((float) width / board.getColumns());
        board.setRows((int) Math.floor((float) height / board.getFieldSize()));
    }

    private void adjustBoardViewToFieldSize() {
        int realHeight = (int) ((float) board.getRows() * board.getFieldSize());
        int additionalBottomMargin = getHeight() - realHeight;
        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) getLayoutParams();
        layoutParams.bottomMargin = layoutParams.bottomMargin + additionalBottomMargin;
        setLayoutParams(layoutParams);
    }

    private void putAppleInRandomPlace() {
        apple = APPLE_SERVICE.createAppleOnBoard(board, snake);
    }

    private void checkSnakeCollisionWithApple() {
        if (apple != null && SNAKE_SERVICE.isCollisionWithApple(snake, apple)) {
            apple = null;
            snake.eatApple();
            snake.addTail();
            GAME_STATE.setSnakeLength(snake.getSnakeElements().size());
            putAppleInRandomPlace();
        }
    }

    private void checkSnakeIsOutsideOfBoard() {
        if (SNAKE_SERVICE.isOutsideOfBoard(snake, board)) {
            finishGame(GameState.State.FAIL_FINISH);
        }
    }

    private void checkSnakeCollisionWithSelf() {
        if (SNAKE_SERVICE.isCollisionWithSelf(snake)) {
            finishGame(GameState.State.FAIL_FINISH);
        }
    }

    private void finishGame(GameState.State state) {
        GAME_STATE.setEndPlayTime(new Date());
        GAME_STATE.setState(state);
        if (onFinishListener != null) {
            onFinishListener.onFinish();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paintApple(canvas);
        paintSnake(canvas);
    }

    public void paintApple(Canvas canvas) {
        if (apple != null) {
            Location2D appleLocation = apple.getLocation();
            int x = appleLocation.getX();
            int y = appleLocation.getY();
            Rect src = new Rect(0, 0, appleImage.getWidth(), appleImage.getHeight());
            RectF out = new RectF(0, 0, board.getFieldSize(), board.getFieldSize());
            out.offset(x * board.getFieldSize(), y * board.getFieldSize());
            canvas.drawBitmap(appleImage, src, out, null);
        }
    }

    public void paintSnake(Canvas canvas) {
        if (snake != null) {
            LinkedList<SnakeElement> snakeElements = snake.getSnakeElements();
            Iterator<SnakeElement> snakeIterator = snakeElements.iterator();
            SnakeElement headSnakeElement = snakeIterator.next();
            Location2D snakeElementLocation = headSnakeElement.getLocation();
            float x = snakeElementLocation.getX() * board.getFieldSize();
            float y = snakeElementLocation.getY() * board.getFieldSize();
            Paint paint = new Paint();
            paint.setColor(headSnakeElement.isHasApple() ? Color.DKGRAY : Color.GRAY);
            canvas.drawRect(x, y, x + board.getFieldSize(), y + board.getFieldSize(), paint);
            while (snakeIterator.hasNext()) {
                SnakeElement nextSnakeElement = snakeIterator.next();
                Location2D nextSnakeElementLocation = nextSnakeElement.getLocation();
                float nextX = nextSnakeElementLocation.getX() * board.getFieldSize();
                float nextY = nextSnakeElementLocation.getY() * board.getFieldSize();
                Paint nextPaint = new Paint();
                nextPaint.setColor(nextSnakeElement.isHasApple() ? Color.DKGRAY : Color.GRAY);
                canvas.drawRect(nextX + 2, nextY + 2, nextX + board.getFieldSize() - 2, nextY + board.getFieldSize() - 2, nextPaint);
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        touchSlideEvent.process(event);
        if (touchSlideEvent.isMove()) {
            float angle = touchSlideEvent.getAngle();
            if (angle >= 45 && angle < 135) {
                lastDirectionPressed = Direction.DOWN;
            } else if (angle >= 135 && angle < 225) {
                lastDirectionPressed = Direction.LEFT;
            } else if (angle >= 225 && angle < 315) {
                lastDirectionPressed = Direction.UP;
            } else {
                lastDirectionPressed = Direction.RIGHT;
            }
        }
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_DPAD_UP) {
            lastDirectionPressed = Direction.UP;
        } else if (keyCode == KeyEvent.KEYCODE_DPAD_DOWN) {
            lastDirectionPressed = Direction.DOWN;
        } else if (keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) {
            lastDirectionPressed = Direction.RIGHT;
        } else if (keyCode == KeyEvent.KEYCODE_DPAD_LEFT) {
            lastDirectionPressed = Direction.LEFT;
        }
        return super.onKeyDown(keyCode, event);
    }

    public interface OnFinishListener {
        void onFinish();
    }
}
