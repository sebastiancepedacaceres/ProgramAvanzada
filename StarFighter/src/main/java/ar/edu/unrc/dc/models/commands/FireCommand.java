package ar.edu.unrc.dc.models.commands;

import ar.edu.unrc.dc.models.Board;
import ar.edu.unrc.dc.models.GameState;
import ar.edu.unrc.dc.models.entities.Entity;
import ar.edu.unrc.dc.models.entities.ProjectileFactory;
import ar.edu.unrc.dc.models.entities.Starfighter;

public class FireCommand implements Command {

    @Override
    public GameState execute(GameState gameState) {
        GameState newGameState = gameState.clone();
        Starfighter sf = newGameState.getStarfighter();
        ProjectileFactory projectileFactory = new ProjectileFactory();

        int row = sf.getRow();
        int column = sf.getColumn();
        int rightEdge = newGameState.getBoard().getColumns() - 1;

        if (column == rightEdge) {
            return newGameState;
        }

        int projectileCol = column + 1;
        Entity projectile =  projectileFactory.createProjectile(row, projectileCol);

        newGameState.getBoard().placeEntity(row, projectileCol, Board.PROJECTILE);
        newGameState.addProjectile(projectile);

        return newGameState;
    }
}
