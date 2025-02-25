package com.xhomerly.sokoban;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import java.util.Objects;

public abstract class Cell extends StackPane {
    private int x;
    private int y;

    public Cell(int x, int y, String imagePath) {
        this.x = x;
        this.y = y;
        ImageView imageView = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream(imagePath))));

        imageView.setFitWidth(45);
        imageView.setFitHeight(45);

        getChildren().add(imageView);
    }

    //TODO: jsou tyto settery a gettery potřeba?

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
