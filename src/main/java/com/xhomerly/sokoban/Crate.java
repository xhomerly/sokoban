package com.xhomerly.sokoban;

public class Crate extends Cell {
    private int x, y;

    public Crate(int x, int y) {
        super("images/crate_01.png");
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
