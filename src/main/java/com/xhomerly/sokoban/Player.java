package com.xhomerly.sokoban;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import java.util.Objects;

public class Player extends Cell {
    private static int x, y;

    public Player(int x, int y) {
        super(loadImage());
        Player.x = x;
        Player.y = y;
    }

    private static Image loadImage() {
        return new Image(Objects.requireNonNull(Crate.class.getResourceAsStream("images/player.png")));
    }

    public static int getX() {
        return x;
    }

    public static int getY() {
        return y;
    }

    public static void setX(int x) {
        Player.x = x;
    }

    public static void setY(int y) {
        Player.y = y;
    }

    public static Scene handleInput(Parent level) {
        Scene scene = new Scene(level);
        scene.setOnKeyPressed((KeyEvent event) -> {
            if (event.getCode() == KeyCode.ESCAPE) {
                Application.loadMenu(); // return to the main menu
            }
            if (event.getCode() == KeyCode.W || event.getCode() == KeyCode.UP) {
                MapLoader.movePlayer("W");
            }
            if (event.getCode() == KeyCode.S || event.getCode() == KeyCode.DOWN) {
                MapLoader.movePlayer("S");
            }
            if (event.getCode() == KeyCode.A || event.getCode() == KeyCode.LEFT) {
                MapLoader.movePlayer("A");
            }
            if (event.getCode() == KeyCode.D || event.getCode() == KeyCode.RIGHT) {
                MapLoader.movePlayer("D");
            }
            if (event.isControlDown() && event.getCode() == KeyCode.R) {
                Menu.startLevelFromDifferentClass(true); // restart the current level
            }
        });
        return scene;
    }
}
