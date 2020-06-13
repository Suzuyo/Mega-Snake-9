package com.suzuyo;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ImageProvider {
    private static ImageProvider INSTANCE;

    public BufferedImage getAppleImage() {
        return readImage("apple");
    }

    public BufferedImage getBoardImage() {
        return readImage("board");
    }

    public BufferedImage getDefaultBackgroundImage() {
        return readImage("default-background");
    }

    public BufferedImage getMenuBackgroundImage() {
        return readImage("menu-background");
    }

    public BufferedImage getVerticalTunnel() {
        return readImage("tunnel/vertical-tunnel");
    }

    public BufferedImage getHorizontalTunnel() {
        return readImage("tunnel/horizontal-tunnel");
    }

    public BufferedImage getBottomLeftCornerTunnel() {
        return readImage("tunnel/bottom-left-corner-tunnel");
    }

    public BufferedImage getBottomRightCornerTunnel() {
        return readImage("tunnel/bottom-right-corner-tunnel");
    }

    public BufferedImage getTopLeftCornerTunnel() {
        return readImage("tunnel/top-left-corner-tunnel");
    }

    public BufferedImage getTopRightCornerTunnel() {
        return readImage("tunnel/top-right-corner-tunnel");
    }

    private BufferedImage readImage(String name) {
        try {
            InputStream inputStream = ImageProvider.class.getClassLoader().getResourceAsStream("images/" + name + ".png");
            return ImageIO.read(inputStream);
        } catch (IOException e) {
            System.exit(1);
            throw new RuntimeException(e);
        }
    }

    public static synchronized ImageProvider getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ImageProvider();
        }
        return INSTANCE;
    }
}
