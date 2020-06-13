package com.suzuyo.actors.apple;

import com.suzuyo.common.Location2D;

import java.awt.*;
import java.util.Random;

import static com.suzuyo.gui.BoardPanel.FIELD_SIZE;

public class AppleService {
    private static AppleService INSTANCE;

    public Apple createAppleByRandomFromRange(int x, int y) {
        Random random = new Random();
        int randomX = (int) (random.nextDouble() * x);
        int randomY = (int) (random.nextDouble() * y);
        return new Apple(randomX, randomY);
    }

    public void paint(Graphics2D g2d, Apple apple) {
        Location2D appleLocation = apple.getLocation();
        int x = appleLocation.getX() * FIELD_SIZE;
        int y = appleLocation.getY() * FIELD_SIZE;
        g2d.drawImage(apple.getImage(), x, y, FIELD_SIZE, FIELD_SIZE, null);
    }

    public static synchronized AppleService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new AppleService();
        }
        return INSTANCE;
    }
}
