package com.xhomerly.sokoban;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        GridPane root = new GridPane();
        Scene scene = new Scene(root, 600, 600);
        stage.getIcons().add(new Image(Application.class.getResourceAsStream("icon.png")));
        stage.setTitle("Sokoban");

        controller(root);

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public void controller(GridPane root) {
        Wall wall = new Wall(0,0);

    }
}