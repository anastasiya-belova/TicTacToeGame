/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamelogic;

/**
 * Singleton class. 
 * @author Anastasiya Belova
 */
public class GameManager {
    
    private static final GameManager INSTANCE = new GameManager();
    
    private GameManager(){}
    
    public static GameManager getInstance(){
        return INSTANCE;
    }
    
    private Cell[][] playingField;
    
    private int lengthOfWinComb;
    
    /**
     * Sets length of winning combination
     * @param l - length of winning combination 
     */
    public void setLength(int l){
        this.lengthOfWinComb = l;
    }
    
    /**
     * Returns the length of winning combination. 
     * @return length of winning combination
     */
    public int getLength(){
        return this.lengthOfWinComb;
    }
    
    public Cell[][] getField(){
        return this.playingField;
    }
    
    public GameProcess createGame(){
        return new GameProcess(INSTANCE);
    }
    
    /**
     * Create a new playing field according to the specified values of width and height,
     * as well as filled in in accordance with the transmitted string. For example, 
     * when WIDTH = 3, HEIGHT = 3, str = "XO X OOXX", the constructed field will look
     * like this: (X O  )(X   O)(O X X)
     * @param str
     * @param WIDTH
     * @param HEIGHT
     */
    public void createPlayingField(String str, int WIDTH, int HEIGHT){
        Cell[][] field;
        char[] inputSymbols = str.toCharArray();
        field = new Cell[WIDTH][HEIGHT];
        int numOfChar = 0;
        for (Cell[] row : field) {
            for (int column = 0; column < row.length; column++) {
                switch (inputSymbols[numOfChar]) {
                    case 'X':
                        row[column] = Cell.CROSS;
                        break;
                    case 'O':
                        row[column] = Cell.ZERO;
                        break;
                    default:
                        row[column] = Cell.EMPTY;
                        break;
                }
                numOfChar++;
            }
        }
        this.playingField = field;
    }
    
    /**
     * Sets element to field to the location with the specified coordinates. NB!
     * The coordinate values are not equal to the numbering of the elements in the array.
     * Line numbering starts at the bottom with 1. Column numbering starts on the left with 1.
     * @param element
     * @param xCoordinate
     * @param yCoordinate
     * @return whether the element was set on the playing field
     */
    public boolean setElementToField(Cell element, int xCoordinate, int yCoordinate) {
        int row = playingField.length - yCoordinate;
        int column = xCoordinate - 1;
        if (!Cell.EMPTY.equals(playingField[row][column])) {
            System.out.println("This cell is occupied! Choose another one!");
            return false;
        }
        else{
            playingField[row][column] = element;
            return true;
        }
    }
    
    /**
     * Searches for whether there are winning combinations on the playing field, 
     * checks the possibility of this combination appearing on the playing field.
     * Returns the corresponding state of the playing field.
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
        if (Math.abs(countZero-countCross) > 1){
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
                    if (isSame && Cell.CROSS.equals(row[element])){
                        xWins = true;
                    }
                    else if (isSame && Cell.ZERO.equals(row[element])){
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
                        isSame = isSame && playingField[row][element].equals(playingField[row+count][element]);
                    }
                    if (isSame && Cell.CROSS.equals(playingField[row][element])){
                        xWins = true;
                    }
                    else if (isSame && Cell.ZERO.equals(playingField[row][element])){
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
        }
        else if(xWins){
            return State.XWINS;
        }
        else if(oWins){
            return State.OWINS;
        }
        else if(countEmpty == 0){
            return State.DRAW;
        }
        return State.GAMENOTFINISHED;
    }
}
