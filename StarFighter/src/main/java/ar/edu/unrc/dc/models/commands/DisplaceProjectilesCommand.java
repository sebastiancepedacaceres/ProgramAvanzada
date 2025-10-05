package ar.edu.unrc.dc.models.commands;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unrc.dc.models.Board;
import ar.edu.unrc.dc.models.GameState;
import ar.edu.unrc.dc.models.ProjectileMovement;
import ar.edu.unrc.dc.models.entities.Entity;

public class DisplaceProjectilesCommand implements Command {
    private final int advanceSteps;

    public DisplaceProjectilesCommand(int advanceSteps) {
        this.advanceSteps = advanceSteps;
    }

    @Override
    public GameState execute(GameState gameState) {
        if (gameState == null || gameState.isGameOver()) {
            throw new IllegalStateException("No active game");
        }
        if(advanceSteps < 1 || advanceSteps > 5){
            throw new IllegalArgumentException("advanceSteps must be greater than 0");
        }
        GameState newGameState = gameState.clone();
        displaceProjectiles(newGameState, advanceSteps);
        return newGameState;
    }

    public GameState execute(GameState gameState, int advanceSteps){
        if (gameState == null || gameState.isGameOver()) {
            throw new IllegalStateException("No active game");
        }
        if(advanceSteps < 1 || advanceSteps > 5){
            throw new IllegalArgumentException("advanceSteps must be greater than 0");
        }
        GameState newGameState = gameState.clone();
        displaceProjectiles(newGameState, advanceSteps);
        return newGameState;
    }

    public void displaceProjectiles(GameState game, int steps) {
     
        Board board = game.getBoard();
        int boardLimit = board.getColumns() - 1;
        List<Entity> projectiles = game.getProjectiles();
        List<Entity> projectilesToRemove = new ArrayList<>();
        List<ProjectileMovement> projectileMovements = game.getProjectileMovements();

        projectileMovements.clear();

        for(Entity p : projectiles){
            int initialRow = p.getRow();
            int initialColumn = p.getColumn();
            int finalColumn = initialColumn + steps;

            if(board.isWithinBounds(initialRow, initialColumn) && 
               board.getCellContent(initialRow, initialColumn) == Board.PROJECTILE) {
                board.clearCell(initialRow, initialColumn);
            }

            int actualColumn = initialColumn;
            boolean collisionOccurred = false;

            while(actualColumn < finalColumn){  
                actualColumn++;
                if(board.isWithinBounds(initialRow, actualColumn) && 
                   board.getCellContent(initialRow, actualColumn) == Board.STARFIGHTER){
                    game.setGameOver(initialRow, actualColumn);
                    collisionOccurred = true;
                    projectilesToRemove.add(p);
                    projectileMovements.add(new ProjectileMovement(initialRow, initialColumn, initialRow, actualColumn, true));
                    break;
                }
            } 
            if(!collisionOccurred) {
                p.move(initialRow, actualColumn);
                
                if(actualColumn > boardLimit){
                    projectilesToRemove.add(p);
                } else {
                    board.placeEntity(initialRow, actualColumn, Board.PROJECTILE);
                    projectileMovements.add(new ProjectileMovement(initialRow, initialColumn, initialRow, actualColumn, false));
                }
            }
        }
        projectiles.removeAll(projectilesToRemove);
    }
}
