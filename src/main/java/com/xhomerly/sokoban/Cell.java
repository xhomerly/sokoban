package com.xhomerly.sokoban;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import java.util.Objects;

public abstract class Cell extends StackPane {
    public Cell(String imagePath) {
        ImageView imageView = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream(imagePath))));

        imageView.setFitWidth(45);
        imageView.setFitHeight(45);

        getChildren().add(imageView);
    }
}
