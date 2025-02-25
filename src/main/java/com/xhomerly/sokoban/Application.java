package com.xhomerly.sokoban;

import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Objects;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) {
        stage.getIcons().add(new Image(Objects.requireNonNull(Application.class.getResourceAsStream("icon.png"))));
        stage.setTitle("Sokoban");
        stage.setResizable(false);

        Menu menu = new Menu(stage);
        menu.show();
    }

    public static void main(String[] args) {
        launch();
    }
}