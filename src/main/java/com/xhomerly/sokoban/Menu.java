package com.xhomerly.sokoban;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Menu {
    private final Stage stage;

    public Menu(Stage stage) {
        this.stage = stage;
    }

    public void show() {
        VBox menuLayout = new VBox(10);
        menuLayout.setStyle("-fx-alignment: center; -fx-padding: 20;");

        Button levelSelectButton = new Button("Select level");
        levelSelectButton.setOnAction(_ -> levelSelect());

        Button exitButton = new Button("Exit");
        exitButton.setOnAction(_ -> stage.close());

        menuLayout.getChildren().addAll(levelSelectButton, exitButton);

        Scene menuScene = new Scene(menuLayout, 250, 300);
        stage.setScene(menuScene);
        stage.show();
    }

    public void levelSelect() {
        VBox levelLayout = new VBox(10);
        levelLayout.setStyle("-fx-alignment: center; -fx-padding: 20;");

        Button level1 = new Button("Level 1");
        level1.setOnAction(_ -> startLevel(1));

        levelLayout.getChildren().addAll(level1);

        Scene levelScene = new Scene(levelLayout, 250, 300);
        stage.setScene(levelScene);
        stage.show();
    }

    public void startLevel(int levelNumber) {
        GridPane level;

        switch (levelNumber) {
            case 1 -> level = MapLoader.loadMap("src/main/resources/com/xhomerly/sokoban/template.xml");
            default -> level = MapLoader.loadMap("src/main/resources/com/xhomerly/sokoban/template.xml");
        }

        Scene scene = Player.handleInput(level);
        stage.setScene(scene);
        stage.show();
    }
}
