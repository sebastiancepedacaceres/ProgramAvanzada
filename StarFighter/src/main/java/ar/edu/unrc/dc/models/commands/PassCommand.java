package ar.edu.unrc.dc.models.commands;

import ar.edu.unrc.dc.models.GameState;

public class PassCommand implements Command {
    
    @Override
    public GameState execute(GameState gameState) {
        return gameState;
    }
}