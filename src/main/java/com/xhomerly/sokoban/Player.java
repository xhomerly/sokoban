package com.xhomerly.sokoban;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Player extends Cell {
    public Player(int x, int y) {
        super(x, y, "images/Player/player_01.png");
    }

    public static Scene handleInput(Parent level) {
        Scene scene = new Scene(level);
        scene.setOnKeyPressed((KeyEvent event) -> {
            if (event.getCode() == KeyCode.ESCAPE) {
                System.out.println("show menu");
            }
            if (event.getCode() == KeyCode.W || event.getCode() == KeyCode.UP) {

            }
            if (event.getCode() == KeyCode.S || event.getCode() == KeyCode.DOWN) {

            }
            if (event.getCode() == KeyCode.A || event.getCode() == KeyCode.LEFT) {

            }
            if (event.getCode() == KeyCode.D || event.getCode() == KeyCode.RIGHT) {

            }
        });
        return scene;
    }
}
