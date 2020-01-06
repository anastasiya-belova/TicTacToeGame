/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

/**
 *
 * @author Kraz
 */
public enum Cell {
    CROSS('X'),
    ZERO('O'),
    EMPTY('_');
    
    private char ch;
    
    Cell(char ch){
        this.ch = ch;
    }
    
    public char getChar(){
        return ch;
    }
}
