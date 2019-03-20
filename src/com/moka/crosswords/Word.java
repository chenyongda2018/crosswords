package com.moka.crosswords;

import com.moka.crosswords.util.Char;
import com.moka.crosswords.util.Coords;
import com.moka.crosswords.util.Step;

import java.util.ArrayList;

public class Word {

    private String word;
    private int orientation;
    private Coords location;

    private ArrayList<Char> charList;

    /**
     * Create a new Word object
     * @param word - String reprensation of the word to add
     * @param startCoords - Starting location where the string starts
     * @param orientation - Orientation of the string in the field
     */
    public Word(String word, Coords startCoords, int orientation) {
        this.word = word;
        this.location = startCoords;
        this.orientation = orientation;

        this.setLocation(startCoords);
    }

    public ArrayList<Char> getCharList() {
        return charList;
    }

    /**
     * Move the word to a new location
     * @param coords - New starting location to move word to
     */
    public void setLocation(Coords coords) {
        charList = new ArrayList<>();

        /* Clone the object */
        Coords co = coords.clone();

        /* Iterate through word */
        for(int i = 0; i < word.length(); i++) {
            char chr = word.charAt(i);

            /* Add this character at current location to list */
            charList.add(new Char(chr, co));

            /* Walk one step along orientation */
            Step.doStep(co, orientation);
        }
    }

    public int getOrientation() {
        return orientation;
    }
}
