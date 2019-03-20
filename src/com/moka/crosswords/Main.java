package com.moka.crosswords;

import com.moka.crosswords.util.Orientation;

public class Main {

    public static void main(String[] args) {
        new Crosswords(
            /* List of words to add */
            new String[]{"banana","toastbrot","apfel","birne","kirsche","pflaume","kiwi","banane"},
            /* List of allowed orientations for words */
            new int[]{Orientation.HORI_RIGHT, Orientation.VERT_DOWN}
        );
    }
}
