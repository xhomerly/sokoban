package com.xhomerly.sokoban;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Menu {
    private final Stage stage;
    private static Menu instance;
    private static int levelNumberis;

    public Menu(Stage stage) {
        this.stage = stage;
        instance = this;
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
        stage.setTitle("Sokoban");
        stage.show();
    }

    public void levelSelect() {
        VBox levelLayout = new VBox(10);
        levelLayout.setStyle("-fx-alignment: center; -fx-padding: 20;");

        Button level1 = new Button("Level 1");
        level1.setOnAction(_ -> startLevel(1));

        Button level2 = new Button("Level 2");
        level2.setOnAction(_ -> startLevel(2));

        Button level3 = new Button("Level 3");
        level3.setOnAction(_ -> startLevel(3));

        Button level4 = new Button("Level 4");
        level4.setOnAction(_ -> startLevel(4));

        Button level5 = new Button("Level 5");
        level5.setOnAction(_ -> startLevel(5));

        levelLayout.getChildren().addAll(level1, level2, level3, level4, level5);

        Scene levelScene = new Scene(levelLayout, 250, 300);
        stage.setScene(levelScene);
        stage.show();
    }

    public void startLevel(int levelNumber) {
        MapLoader.resetMap();
        levelNumberis = levelNumber;

        StackPane level = MapLoader.loadMap("src/main/resources/com/xhomerly/sokoban/level_0" + levelNumber + ".xml");

        Scene scene = Player.handleInput(level);
        stage.setScene(scene);
        stage.setTitle("Sokoban - Level " + levelNumberis);
        stage.show();
    }

    // handles level restarting and progression
    public static void startLevelFromDifferentClass(boolean isReset) {
        if (instance != null && levelNumberis <= 4 && !isReset) {
            instance.startLevel(levelNumberis+1);
        } else if (instance != null && levelNumberis <= 5) {
            instance.startLevel(levelNumberis);
        } else if (instance != null) {
            instance.show();
            levelNumberis = 0;
        } else {
            System.err.println("Error: Menu instance is not initialized!");
        }
    }
}
