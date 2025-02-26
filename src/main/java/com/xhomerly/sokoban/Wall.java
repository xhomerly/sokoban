package com.xhomerly.sokoban;

import javafx.scene.image.Image;

import java.util.Objects;

public class Wall extends Cell {
    private int x, y;

    public Wall(int x, int y) {
        super(loadImage());
        this.x = x;
        this.y = y;
    }

    private static Image loadImage() {
        return new Image(Objects.requireNonNull(Crate.class.getResourceAsStream("images/wall.png")));
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
