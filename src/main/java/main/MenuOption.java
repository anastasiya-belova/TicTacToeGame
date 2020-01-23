package main;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Anastasiya Belova
 */
enum MenuOption {
    WELCOME_PAGE(1, "Welcome page"),
    GAME_PAGE(2, "Game page"),
    HISTORY_PAGE(3, "History page"),
    CLOSE(4, "Close");
    
    private final int number;
    private final String description;
    
    MenuOption(int number, String description){
        this.number = number;
        this.description = description;
    }
    
    int getNumber(){
        return this.number;
    }
    
    String getDescription(){
        return this.description;
    }
}
