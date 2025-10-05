package ar.edu.unrc.dc.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import ar.edu.unrc.dc.models.entities.Projectile;
import ar.edu.unrc.dc.models.entities.Starfighter;

public class GameEngineTest {

    @Test
    void testGameInitializationPlacesStarfighterInMiddle() {
        StarfighterGameEngine engine = new StarfighterGameEngine();
        engine.play(5, 7, 10, 2);
        GameState state = engine.getCurrentGame();
        Starfighter sf = state.getStarfighter();
        // Fila central (ceil(5/2) = 3)-1, columna 0
        assertEquals(2, sf.getRow());
        assertEquals(0, sf.getColumn());
        assertFalse(state.isGameOver());
    }

    @Test
    void testGameInitializationPlacesStarfighterInMiddleRowPar() {
        StarfighterGameEngine engine = new StarfighterGameEngine();
        engine.play(6, 7, 10, 2);
        GameState state = engine.getCurrentGame();
        Starfighter sf = state.getStarfighter();
        // Fila central (ceil(5/2) = 3)-1, columna 0
        assertEquals(2, sf.getRow());
        assertEquals(0, sf.getColumn());
        assertFalse(state.isGameOver());
    }

    @Test
    void testCannotStartGameTwice() {
        StarfighterGameEngine engine = new StarfighterGameEngine();
        engine.play(5, 7, 10, 2);
        assertThrows(IllegalStateException.class, () -> engine.play(5, 7, 10, 2));
    }

    @Test
    void testFireAddsProjectile() {
        StarfighterGameEngine engine = new StarfighterGameEngine();
        engine.play(5, 7, 10, 2);
        engine.fire();
        GameState state = engine.getCurrentGame();
        // El proyectil debe estar a la derecha del Starfighter
        assertEquals(1, state.getProjectiles().size());
        Projectile p = (Projectile) state.getProjectiles().get(0);
        assertEquals(2, p.getRow());
        assertEquals(1, p.getColumn());
    }

    @Test
    void testFireOnRightEdgeDoesNotAddProjectile() {
        StarfighterGameEngine engine = new StarfighterGameEngine();
        engine.play(5, 5, 10, 2);
        // Mover Starfighter a la última columna
        engine.move(0, 4);
        engine.fire();
        GameState state = engine.getCurrentGame();
        assertEquals(0, state.getProjectiles().size());
    }

    @Test
    void testProjectileMovesAndCollidesWithStarfighter() {
        StarfighterGameEngine engine = new StarfighterGameEngine();
        engine.play(5, 7, 10, 2);
        // Disparar proyectil
        engine.fire();
        // Mover Starfighter a la derecha, a la posición donde irá el proyectil
        engine.move(0, 1);
        GameState state = engine.getCurrentGame();
        assertTrue(state.isGameOver());
        assertTrue(state.checkColision());
    }

    @Test
    void testMoveStarfighterWithCollisionOnPath() {
        StarfighterGameEngine engine = new StarfighterGameEngine();
        engine.play(5, 7, 10, 2);
        // Disparar proyectil
        engine.fire();
        // Mover Starfighter a la derecha, pasando por la celda donde estará el proyectil
        engine.move(0, 1);
        // El Starfighter debe estar destruido si colisiona en el camino
        GameState state = engine.getCurrentGame();
        assertTrue(state.getStarfighter().isDestroyed() || state.isGameOver());
    }

    @Test
    void testAbortEndsGame() {
        StarfighterGameEngine engine = new StarfighterGameEngine();
        engine.play(5, 7, 10, 2);
        engine.abort();
        GameState state = engine.getCurrentGame();
        assertTrue(state.isGameOver());
    }

    @Test
    void testMoveExceedsM1ThrowsException() {
        StarfighterGameEngine engine = new StarfighterGameEngine();
        engine.play(5, 7, 3, 2); // m1 = 3
        // Intentar mover más de 3 unidades
        assertThrows(IllegalArgumentException.class, () -> engine.move(2, 2));
    }

    @Test
    void testProjectileLeavesBoard() {
        StarfighterGameEngine engine = new StarfighterGameEngine();
        engine.play(5, 5, 10, 2);
        engine.fire();
        // Avanzar proyectil fuera del tablero
        engine.ProjectilesAdvance();
        engine.ProjectilesAdvance();
        engine.ProjectilesAdvance();
        GameState state = engine.getCurrentGame();
        assertEquals(0, state.getProjectiles().size());
    }


}
