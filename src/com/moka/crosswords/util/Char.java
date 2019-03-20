package com.moka.crosswords.util;

import com.moka.crosswords.util.Coords;

public class Char {

    private Coords coords;
    private char chr;

    public Char(char chr, Coords coords) {
        this.coords = coords.clone();
        this.chr = chr;
    }

    public Coords getCoords() {
        return coords;
    }

    public char getChr() {
        return chr;
    }


}
