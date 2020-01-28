/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamelogic;

/**
 * This class provides a template of the gameplay.
 *
 * @author Anastasiya Belova
 */
public abstract class AbstractGameProcess {

    private final GameManager gameManager;

    AbstractGameProcess(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    public GameManager getGameManager() {
        return this.gameManager;
    }

    public void game() {
        setPlayers();
        createField();
        while (!gameManager.isEndingCondition()) {
            iterateGame();
        }
        returnResults();
    }

    protected abstract void setPlayers();

    protected abstract void createField();

    protected abstract void iterateGame();

    protected abstract void returnResults();
}
