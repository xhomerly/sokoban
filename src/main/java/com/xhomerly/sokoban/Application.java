package com.xhomerly.sokoban;

import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.util.Objects;

public class Application extends javafx.application.Application {
    private static Stage primaryStage;

    @Override
    public void start(Stage stage) {
        primaryStage = stage;
        stage.getIcons().add(new Image(Objects.requireNonNull(Application.class.getResourceAsStream("icon.png"))));
        stage.setTitle("Sokoban");
        stage.setResizable(false);

        loadMenu();
    }

    public static void loadMenu() {
        Menu menu = new Menu(primaryStage);
        menu.show();
    }

    public static void main(String[] args) {
        launch();
    }
}