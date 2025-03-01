package com.xhomerly.sokoban;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public abstract class Cell extends StackPane {
    public Cell(Image image) {
        ImageView imageView = new ImageView(image);

        imageView.setFitWidth(45);
        imageView.setFitHeight(45);

        getChildren().add(imageView);
    }
}
