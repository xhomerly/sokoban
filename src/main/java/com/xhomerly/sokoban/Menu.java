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
        StackPane level = null;
        MapLoader.resetMap();
        levelNumberis = levelNumber;

        switch (levelNumber) {
            case 1 -> level = MapLoader.loadMap("src/main/resources/com/xhomerly/sokoban/level_01.xml");
            case 2 -> level = MapLoader.loadMap("src/main/resources/com/xhomerly/sokoban/level_02.xml");
            case 3 -> level = MapLoader.loadMap("src/main/resources/com/xhomerly/sokoban/level_03.xml");
            case 4 -> level = MapLoader.loadMap("src/main/resources/com/xhomerly/sokoban/level_04.xml");
            case 5 -> level = MapLoader.loadMap("src/main/resources/com/xhomerly/sokoban/level_05.xml");
        }

        Scene scene = Player.handleInput(level);
        stage.setScene(scene);
        stage.show();
    }

    public static void startLevelFromDifferentClass() {
        if (instance != null && levelNumberis <= 4) {
            instance.startLevel(levelNumberis+1);
        } else if (instance != null) {
            instance.show();
            levelNumberis = 0;
        } else {
            System.err.println("Error: Menu instance is not initialized!");
        }
    }
}
