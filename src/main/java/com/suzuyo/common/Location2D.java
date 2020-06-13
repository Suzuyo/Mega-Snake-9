package com.suzuyo.common;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Location2D {
    private int x;
    private int y;

    public Location2D(Location2D location2D) {
        this.x = location2D.x;
        this.y = location2D.y;
    }

    public boolean isOutside(int left, int right, int top, int bottom) {
        return x < left || x > right || y < top || y > bottom;
    }

    public Location2D subtract(Location2D location) {
        Location2D subtractLocation = new Location2D();
        subtractLocation.setX(x - location.getX());
        subtractLocation.setY(y - location.getY());
        return subtractLocation;
    }
}
