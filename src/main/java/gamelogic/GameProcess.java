/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamelogic;

import gamelogic.entity.Cell;
import IO.InputManager;

/**
 * This is the implementation of the game process.
 *
 * @author Anastasiya Belova
 */
public class GameProcess extends AbstractGameProcess {

    private boolean isXMove;

    GameProcess(GameManager gameManager) {
        super(gameManager);
        isXMove = true;
    }

    @Override
    protected void setPlayers() {
        //so far nothing
    }

    /**
     * Create a new playing field according to the parameters of the game
     * manager. For example, when WIDTH = 3, HEIGHT = 3, str = "XO X OOXX", the
     * constructed field will look like this: (X O )(X O)(O X X)
     */
    @Override
    protected void createField() {
        int WIDTH = super.getGameManager().getFieldWidth();
        int HEIGHT = super.getGameManager().getFieldHeight();
        String str = super.getGameManager().getStartCondition();
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
        super.getGameManager().setField(field);
        super.getGameManager().printTable();
    }

    /**
     * Inserts one element on the playing field in the cell whose coordinates
     * were specified by the player.
     */
    @Override
    protected void iterateGame() {
        boolean isSetted = false;
        while (!isSetted) {
            int[] coordinates = InputManager.readTwoNumbersFromString(1, 3);
            int x = coordinates[0];
            int y = coordinates[1];
            if (isXMove) {
                if (super.getGameManager().setElementToField(Cell.CROSS, x, y)) {
                    isSetted = true;
                    isXMove = false;
                }
            } else {
                if (super.getGameManager().setElementToField(Cell.ZERO, x, y)) {
                    isSetted = true;
                    isXMove = true;
                }
            }
        }
        super.getGameManager().printTable();
    }

    @Override
    protected void returnResults() {
        System.out.println(super.getGameManager().analyze());
    }
}
