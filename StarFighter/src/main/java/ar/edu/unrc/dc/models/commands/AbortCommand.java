package ar.edu.unrc.dc.models.commands;

import ar.edu.unrc.dc.models.GameState;

public class AbortCommand implements Command {

    @Override
    public GameState execute(GameState gameState) {
        GameState newGameState = gameState.clone();
        if(!newGameState.isGameOver()){
            newGameState.setGameOver(true);
        }
        return newGameState;
    }
}