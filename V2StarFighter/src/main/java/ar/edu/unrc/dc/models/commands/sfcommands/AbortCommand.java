package ar.edu.unrc.dc.models.commands.sfcommands;
import ar.edu.unrc.dc.models.GameState;
import ar.edu.unrc.dc.models.commands.Command;
/**
 * Command Pattern
 * Command to abort the current game.
 */

public class AbortCommand implements Command {

    /**
     * precondition: gameState is not null and the game is not already over.
     * postcondition: returns a new GameState with the game marked as over.
     */

    @Override
    public GameState execute(GameState gameState) {
        if (gameState == null) {
        throw new IllegalStateException("No active game: cannot execute the Abort command.");
        }
        GameState newGameState = gameState.clone();
        if(!newGameState.isGameOver()){
            newGameState.setGameOver();
        }
        return newGameState;
    }
}