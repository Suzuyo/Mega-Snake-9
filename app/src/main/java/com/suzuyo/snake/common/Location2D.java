package com.suzuyo.snake.common;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
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
}
