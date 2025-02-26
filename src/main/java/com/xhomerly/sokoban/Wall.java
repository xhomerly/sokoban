package com.xhomerly.sokoban;

import javafx.scene.image.Image;

import java.util.Objects;

public class Wall extends Cell {
    public Wall() {
        super(loadImage());
    }

    private static Image loadImage() {
        return new Image(Objects.requireNonNull(Crate.class.getResourceAsStream("images/wall.png")));
    }
}
