/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

/**
 * Represents all possible elements of the playing field.
 * @author Anastasia Belova
 */
public enum Cell {
    CROSS('X'),
    ZERO('O'),
    EMPTY(' ');
    
    private char ch;
    
    Cell(char ch){
        this.ch = ch;
    }
    
    public char getChar(){
        return ch;
    }
}
