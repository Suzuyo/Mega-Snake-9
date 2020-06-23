package com.suzuyo.snake.utils;

import android.annotation.SuppressLint;

import java.util.Date;

public class DurationUtils {
    static final int MINUTES_PER_HOUR = 60;
    static final int SECONDS_PER_MINUTE = 60;
    static final int SECONDS_PER_HOUR = SECONDS_PER_MINUTE * MINUTES_PER_HOUR;

    public static long between(Date start, Date end) {
        return (end.getTime() - start.getTime()) / 1000;
    }

    @SuppressLint("DefaultLocale")
    public static String toString(long allSeconds) {
        long hours = allSeconds / SECONDS_PER_HOUR;
        int minutes = (int) ((allSeconds % SECONDS_PER_HOUR) / SECONDS_PER_MINUTE);
        int seconds = (int) (allSeconds % SECONDS_PER_MINUTE);
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }
}
