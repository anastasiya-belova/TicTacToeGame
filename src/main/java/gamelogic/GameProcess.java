/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamelogic;

import IO.InputManager;

/**
 *
 * @author Anastasiya Belova
 */
public class GameProcess {
    
    private final GameManager gameManager;
    
    GameProcess(GameManager gameManager){
        this.gameManager = gameManager;
    }
    
    public void startGame() {
        boolean isXMove = true;
        gameManager.printTable();
        while (!gameManager.analyze().isFinal()) {
            boolean isSetted = false;
            while (!isSetted) {
                int[] coordinates = InputManager.readTwoNumbersFromString(1, 3);
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
        gameManager.printTable();
        }
    }
}
