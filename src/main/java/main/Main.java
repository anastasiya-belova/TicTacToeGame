/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import gamelogic.State;
import gamelogic.Cell;
import gamelogic.GameManager;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 *
 * @author Anastasia Belova
 */
public class Main {
    
    private static final String NINEWHITESPACES = "         ";
    
    public static void main(String[] args){
        start();
    }
    
    private static void start(){
        GameManager gameManager = GameManager.getInstance();
        gameManager.setLength(3);
        gameManager.createPlayingField(NINEWHITESPACES, 3, 3);
        gameManager.createGame().startGame();
        String result = gameManager.analyze().getString();
        System.out.println(result);
    }
 
    /**
     * Returns two int numbers belonging to the interval from lowerLimit to upperLimit.
     * Numbers are read from System.in with the use java.util.Scanner.
     * @param lowerLimit
     * @param upperLimit
     * @return two int numbers belonging to the interval from lowerLimit to upperLimit
     */
    public static int[] readTwoNumbersFromString(int lowerLimit, int upperLimit) {
        Scanner sc = new Scanner(System.in);
        int[] numbers = new int[2];
        boolean validSecond = false;
        boolean validFirst = false;
        while (!validSecond && !validFirst) {
            try {
                System.out.print("Enter the coordinates: ");
                numbers[0] = sc.nextInt();
                validFirst = true;
                numbers[1] = sc.nextInt();
                validSecond = true;
                if (numbers[0] < lowerLimit || numbers[0] > upperLimit
                        || numbers[1] < lowerLimit || numbers[1] > upperLimit) {
                    validFirst = false;
                    validSecond = false;
                    System.out.println("Coordinates should be from 1 to 3!");
                }
            } catch (NoSuchElementException e) {
                System.out.println("You should enter numbers!");
                validFirst = false;
                validSecond = false;
                sc.nextLine();
            }
        }
        return numbers;
    }

    /**
     * Prints the playing field by System.out.
     * @param table 
     */
    public static void printTable(Cell[][] table){
        System.out.println("---------");
        for (Cell[] row : table){
            System.out.print("| ");
            for (Cell column : row){
                System.out.print(column.getChar());
                System.out.print(" ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }
}
