/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.Scanner;

/**
 *
 * @author Kraz
 */
public class Main {
    
    
    
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        char[] inputSymbols = sc.next().toCharArray();
        Cell[][] table = new Cell[3][3];
        int numOfChar = 0;
        for (Cell[] row : table) {
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
        
    }
    
    public static State analyze(Cell[][] table){
        
    }
}
