/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamelogic;

/**
 * Represents all possible states of the playing field.
 * @author Anastasia Belova
 */
public enum State {
    GAMENOTFINISHED("Game not finished", false),
    DRAW("Draw", true),
    XWINS("X wins", true),
    OWINS("O wins", true),
    IMPOSSIBLE("Impossible", true);
    
    private final String str;
    private final boolean isFinal;
    
    State(String str, boolean isFinal){
        this.str = str;
        this.isFinal = isFinal;
    }
    
    /**
     * Returns a string description of the state of the playing field.
     * @return a string description.
     */
    public String getString(){
        return str;
    }
    
    /**
     * Returns information about whether this state of the playing field is final.
     * @return true if this state is final, false otherwise.
     */
    public boolean isFinal(){
        return isFinal;
    }
}
