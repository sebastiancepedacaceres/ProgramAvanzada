package ar.edu.unrc.dc.models.commandstest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import ar.edu.unrc.dc.models.GameState;
import ar.edu.unrc.dc.models.ProjectileMovement;
import ar.edu.unrc.dc.models.StarfighterGameEngine;
import ar.edu.unrc.dc.models.commands.DisplaceProjectilesCommand;
import ar.edu.unrc.dc.models.entities.Projectile;

public class DisplaceCommandTest {
    @Test
    void displaceProjectilesCommandTest() {
        // Arrange
        int row = 10;
        int col = 10;
        GameState game = new GameState(row, col);

        Projectile p1 = new Projectile(6, 6);
        Projectile p2 = new Projectile(3, 3);

        game.addProjectile(p1);
        game.addProjectile(p2);
        game.getBoard().placeEntity(6, 6, '*');
        game.getBoard().placeEntity(3, 3, '*');

        int steps = 3;
        DisplaceProjectilesCommand displaceCommand = new DisplaceProjectilesCommand(steps);

        // expected state
        GameState gameExpected = new GameState(row, col);
        Projectile p3 = new Projectile(6, 6 + steps);
        Projectile p4 = new Projectile(3, 3 + steps);
        gameExpected.addProjectile(p3);
        gameExpected.addProjectile(p4);
        gameExpected.getBoard().placeEntity(6, 6 + steps, '*');
        gameExpected.getBoard().placeEntity(3, 3 + steps, '*');

        ArrayList<ProjectileMovement> movements = new ArrayList<>();
        ProjectileMovement movement1 = new ProjectileMovement(6, 6, 6, 6 + steps, false);
        ProjectileMovement movement2 = new ProjectileMovement(3, 3, 3, 3 + steps, false);
        movements.add(movement1);
        movements.add(movement2);
        gameExpected.setProjectileMovements(movements);

        displaceCommand.displaceProjectiles(game, steps);

        // Assert
        assertEquals(true, game.equals(gameExpected));
    }

    @Test
    void displaceProjectilesStepsOverflowTest() {
        // Arrange
        int row = 10;
        int col = 10;
        GameState game = new GameState(row, col);

        Projectile p1 = new Projectile(6, 6);
        Projectile p2 = new Projectile(3, 3);

        game.addProjectile(p1);
        game.addProjectile(p2);

        int steps = 8;
        DisplaceProjectilesCommand displaceCommand = new DisplaceProjectilesCommand(steps);

        // Assert
        assertThrows(IllegalArgumentException.class, () -> {
            displaceCommand.execute(game, steps);
        });
    }

    @Test
    void displaceProjectiles5InitializaInGameEngineTest() {
        // Arrange
        StarfighterGameEngine engine = new StarfighterGameEngine();
        engine.play(10, 10, 5, 5);
        GameState game = engine.getCurrentGame();
        GameState gameExpected = engine.getCurrentGame();
        Projectile p1 = new Projectile(1, 3);
        Projectile p2 = new Projectile(1, 8);
        game.addProjectile(p1);
        game.getBoard().placeEntity(1, 3, '*');

        gameExpected.addProjectile(p2);
        gameExpected.getBoard().placeEntity(1, 8, '*');

        // Act
        engine.ProjectilesAdvance();

        // Assert
        assertEquals(gameExpected, game);

    }

    @Test
    void displaceProjectilesCommandOrderTest() {
        // Arrange
        int row = 8;
        int col = 8;
        GameState game = new GameState(row, col);

        Projectile p1 = new Projectile(2, 2);
        Projectile p2 = new Projectile(4, 4);
        Projectile p3 = new Projectile(6, 6);

        game.addProjectile(p1);
        game.addProjectile(p2);
        game.addProjectile(p3);

        game.getBoard().placeEntity(2, 2, '*');
        game.getBoard().placeEntity(4, 4, '*');
        game.getBoard().placeEntity(6, 6, '*');

        int steps = 2;
        DisplaceProjectilesCommand displaceCommand = new DisplaceProjectilesCommand(steps);

        // Expected state
        GameState expected = new GameState(row, col);
        Projectile ep1 = new Projectile(2, 4);
        Projectile ep2 = new Projectile(4, 6);
        Projectile ep3 = new Projectile(6, 8);

        expected.addProjectile(ep1);
        expected.addProjectile(ep2);
        expected.addProjectile(ep3);

        expected.getBoard().placeEntity(2, 4, '*');
        expected.getBoard().placeEntity(4, 6, '*');
        // (6,8) is out of bounds, so only two should remain if logic removes
        // out-of-bounds

        // Act
        displaceCommand.displaceProjectiles(game, steps);

        // Assert: check projectiles list size and order
        assertEquals(expected.getProjectiles().size(), game.getProjectiles().size());
        for (int i = 0; i < expected.getProjectiles().size(); i++) {
            assertEquals(expected.getProjectiles().get(i), game.getProjectiles().get(i));
        }
    }

    @Test
    void displaceProjectilesCommandEmptyListTest() {
        // Arrange
        int row = 5;
        int col = 5;
        GameState game = new GameState(row, col);

        int steps = 1;
        DisplaceProjectilesCommand displaceCommand = new DisplaceProjectilesCommand(steps);

        // Act
        displaceCommand.displaceProjectiles(game, steps);

        // Assert:
        assertEquals(0, game.getProjectiles().size());
    }

}
