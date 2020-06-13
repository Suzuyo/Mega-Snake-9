package com.suzuyo.actors.tunnel;

import com.suzuyo.ImageProvider;
import com.suzuyo.common.Location2D;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.awt.image.BufferedImage;

@Getter
@RequiredArgsConstructor
public class TunnelElement {
    private static final ImageProvider IMAGE_PROVIDER = ImageProvider.getInstance();
    private final Type type;
    private final Location2D location;

    @Getter
    @RequiredArgsConstructor
    public enum Type {
        TOP_LEFT_CORNER(IMAGE_PROVIDER.getTopLeftCornerTunnel()),
        TOP_RIGHT_CORNER(IMAGE_PROVIDER.getTopRightCornerTunnel()),
        BOTTOM_LEFT_CORNER(IMAGE_PROVIDER.getBottomLeftCornerTunnel()),
        BOTTOM_RIGHT_CORNER(IMAGE_PROVIDER.getBottomRightCornerTunnel()),
        HORIZONTAL(IMAGE_PROVIDER.getHorizontalTunnel()),
        VERTICAL(IMAGE_PROVIDER.getVerticalTunnel());

        private final BufferedImage image;
    }
}
