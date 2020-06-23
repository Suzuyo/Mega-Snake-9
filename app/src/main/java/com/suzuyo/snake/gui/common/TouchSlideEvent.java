package com.suzuyo.snake.gui.common;

import android.view.MotionEvent;
import com.suzuyo.snake.utils.MathUtils;
import lombok.Getter;

public class TouchSlideEvent {
    @Getter private boolean isMove;
    private float startX;
    private float startY;
    @Getter private float angle;

    public void process(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            startX = event.getX();
            startY = event.getY();
            isMove = false;
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            angle = MathUtils.getAngleBetweenTwoVector(startX, startY, event.getX(), event.getY());
            isMove = true;
        }
    }
}
