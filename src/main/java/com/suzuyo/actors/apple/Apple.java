package com.suzuyo.actors.apple;

import com.suzuyo.ImageProvider;
import com.suzuyo.common.Location2D;
import lombok.*;

import java.awt.image.BufferedImage;

@Getter
@Setter
@AllArgsConstructor
public class Apple {
    private static final BufferedImage IMAGE = ImageProvider.getInstance().getAppleImage();
    private Location2D location;

    public Apple() {
        this(0, 0);
    }

    public Apple(int x, int y) {
        this.location = new Location2D(x, y);
    }

    public BufferedImage getImage() {
        return IMAGE;
    }
}
