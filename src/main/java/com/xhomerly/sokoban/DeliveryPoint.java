package com.xhomerly.sokoban;

import javafx.scene.image.Image;

import java.util.Objects;

public class DeliveryPoint extends Cell {
    public DeliveryPoint() {
        super(loadImage());
    }

    private static Image loadImage() {
        return new Image(Objects.requireNonNull(Crate.class.getResourceAsStream("images/delivery_point.png")));
    }
}
