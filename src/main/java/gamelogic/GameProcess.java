/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamelogic;

import static main.Main.printTable;
import static main.Main.readTwoNumbersFromString;

/**
 *
 * @author Anastasiya Belova
 */
public class GameProcess {
    
    private final GameManager gameManager;
    //private final Player xPlayer;
    //private final Player yPlayer;
    
    GameProcess(GameManager gameManager){
        this.gameManager = gameManager;
    }
    
    public void startGame() {
        boolean isXMove = true;
        printTable(gameManager.getField());
        while (!gameManager.analyze().isFinal()) {
            boolean isSetted = false;
            while (!isSetted) {
                int[] coordinates = readTwoNumbersFromString(1, 3);
                int x = coordinates[0];
                int y = coordinates[1];
                if (isXMove) {
                    if (gameManager.setElementToField(Cell.CROSS, x, y)) {
                        isSetted = true;
                        isXMove = false;
                    }
                } else {
                    if (gameManager.setElementToField(Cell.ZERO, x, y)) {
                        isSetted = true;
                        isXMove = true;
                    }
                }
            }
        printTable(gameManager.getField());
        }
    }
}
