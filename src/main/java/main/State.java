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
public enum State {
    GAMENOTFINISHED("Game not finished"),
    DRAW("Draw"),
    XWINS("X wins"),
    OWINS("O wins"),
    IMPOSSIBLE("Impossible");
    
    private String str;
    
    State(String str){
        this.str = str;
    }
    
    public String getString(){
        return str;
    }
}
