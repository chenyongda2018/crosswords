package com.moka.crosswords.util;

public class Step {

    public static void doStep(Coords coords, int orientation) {

        int x = coords.getX();
        int y = coords.getY();

        switch(orientation) {
            case Orientation.HORI_RIGHT:
                x++; break;
            case Orientation.HORI_LEFT:
                x--; break;
            case Orientation.VERT_DOWN:
                y++; break;
            case Orientation.VERT_UP:
                y--; break;
            case Orientation.DIAG_UPLEFT:
                y--;x--; break;
            case Orientation.DIAG_UPRIGHT:
                y--;x++; break;
            case Orientation.DIAG_DOWNLEFT:
                y++;x--; break;
            case Orientation.DIAG_DOWNRIGHT:
                y++;x++; break;
        }

        coords.setX(x);
        coords.setY(y);
    }

    public static void doCounterSteps(Coords coords, int orientation, int stepNum) {

        int counterOrientation = Orientation.getCounter(orientation);

        for(int i = 0; i < stepNum; i++) {
            doStep(coords, counterOrientation);
        }
    }
}
