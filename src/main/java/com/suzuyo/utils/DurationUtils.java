package com.suzuyo.utils;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class DurationUtils {
    static final int MINUTES_PER_HOUR = 60;
    static final int SECONDS_PER_MINUTE = 60;
    static final int SECONDS_PER_HOUR = SECONDS_PER_MINUTE * MINUTES_PER_HOUR;

    public static Duration between(LocalDateTime start, LocalDateTime end) {
        return Duration.between(start.toInstant(ZoneOffset.UTC), end.toInstant(ZoneOffset.UTC));
    }

    public static String toString(Duration duration) {
        long allSeconds = duration.getSeconds();
        long hours = allSeconds / SECONDS_PER_HOUR;
        int minutes = (int) ((allSeconds % SECONDS_PER_HOUR) / SECONDS_PER_MINUTE);
        int seconds = (int) (allSeconds % SECONDS_PER_MINUTE);
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }
}
