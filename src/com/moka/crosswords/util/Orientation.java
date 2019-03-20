package com.moka.crosswords.util;

public class Orientation {

    public static final int
            HORI_RIGHT = 0,
            HORI_LEFT = 1,
            VERT_UP = 2,
            VERT_DOWN = 3,
            DIAG_UPLEFT = 4,
            DIAG_UPRIGHT = 5,
            DIAG_DOWNLEFT = 6,
            DIAG_DOWNRIGHT = 7;

    public static int getCounter(int orientation) {
        switch(orientation) {
            case Orientation.HORI_RIGHT:
                return HORI_LEFT;
            case Orientation.HORI_LEFT:
                return HORI_RIGHT;
            case Orientation.VERT_DOWN:
                return VERT_UP;
            case Orientation.VERT_UP:
                return VERT_DOWN;
            case Orientation.DIAG_UPLEFT:
                return DIAG_DOWNRIGHT;
            case Orientation.DIAG_UPRIGHT:
                return DIAG_DOWNLEFT;
            case Orientation.DIAG_DOWNLEFT:
                return DIAG_UPRIGHT;
            case Orientation.DIAG_DOWNRIGHT:
                return DIAG_UPRIGHT;
        }
        return -1;
    }
}
