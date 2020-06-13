package com.suzuyo.actors.tunnel;

import com.suzuyo.common.Location2D;

public class TunnelProvider {

    private static Tunnel[] TUNNELS = {
            new Tunnel(1, new TunnelElement[] {
                    new TunnelElement(TunnelElement.Type.VERTICAL, new Location2D(12, 12)),
                    new TunnelElement(TunnelElement.Type.VERTICAL, new Location2D(12, 13)),
                    new TunnelElement(TunnelElement.Type.VERTICAL, new Location2D(12, 14))
            }),
            new Tunnel(2, new TunnelElement[] {
                    new TunnelElement(TunnelElement.Type.VERTICAL, new Location2D(8, 8)),
                    new TunnelElement(TunnelElement.Type.BOTTOM_LEFT_CORNER, new Location2D(8, 9)),
                    new TunnelElement(TunnelElement.Type.HORIZONTAL, new Location2D(9, 9))
            }),
            new Tunnel(3, new TunnelElement[] {
                    new TunnelElement(TunnelElement.Type.VERTICAL, new Location2D(23, 10)),
                    new TunnelElement(TunnelElement.Type.VERTICAL, new Location2D(23, 9)),
                    new TunnelElement(TunnelElement.Type.VERTICAL, new Location2D(23, 8))
            }),
            new Tunnel(4, new TunnelElement[] {
                    new TunnelElement(TunnelElement.Type.VERTICAL, new Location2D(0, 1)),
                    new TunnelElement(TunnelElement.Type.TOP_LEFT_CORNER, new Location2D(0, 0)),
                    new TunnelElement(TunnelElement.Type.TOP_RIGHT_CORNER, new Location2D(1, 0)),
                    new TunnelElement(TunnelElement.Type.VERTICAL, new Location2D(1, 1))
            }),
            new Tunnel(5, new TunnelElement[] {
                    new TunnelElement(TunnelElement.Type.HORIZONTAL, new Location2D(15, 18)),
                    new TunnelElement(TunnelElement.Type.BOTTOM_RIGHT_CORNER, new Location2D(16, 18)),
                    new TunnelElement(TunnelElement.Type.TOP_LEFT_CORNER, new Location2D(16, 17)),
                    new TunnelElement(TunnelElement.Type.HORIZONTAL, new Location2D(17, 17))
            }),
            new Tunnel(6, new TunnelElement[] {
                    new TunnelElement(TunnelElement.Type.HORIZONTAL, new Location2D(22, 0)),
                    new TunnelElement(TunnelElement.Type.TOP_RIGHT_CORNER, new Location2D(23, 0)),
                    new TunnelElement(TunnelElement.Type.VERTICAL, new Location2D(23, 1)),
                    new TunnelElement(TunnelElement.Type.VERTICAL, new Location2D(23, 2))
            }),
            new Tunnel(7, new TunnelElement[] {
                    new TunnelElement(TunnelElement.Type.HORIZONTAL, new Location2D(22, 18)),
                    new TunnelElement(TunnelElement.Type.TOP_LEFT_CORNER, new Location2D(21, 18)),
                    new TunnelElement(TunnelElement.Type.VERTICAL, new Location2D(21, 19)),
                    new TunnelElement(TunnelElement.Type.BOTTOM_LEFT_CORNER, new Location2D(21, 20)),
                    new TunnelElement(TunnelElement.Type.HORIZONTAL, new Location2D(22, 20))
            }),
            new Tunnel(8, new TunnelElement[] {
                    new TunnelElement(TunnelElement.Type.HORIZONTAL, new Location2D(12, 16)),
                    new TunnelElement(TunnelElement.Type.HORIZONTAL, new Location2D(11, 16)),
                    new TunnelElement(TunnelElement.Type.TOP_LEFT_CORNER, new Location2D(10, 16)),
                    new TunnelElement(TunnelElement.Type.BOTTOM_LEFT_CORNER, new Location2D(10, 17)),
                    new TunnelElement(TunnelElement.Type.HORIZONTAL, new Location2D(11, 17)),
                    new TunnelElement(TunnelElement.Type.TOP_RIGHT_CORNER, new Location2D(12, 17)),
                    new TunnelElement(TunnelElement.Type.BOTTOM_LEFT_CORNER, new Location2D(12, 18)),
                    new TunnelElement(TunnelElement.Type.HORIZONTAL, new Location2D(13, 18))
            })
    };

    public static Tunnel getTunnel(int length, int number) {
        for (Tunnel tunnel : TUNNELS) {
            if (tunnel.getNumber() == number && tunnel.getElements().length == length) {
                return tunnel;
            }
        }
        return null;
    }
}
