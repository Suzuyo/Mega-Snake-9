package com.suzuyo.snake;

import android.os.Handler;

import java.util.Timer;
import java.util.TimerTask;

public class PlayTimer extends Timer {
    private Handler handler = new Handler();

    public void schedule(long delay, long period, Runnable runnable) {
        schedule(new TimerTask() {
            public void run() {
                handler.post(runnable);
            }
        }, delay, period);
    }
}
