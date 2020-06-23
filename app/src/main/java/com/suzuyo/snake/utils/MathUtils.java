package com.suzuyo.snake.utils;

public class MathUtils {

    public static float getAngleBetweenTwoVector(float x1, float y1, float x2, float y2) {
        float deltaX = x2 - x1;
        float deltaY = y2 - y1;
        float degrees = (float) Math.toDegrees(Math.atan2(deltaY, deltaX));
        if (deltaY < 0) {
            return 360 + degrees;
        }
        return degrees;
    }
}
