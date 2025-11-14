package ar.edu.unrc.dc.models.commands.sfcommands;

import ar.edu.unrc.dc.models.GameState;
import ar.edu.unrc.dc.models.commands.Command;

/**
 * Command Pattern
 * Command to pass the turn without making any changes to the game state.
 */

public class PassCommand implements Command {
    /**
     * returns the same GameState without any modifications.
     */
    
    @Override
    public GameState execute(GameState gameState) {
        return gameState;
    }
}