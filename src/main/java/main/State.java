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
    GAMENOTFINISHED("Game not finished", false),
    DRAW("Draw", true),
    XWINS("X wins", true),
    OWINS("O wins", true),
    IMPOSSIBLE("Impossible", true);
    
    private String str;
    private boolean isFinal;
    
    State(String str, boolean isFinal){
        this.str = str;
        this.isFinal = isFinal;
    }
    
    public String getString(){
        return str;
    }
    
    public boolean isFinal(){
        return isFinal;
    }
}
