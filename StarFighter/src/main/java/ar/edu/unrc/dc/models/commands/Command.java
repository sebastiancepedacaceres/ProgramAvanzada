package ar.edu.unrc.dc.models.commands;

import ar.edu.unrc.dc.models.GameState;

public interface Command {
    GameState execute(GameState gameState);
}
