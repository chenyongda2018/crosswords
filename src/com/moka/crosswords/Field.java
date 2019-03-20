package com.moka.crosswords;

import com.moka.crosswords.util.Char;
import com.moka.crosswords.util.Coords;
import com.moka.crosswords.util.Orientation;
import com.moka.crosswords.util.Step;

import java.util.ArrayList;

public class Field {
    private ArrayList<Word> wordList;

    public Field() {
        wordList = new ArrayList<>();

        wordList.add(new Word("baumkuchen", new Coords(100,100), Orientation.DIAG_DOWNLEFT));

        addAtBestLocation("toastbrot");

        printField();
    }

    public void addAtBestLocation(String wordString) {

        int mostMatches = 0, currentMatches;
        Coords bestCoords = null;
        int bestOrientation = -1;

        /* Go through the word string, char by char */
        for(int i = 0; i < wordString.length(); i++) {
            char chr = wordString.charAt(i);

            /* Go through every char already on the field */
            for(Word fieldWord : wordList) {
                for(Char c : fieldWord.getCharList()) {
                    /* If it matches */
                    if(c.getChr() == chr) {
                        /* Try all orientations to get match */
                        for(int o = 0; o < 8; o++) {

                            if(chr == 'a') {
                                boolean b = true;
                            }

                            /* Get coords of character */
                            Coords chrCoords = new Coords(c.getCoords());

                            /* Create new word at this location */
                            /* Since we're "i" chars in, we calculate the start location by going the other way */
                            Step.doCounterSteps(chrCoords,o,i);

                            /* chrCoords now has the true starting Location */
                            Word newWord = new Word(wordString, chrCoords, o);

                            /* Get number of matching chars */
                            int matches = countMatches(newWord);

                            if(matches > mostMatches) {
                                mostMatches = matches;
                                bestCoords = new Coords(chrCoords);
                                bestOrientation = o;
                            }
                        }
                    }
                }
            }
        }

        if(bestCoords != null && bestOrientation > -1) {
            Word finalWord = new Word(wordString, bestCoords, bestOrientation);
            wordList.add(finalWord);
            System.out.println("Added new word "+wordString+" at location "+bestCoords+" oriented: "+bestOrientation);
        } else {
            /* TODO: No best location found, or field empty, just place it somewhere */
        }
    }

    private int countMatches(Word word) {

        int count = 0;
        for(Char c : word.getCharList()) {
            Char fieldChar = getCharAt(c.getCoords());

            if(fieldChar != null) {
                if((fieldChar.getChr()+"").equalsIgnoreCase(c.getChr()+"")) {
                    count++;
                } else {
                    /* We dont want to replace an existing character */
                    return 0;
                }

            }

        }

        return count;
    }

    private Char getCharAt(Coords coords) {
        for(Word w : wordList) {
            for(Char c : w.getCharList()) {
                if(c.getCoords().getX() == coords.getX() && c.getCoords().getY() == coords.getY()) {
                    return c;
                }
            }
        }

        return null;
    }

    public void printField() {
        boolean firstSet = true;
        int minX = 0, minY = 0, maxX = 0, maxY = 0;

        /* find minimum and maximum bounds */
        for(Word w : wordList) {
            for(Char c : w.getCharList()) {
                int x = c.getCoords().getX();
                int y = c.getCoords().getY();

                /* Assuming no values were set yet */
                if(firstSet)
                {
                    /* Set them first */
                    minX = x;
                    minY = y;

                    maxX = x;
                    maxY = y;

                    firstSet = false;
                }

                minX = Math.min(x, minX);
                minY = Math.min(y, minY);

                maxX = Math.max(x, maxX);
                maxY = Math.max(y, maxY);
            }
        }

        /* Calculate values of the "distance" to shift every location so that it fits array indices */
        /* lets say minimum coordinate is [-50,-50]. to get that to be [0,0] in the array, it and all coords have to be moved by [+50,+50] */
        int shiftX = 0 - minX;
        int shiftY = 0 - minY;

        int fieldWidth = maxX + shiftX + 1;
        int fieldHeight = maxY + shiftY + 1;

        Char[][] field = new Char[fieldWidth][fieldHeight];

        /* Add them all shifted into the field array */
        for(Word w : wordList) {
            for(Char c : w.getCharList()) {
                int x = c.getCoords().getX() + shiftX;
                int y = c.getCoords().getY() + shiftY;

                field[x][y] = c;
            }
        }

        /* And then print it! */
        for(int y = 0; y < fieldHeight; y++) {
            for(int x = 0; x < fieldWidth; x++) {

                Char c = field[x][y];
                if(c != null) {
                    System.out.print(field[x][y].getChr());
                } else {
                    System.out.print('-');
                }
            }
            System.out.println();
        }

    }
}
