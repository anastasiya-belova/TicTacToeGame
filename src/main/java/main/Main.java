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
        int WIDTH = 3;
        int HEIGHT = 3;
        Scanner sc = new Scanner(System.in);
        char[] inputSymbols = sc.next().toCharArray();
        Cell[][] table = new Cell[WIDTH][HEIGHT];
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
        printTable(table);
        System.out.println(analyze(table, 3).getString());
    }

    public static State analyze(Cell[][] table, int len) {
        //переменная LENGTH задаёт длину выигрышной комбинации
        int LENGTH = len;
        int countCross = 0;
        int countZero = 0;
        int countEmpty = 0;
        boolean xWins = false;
        boolean oWins = false;
        //проверка возможности
        for (Cell[] row : table) {
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
        if (Math.abs(countZero-countCross) > 1){
            return State.IMPOSSIBLE;
        }
        //проверка по строкам
        for (Cell[] row : table) {
            for (int element = 0; element < row.length - LENGTH + 1; element++) {
                if (!Cell.EMPTY.equals(row[element])) {
                    boolean isSame = true;
                    for (int count = 1; count < LENGTH; count++) {
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
        //проверка по столбцам
        for (int element = 0; element < 3; element++) {//!!!!
            for (int row = 0; row < table.length - LENGTH + 1; row++) {
                if (!Cell.EMPTY.equals(table[row][element])) {
                    boolean isSame = true;
                    for (int count = 1; count < LENGTH; count++) {
                        isSame = isSame && table[row][element].equals(table[row+count][element]);
                    }
                    if (isSame && Cell.CROSS.equals(table[row][element])){
                        xWins = true;
                    }
                    else if (isSame && Cell.ZERO.equals(table[row][element])){
                        oWins = true;
                    }
                }
            }
        }
        //проверка по диагоналям
        for (int row = 0; row < table.length - LENGTH + 1; row++) {
            for (int element = 0; element < table[row].length - LENGTH; element++) {
                if (!Cell.EMPTY.equals(table[row][element])) {
                    boolean isSame = true;
                    for (int count = 1; count < LENGTH; count++) {
                        isSame = isSame && table[row][element].equals(table[row + count][element + count]);
                    }
                    if (isSame && Cell.CROSS.equals(table[row][element])) {
                        xWins = true;
                    } else if (isSame && Cell.ZERO.equals(table[row][element])) {
                        oWins = true;
                    }
                }
            }
        }
        for (int row = 0; row < table.length - LENGTH + 1; row++) {
            for (int element = table[row].length - 1; element > LENGTH - 2; element--) {
                if (!Cell.EMPTY.equals(table[row][element])) {
                    boolean isSame = true;
                    for (int count = 1; count < LENGTH; count++) {
                        isSame = isSame && table[row][element].equals(table[row + count][element - count]);
                    }
                    if (isSame && Cell.CROSS.equals(table[row][element])) {
                        xWins = true;
                    } else if (isSame && Cell.ZERO.equals(table[row][element])) {
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
