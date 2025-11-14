package ar.edu.unrc.dc.models.commands;



import ar.edu.unrc.dc.models.GameState;


/**
 * Command Pattern
 * Command interface for executing game phases.
 */

public interface Command {
    GameState execute(GameState gameState);
}

