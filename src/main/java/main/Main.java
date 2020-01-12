/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 *
 * @author Kraz
 */
public class Main {
    
    public static void main(String[] args){
        int WIDTH = 3;
        int HEIGHT = 3;
        //построение таблицы по заданным значениям
        System.out.print("Enter cells: ");
        Cell[][] table;
        Scanner sc = new Scanner(System.in);
        char[] inputSymbols = sc.next().toCharArray();
        table = new Cell[WIDTH][HEIGHT];
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
        //System.out.println(analyze(table, 3).getString());
        //добавление символа в таблицу
        boolean isSetted = false;
        while (!isSetted) {
            int[] coordinates = readTwoNumbersFromString(1, 3);
            int x = coordinates[0];
            int y = coordinates[1];
            if(setElementToTable(table, Cell.CROSS, x, y) != null){
                isSetted = true;
            }
        }
        printTable(table);
    }

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

    public static Cell[][] setElementToTable(Cell[][] table, Cell element, int xCoordinate, int yCoordinate) {
        int row = table.length - yCoordinate;
        int column = xCoordinate - 1;
        if (!Cell.EMPTY.equals(table[row][column])) {
            System.out.println("This cell is occupied! Choose another one!");
            return null;
            //спросить координаты ещё раз
            //мб выделить считывание координат в отдельный метод?
        }
        else{
            table[row][column] = element;
        }
        return table;
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
        for (int element = 0; element < table[0].length; element++) {//тут не очень
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
