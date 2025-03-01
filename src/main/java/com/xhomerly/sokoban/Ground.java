package com.xhomerly.sokoban;

import javafx.scene.image.Image;
import java.util.Objects;

public class Ground extends Cell {
    public Ground() {
        super(loadImage());
    }

    private static Image loadImage() {
        return new Image(Objects.requireNonNull(Crate.class.getResourceAsStream("images/ground.png")));
    }
}
