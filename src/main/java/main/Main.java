/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import IO.InputManager;
import gamelogic.GameManager;

/**
 *
 * @author Anastasia Belova
 */
public class Main {

    private static final String NINE_WHITE_SPACES = "         ";

    private static final char[] VALID_CHARACTERS_OF_MENU = {'1', '2', '3', '4'};

    public static void main(String[] args) {
        startLoop();
    }

    private static void startLoop() {
        MenuOption menu = MenuOption.WELCOME_PAGE;
        //i - scam variable
        int i = 0;
        while (i == 0) {
            switch (menu) {
                case WELCOME_PAGE:
                    menu = welcome();
                    break;
                case GAME_PAGE:
                    menu = game();
                    break;
                case HISTORY_PAGE:
                    menu = history();
                    break;
                case CLOSE:
                    System.exit(0);
                    break;
            }
        }
    }

    private static MenuOption welcome() {
        System.out.println("Welcome!");
        return switchMenu();
    }

    private static MenuOption game() {
        GameManager gameManager = GameManager.getInstance();
        gameManager.setLengthOfWinComb(3);
        gameManager.setStartCondition(NINE_WHITE_SPACES);
        gameManager.setFieldHeight(3);
        gameManager.setFieldWidth(3);
        gameManager.createGame().game();
        return switchMenu();
    }

    private static MenuOption history() {
        System.out.println("Soon will be a history here =)");
        return switchMenu();
    }

    private static MenuOption switchMenu() {
        System.out.println("What do you want? Enter \"1\" to go to the start page, enter \"2\" to start the game,"
                + "enter \"3\" to view the game history, enter \"4\" to exit.");
        char input = InputManager.readOneChar(VALID_CHARACTERS_OF_MENU);
        switch (input) {
            case '1':
                return MenuOption.WELCOME_PAGE;
            case '2':
                return MenuOption.GAME_PAGE;
            case '3':
                return MenuOption.HISTORY_PAGE;
            case '4':
                return MenuOption.CLOSE;
            default:
                return null;
        }
    }
}
