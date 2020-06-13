package com.suzuyo.actors.tunnel;

import com.suzuyo.common.Location2D;
import lombok.Getter;

@Getter
public class Tunnel {
    private int step = 0;
    private final int number;
    private final TunnelElement[] elements;

    public Tunnel(int number, TunnelElement[] tunnelElements) {
        this.number = number;
        this.elements = tunnelElements;
    }

    public void nextStep() {
        step++;
    }

    public Location2D getCurrentStepLocation() {
        return elements[step].getLocation();
    }
}
