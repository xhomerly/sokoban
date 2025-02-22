package com.xhomerly.sokoban;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public abstract class Cell extends StackPane {
    private int x, y;
    private ImageView imageView;

    public Cell(int x, int y, String imagePath) {
        this.x = x;
        this.y = y;
        this.imageView = new ImageView(new Image(getClass().getResourceAsStream(imagePath)));

        imageView.setFitWidth(45);
        imageView.setFitHeight(45);

        getChildren().add(imageView);
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
