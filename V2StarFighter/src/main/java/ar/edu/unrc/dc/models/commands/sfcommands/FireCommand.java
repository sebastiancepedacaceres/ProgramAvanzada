package ar.edu.unrc.dc.models.commands.sfcommands;

import ar.edu.unrc.dc.entities.character.Starfighter;
import ar.edu.unrc.dc.models.Board;
import ar.edu.unrc.dc.models.GameState;
import ar.edu.unrc.dc.models.commands.Command;
/**
 * Command Pattern
 * Command to fire a projectile from the starfighter.
 */
public class FireCommand implements Command {

    /**
     * returns a new GameState with a new projectile added in front of the starfighter,
     * if the starfighter is not at the right edge of the board.
     * If the starfighter is at the right edge, the game state remains unchanged.
     */
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
