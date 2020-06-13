package com.suzuyo.actors.tunnel;

import com.suzuyo.ImageProvider;
import com.suzuyo.common.Location2D;

import java.awt.*;
import java.awt.image.BufferedImage;

import static com.suzuyo.gui.BoardPanel.FIELD_SIZE;

public class TunnelService {
    private static TunnelService INSTANCE;

    public void paint(Graphics2D g2d, Tunnel tunnel) {
        TunnelElement[] tunnelElements = tunnel.getElements();
        for (TunnelElement tunnelElement : tunnelElements) {
            Location2D snakeShadowElementLocation = tunnelElement.getLocation();
            int x = snakeShadowElementLocation.getX() * FIELD_SIZE;
            int y = snakeShadowElementLocation.getY() * FIELD_SIZE;
            g2d.drawImage(tunnelElement.getType().getImage(), x, y, FIELD_SIZE, FIELD_SIZE, null);
        }
        if (tunnelElements.length > 0) {
            Location2D snakeShadowElementLocation = tunnelElements[0].getLocation();
            int x = snakeShadowElementLocation.getX() * FIELD_SIZE;
            int y = snakeShadowElementLocation.getY() * FIELD_SIZE;
            int pointSize = FIELD_SIZE / 2;
            g2d.fillOval(x + (pointSize / 2), y + (pointSize / 2), pointSize, pointSize);
        }
    }

    public static synchronized TunnelService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TunnelService();
        }
        return INSTANCE;
    }
}
