/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IO;

import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 *
 * @author Anastasiya Belova
 */
public class InputManager {
    
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
     * Gives a set of valid characters. Reads a character from System.in with the use java.util.Scanner.
     * If the read character matches th specified field it returns it. If not it reads the character again.
     * @param validCharacters
     * @return a character that belongs to a given set
     */
    public static char readOneChar(char[] validCharacters){
        Scanner sc = new Scanner(System.in);
        char readedCharacter = 0;
        boolean isValid = false;
        while (!isValid) {
            String str = sc.next();
            if (str.length() == 1) {
                readedCharacter = str.charAt(0);
            } else {
                System.out.println("Not valid string. Try again.");
                continue;
            }
            for (char next : validCharacters) {
                if (next == readedCharacter) {
                    isValid = true;
                    break;
                }
            }
            if (!isValid){
                System.out.println("Not valid character. Try again.");
            }
        }
        return readedCharacter;
    }
}
