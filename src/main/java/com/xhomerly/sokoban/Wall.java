package com.xhomerly.sokoban;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Wall extends Cell {
    private final ImageView image = new ImageView(new Image(getClass().getResourceAsStream("images/wall.png")));

    public ImageView getImage() {
        return image;
    }
}
