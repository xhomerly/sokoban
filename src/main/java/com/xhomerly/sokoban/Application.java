package com.xhomerly.sokoban;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        VBox root = new VBox();
        Scene scene = new Scene(root, 600, 600);
        stage.getIcons().add(new Image(Application.class.getResourceAsStream("icon.png")));
        stage.setTitle("Sokoban");

        controller();

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public void controller() {

    }
}