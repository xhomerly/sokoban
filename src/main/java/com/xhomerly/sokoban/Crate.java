package com.xhomerly.sokoban;

public class Crate extends Cell {
    private static int x, y;

    public Crate(int x, int y) {
        super("images/crate_01.png");
        Crate.x = x;
        Crate.y = y;
    }

    public static int getX() {
        return x;
    }

    public static int getY() {
        return y;
    }

    public static void setX(int x) {
        Crate.x = x;
    }

    public static void setY(int y) {
        Crate.y = y;
    }
}
