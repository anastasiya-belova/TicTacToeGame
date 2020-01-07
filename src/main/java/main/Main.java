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
        printTable(table);
        System.out.println(analyze(table).getString());
    }

    public static State analyze(Cell[][] table) {
        //проверка возможности
        int countCross = 0;
        int countZero = 0;
        int countEmpty = 0;
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
        boolean xWins = false;
        boolean oWins = false;
        //проверка по строкам
        for (Cell[] row : table) {
            if (!Cell.EMPTY.equals(row[0])) {
                if (row[0].equals(row[1]) && row[1].equals(row[2])) {
                    if (Cell.CROSS.equals(row[0])) {
                        xWins = true;
                    } else {
                        oWins = true;
                    }
                }
            }
        }
        //проверка по столбцам
        for (int column = 0; column < 3; column++) {
            //for (Cell[] row : table) {
            if (!Cell.EMPTY.equals(table[0][column])) {
                if (table[0][column].equals(table[1][column]) && table[1][column].equals(table[2][column])) {
                    if (Cell.CROSS.equals(table[0][column])) {
                        xWins = true;
                    } else {
                        oWins = true;
                    }
                }
            }
        }
        //проверка по диагоналям
        if (!Cell.EMPTY.equals(table[0][0])) {
            if (table[0][0].equals(table[1][1]) && table[2][2].equals(table[1][1])) {
                if (Cell.CROSS.equals(table[0][0])) {
                    xWins = true;
                } else {
                    oWins = true;
                }
            }
        }
        if (!Cell.EMPTY.equals(table[0][2])) { //0;2 угол? переписать бы алгоритм
            if (table[0][2].equals(table[1][1]) && table[2][0].equals(table[1][1])) {
                if (Cell.CROSS.equals(table[0][2])) {
                    xWins = true;
                } else {
                    oWins = true;
                }
            }
        }
        if(xWins && oWins){
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
