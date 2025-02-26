package com.xhomerly.sokoban;

import javafx.scene.image.Image;

import java.util.Objects;

public class DeliveryPoint extends Cell {
    private int x, y;

    public DeliveryPoint(int x, int y) {
        super(loadImage());
        this.x = x;
        this.y = y;
    }

    public DeliveryPoint() {
        super(loadImage());
    }

    private static Image loadImage() {
        return new Image(Objects.requireNonNull(Crate.class.getResourceAsStream("images/delivery_point.png")));
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
