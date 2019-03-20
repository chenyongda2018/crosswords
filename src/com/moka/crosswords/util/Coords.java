package com.moka.crosswords.util;

public class Coords {
    private int x, y;

    public Coords(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Coords clone() {
        return new Coords(x, y);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String toString() {
        return x+","+y;
    }

    public boolean equals(Coords b) {
        return this.x == b.getX() && this.y == b.getY();
    }

}
