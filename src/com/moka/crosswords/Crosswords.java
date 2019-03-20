package com.moka.crosswords;

import com.moka.crosswords.util.Char;
import com.moka.crosswords.util.Coords;
import com.moka.crosswords.util.Orientation;
import com.moka.crosswords.util.Step;

import java.util.ArrayList;
import java.util.Arrays;

public class Crosswords {

    private ArrayList<Word> wordList;
    private int[] allowedOrientations;

    /**
     * Create a crossword generator.
     * @param answers - Array of words (answers) to fill the crossword with
     * @param allowedOrientations - Array of Orientation values that are allowed to be used. See @Orientation
     */
    public Crosswords(String[] answers, int[] allowedOrientations) {
        this.allowedOrientations = allowedOrientations;

        if(answers.length > 0 && allowedOrientations.length > 0) {
            wordList = new ArrayList<>();
            wordList.add(new Word( answers[0], new Coords(100,100), allowedOrientations[0]));

            /* Remove first word from answers array */
            answers = Arrays.copyOfRange(answers, 1, answers.length);
        }

        for(String answer : answers) {
            addAtBestLocation(answer);
        }

        printField();
    }

    /**
     * Adds a new word to the field. It finds the place and orientation to the existing words
     * that yields the most crossings of other words, without overlapping other, existing letters.
     * @param wordString - Word to add to the field
     */
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

                        /* Get used orientations of all words at this location */
                        ArrayList<Integer> usedOrientations = this.getUsedOrientations( this.getWordsAt(c.getCoords()) );

                        /* Try all allowed orientations to get match */
                        for(int ori : allowedOrientations) {

                            /* Skip check if any word at this location uses that orientation already */
                            if(usedOrientations.contains(ori) || usedOrientations.contains(Orientation.getCounter(ori))) {
                                continue;
                            }

                            /* Get coords of character */
                            Coords chrCoords = c.getCoords().clone();

                            /* Create new word at this location */
                            /* Since we're "i" chars in, we calculate the start location by going the other way */
                            Step.doCounterSteps(chrCoords,ori,i);

                            /* chrCoords now has the true starting Location. Test-place word here */
                            Word newWord = new Word(wordString, chrCoords, ori);

                            /* Get number of matching chars */
                            int matches = countMatches(newWord);

                            /* Update match info if new one is better */
                            if(matches > mostMatches) {
                                mostMatches = matches;
                                bestCoords = chrCoords.clone();
                                bestOrientation = ori;
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

    /**
     * Count the number of crossings a Word object (which has a location and orientation)
     * has with the existing field. Exceptions from the rule are
     * - if characters of other words would be replaced instead of crossed -> return 0
     * - if other words on the field, which do not cross this word, would touch it (there has to be space between them) -> return 0
     * @param word - Word object to check against field
     * @return int - number of crossings the word would have with the field words
     */
    private int countMatches(Word word) {
        ArrayList<Word> crossingWords = new ArrayList<>();
        int count = 0;

        /* Go through each char of the new word */
        for(Char c : word.getCharList()) {
            Char fieldChar = getCharAt(c.getCoords());

            /* If there's a char on the possible new chars location already on the field */
            if(fieldChar != null) {
                /* And it matches the new char? */
                if((fieldChar.getChr()+"").equalsIgnoreCase(c.getChr()+"")) {

                    /* It's a match! */
                    count++;

                    /* Get all words at this location (without duplicates), for later inspection */
                    ArrayList<Word> coordWords = getWordsAt(c.getCoords());
                    for(Word w : coordWords) {
                        if(!crossingWords.contains(w)) crossingWords.add(w);
                    }

                /* If it doesn't match, then it's another word. Return. We don't want to replace that character */
                } else {
                    return 0;
                }
            }
        }

        /* Check nearest neighbours. Only words crossing the new word are allowed to "touch" it */
        for(Char c : word.getCharList()) {

            /* Iterate through all directions */
            for(int ori = 0; ori < 8; ori++) {
                /* Move one step into each direction */
                Coords neighCoords = c.getCoords().clone();
                Step.doStep(neighCoords,ori);

                /* Get words at this neighbour location */
                ArrayList<Word> neighWords = getWordsAt(neighCoords);
                for(Word w : neighWords) {
                    /* If any char on a neighbouring position belongs to a word that doesnt cross this one, quit and return 0 */
                    if(!crossingWords.contains(w)) {
                        return 0;
                    }
                }
            }
        }

        /* Return number of crossings */
        return count;
    }

    /**
     * Return the Char object of the field at the given location
     * @param coords - Coordinates of location to check
     * @return - Char at this location, or null if location is empty
     */
    private Char getCharAt(Coords coords) {
        for(Word w : wordList) {
            for(Char c : w.getCharList()) {
                if(c.getCoords().equals(coords)) {
                    return c;
                }
            }
        }

        return null;
    }

    /**
     * Return a list of words that cross the given location
     * @param coords - Coordinations of location to check
     * @return - ArrayList<Word> list of words that cross this location
     */
    private ArrayList<Word> getWordsAt(Coords coords) {
        ArrayList<Word> words = new ArrayList<>();

        for(Word w : wordList) {
            for(Char c : w.getCharList()) {
                if(c.getCoords().equals(coords)) {
                    words.add(w);
                }
            }
        }
        return words;
    }

    /**
     * Return a list of Orientation values used by the given words
     * @param words - List of words to get Orientations of
     * @return - List of orientations that the checked words have
     */
    private ArrayList<Integer> getUsedOrientations(ArrayList<Word> words) {
        ArrayList<Integer> usedOrientations = new ArrayList<>();
        words.forEach((w) -> usedOrientations.add(w.getOrientation()));
        return usedOrientations;
    }

    /**
     * Prints out the crossword puzzle.
     * For this it calculates the boundaries of the field, creates
     * a 2D array using these boundaries, writes the words to it and then prints it.
     */
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
