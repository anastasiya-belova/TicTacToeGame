/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamelogic;

import gamelogic.entity.State;
import gamelogic.entity.Cell;

/**
 * Singleton class. Do not use this in a multithreading. This class stores the
 * game settings (the size of playing field, the length of winning combination,
 * etc.) and the current state of the playing field. This class also provides
 * changes to the playing field and is able to analyze it.
 *
 * @author Anastasiya Belova
 */
public class GameManager {

    private static final GameManager INSTANCE = new GameManager();

    private GameManager() {
    }

    public static GameManager getInstance() {
        return INSTANCE;
    }

    private int lengthOfWinComb;

    private Cell[][] playingField;

    private String startCondition;

    private int fieldWidth;

    private int fieldHeight;

    /**
     * Sets length of winning combination
     *
     * @param l - length of winning combination
     */
    public void setLengthOfWinComb(int l) {
        this.lengthOfWinComb = l;
    }

    /**
     * Returns the length of winning combination.
     *
     * @return length of winning combination
     */
    public int getLengthOfWinComb() {
        return this.lengthOfWinComb;
    }

    public void setField(Cell[][] playingField) {
        this.playingField = playingField;
    }

    public Cell[][] getField() {
        return this.playingField;
    }

    public String getStartCondition() {
        return this.startCondition;
    }

    public void setStartCondition(String startCondition) {
        this.startCondition = startCondition;
    }

    public int getFieldWidth() {
        return fieldWidth;
    }

    public void setFieldWidth(int fieldWidth) {
        this.fieldWidth = fieldWidth;
    }

    public int getFieldHeight() {
        return fieldHeight;
    }

    public void setFieldHeight(int fieldHeight) {
        this.fieldHeight = fieldHeight;
    }

    public boolean isEndingCondition() {
        return this.analyze().isFinal();
    }

    public GameProcess createGame() {
        return new GameProcess(INSTANCE);
    }

    /**
     * Sets element to field to the location with the specified coordinates. NB!
     * The coordinate values are not equal to the numbering of the elements in
     * the array. Line numbering starts at the top with 1. Column numbering
     * starts on the left with 1.
     *
     * @param element
     * @param xCoordinate
     * @param yCoordinate
     * @return whether the element was set on the playing field
     */
    public boolean setElementToField(Cell element, int xCoordinate, int yCoordinate) {
        int row = yCoordinate - 1;
        int column = xCoordinate - 1;
        if (!Cell.EMPTY.equals(playingField[row][column])) {
            System.out.println("This cell is occupied! Choose another one!");
            return false;
        } else {
            playingField[row][column] = element;
            return true;
        }
    }

    /**
     * Searches for whether there are winning combinations on the playing field,
     * checks the possibility of this combination appearing on the playing
     * field. Returns the corresponding state of the playing field.
     *
     * @see State
     * @return state of the playing field
     */
    public State analyze() {
        int countCross = 0;
        int countZero = 0;
        int countEmpty = 0;
        boolean xWins = false;
        boolean oWins = false;
        for (Cell[] row : playingField) {
            for (Cell column : row) {
                switch (column) {
                    case CROSS:
                        countCross++;
                        break;
                    case ZERO:
                        countZero++;
                        break;
                    default:
                        countEmpty++;
                        break;
                }
            }
        }
        //checking the possibility of this combination appearing on the playing field
        if (Math.abs(countZero - countCross) > 1) {
            return State.IMPOSSIBLE;
        }
        //checking strings
        for (Cell[] row : playingField) {
            for (int element = 0; element < row.length - lengthOfWinComb + 1; element++) {
                if (!Cell.EMPTY.equals(row[element])) {
                    boolean isSame = true;
                    for (int count = 1; count < lengthOfWinComb; count++) {
                        isSame = isSame && row[element].equals(row[element + count]);
                    }
                    if (isSame && Cell.CROSS.equals(row[element])) {
                        xWins = true;
                    } else if (isSame && Cell.ZERO.equals(row[element])) {
                        oWins = true;
                    }
                }
            }
        }
        //checking columns
        for (int element = 0; element < playingField[0].length; element++) {
            for (int row = 0; row < playingField.length - lengthOfWinComb + 1; row++) {
                if (!Cell.EMPTY.equals(playingField[row][element])) {
                    boolean isSame = true;
                    for (int count = 1; count < lengthOfWinComb; count++) {
                        isSame = isSame && playingField[row][element].equals(playingField[row + count][element]);
                    }
                    if (isSame && Cell.CROSS.equals(playingField[row][element])) {
                        xWins = true;
                    } else if (isSame && Cell.ZERO.equals(playingField[row][element])) {
                        oWins = true;
                    }
                }
            }
        }
        //checking diagonals
        for (int row = 0; row < playingField.length - lengthOfWinComb + 1; row++) {
            for (int element = 0; element < playingField[row].length - lengthOfWinComb + 1; element++) {
                if (!Cell.EMPTY.equals(playingField[row][element])) {
                    boolean isSame = true;
                    for (int count = 1; count < lengthOfWinComb; count++) {
                        isSame = isSame && playingField[row][element].equals(playingField[row + count][element + count]);
                    }
                    if (isSame && Cell.CROSS.equals(playingField[row][element])) {
                        xWins = true;
                    } else if (isSame && Cell.ZERO.equals(playingField[row][element])) {
                        oWins = true;
                    }
                }
            }
        }
        for (int row = 0; row < playingField.length - lengthOfWinComb + 1; row++) {
            for (int element = playingField[row].length - 1; element > lengthOfWinComb - 2; element--) {
                if (!Cell.EMPTY.equals(playingField[row][element])) {
                    boolean isSame = true;
                    for (int count = 1; count < lengthOfWinComb; count++) {
                        isSame = isSame && playingField[row][element].equals(playingField[row + count][element - count]);
                    }
                    if (isSame && Cell.CROSS.equals(playingField[row][element])) {
                        xWins = true;
                    } else if (isSame && Cell.ZERO.equals(playingField[row][element])) {
                        oWins = true;
                    }
                }
            }
        }
        if (xWins && oWins) {
            return State.IMPOSSIBLE;
        } else if (xWins) {
            return State.XWINS;
        } else if (oWins) {
            return State.OWINS;
        } else if (countEmpty == 0) {
            return State.DRAW;
        }
        return State.GAMENOTFINISHED;
    }

    /**
     * Prints the playing field by System.out.
     */
    public void printTable() {
        System.out.println("---------");
        for (Cell[] row : playingField) {
            System.out.print("| ");
            for (Cell column : row) {
                System.out.print(column.getChar());
                System.out.print(" ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }
}
