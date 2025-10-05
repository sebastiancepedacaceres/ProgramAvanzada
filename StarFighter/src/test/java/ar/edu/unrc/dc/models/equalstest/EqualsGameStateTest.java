package ar.edu.unrc.dc.models.equalstest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import ar.edu.unrc.dc.models.Board;
import ar.edu.unrc.dc.models.GameState;
import ar.edu.unrc.dc.models.ProjectileMovement;
import ar.edu.unrc.dc.models.entities.Entity;
import ar.edu.unrc.dc.models.entities.Projectile;
import ar.edu.unrc.dc.models.entities.Starfighter;

public class EqualsGameStateTest {

    @Test
    void equalsEmptyStatesTest() {
        GameState gameState1 = new GameState();
        GameState gameState2 = new GameState();

        boolean result = gameState1.equals(gameState2);

        assertEquals(true, result);
    }

    @Test
    void DifferentStarfightersTest() {
        Starfighter starfighter1 = new Starfighter(3, 3);
        Starfighter starfighter2 = new Starfighter(3, 4);
        GameState gameState1 = new GameState();
        gameState1.setStarfighter(starfighter1);
        GameState gameState2 = new GameState();
        gameState2.setStarfighter(starfighter2);

        boolean result = gameState1.equals(gameState2);

        assertEquals(false, result);
    }

    @Test
    void differentGameOver() {
        Starfighter starfighter1 = new Starfighter(3, 3);
        Starfighter starfighter2 = new Starfighter(3, 3);
        GameState gameState1 = new GameState();
        gameState1.setGameOver(true);
        gameState1.setStarfighter(starfighter1);
        GameState gameState2 = new GameState();
        gameState2.setGameOver(false);
        gameState2.setStarfighter(starfighter2);

        boolean result = gameState1.equals(gameState2);

        assertFalse(result);
    }

    @Test
    void differentProjectilesTest() {
        //ARRANGE
        //la ubicación de los proyectiles es diferente
        Projectile projectile1 = new Projectile(1, 2);
        Projectile projectile2 = new Projectile(1, 3);

        Starfighter starfighter1 = new Starfighter(3, 3);
        Starfighter starfighter2 = new Starfighter(3, 3);

        ArrayList<Entity> projectiles = new ArrayList();
        projectiles.add(projectile1);

        ArrayList<Entity> projectilesGameState2 = new ArrayList();
        projectilesGameState2.add(projectile2);

        GameState gameState1 = new GameState(starfighter1,new Board(4,4),projectiles, false, new ArrayList<>());
        GameState gameState2 = new GameState(starfighter1,new Board(4,4),projectilesGameState2, false, new ArrayList<>());
        
        
        boolean result = gameState1.equals(gameState2);

        assertEquals(false, result);
    }

     @Test
    void differentMultipleProjectilesTest() {
        //ARRANGE
        //la ubicación de los proyectiles es diferente
        Projectile projectile1 = new Projectile(1, 2);
        Projectile projectile2 = new Projectile(1, 3);
        Projectile projectile3 = new Projectile(1, 2);
        Projectile projectile4 = new Projectile(1, 3);

        Starfighter starfighter1 = new Starfighter(3, 3);
        Starfighter starfighter2 = new Starfighter(3, 3);

        ArrayList<Entity> projectiles = new ArrayList<>();
        projectiles.add(projectile1);
        projectiles.add(projectile2);

        ArrayList<Entity> projectilesGameState2 = new ArrayList<>();
        projectilesGameState2.add(projectile4);
        projectilesGameState2.add(projectile3);

        GameState gameState1 = new GameState(starfighter1,new Board(4,4),projectiles, false,new ArrayList<>());
        GameState gameState2 = new GameState(starfighter1,new Board(4,4),projectilesGameState2, false,new ArrayList<>());
        
        
        boolean result = gameState1.equals(gameState2);

        assertEquals(false, result);
    }
    
    @Test
    void differentBoardTest() {
        //ARRANGE
        //la ubicación de los proyectiles es diferente
        Projectile projectile = new Projectile(1, 2);
        Starfighter starfighter = new Starfighter(3, 3);
        ArrayList<Entity> projectiles = new ArrayList<>();
        projectiles.add(projectile);

        //Different Boards Arrange 
        Board board1 = new Board(4, 4);
        board1.placeEntity(2,2,'*');
        Board board2 = new Board(4, 4);
        board2.placeEntity(2,1,'*');

        GameState gameState1 = new GameState(starfighter,board1,projectiles, false, new ArrayList<>());
        GameState gameState2 = new GameState(starfighter,board2,projectiles,false,new ArrayList<>());
        
        
        boolean result = gameState1.equals(gameState2);

        assertEquals(false, result);
    }


     @Test
    void equalsMultipleProjectilesTest() {
        //ARRANGE
        //la ubicación de los proyectiles es diferente
        Projectile projectile1 = new Projectile(1, 2);
        Projectile projectile2 = new Projectile(1, 2);
        Projectile projectile3 = new Projectile(1, 2);
        Projectile projectile4 = new Projectile(1, 2);

        Starfighter starfighter1 = new Starfighter(3, 3);
        Starfighter starfighter2 = new Starfighter(3, 3);

        ArrayList<Entity> projectiles = new ArrayList<>();
        projectiles.add(projectile1);

        ArrayList<Entity> projectilesGameState2 = new ArrayList<>();
        projectilesGameState2.add(projectile2);

        GameState gameState1 = new GameState(starfighter1,new Board(4,4),projectiles, false, new ArrayList<>());
        GameState gameState2 = new GameState(starfighter1,new Board(4,4),projectilesGameState2, false, new ArrayList<>());
        
        
        boolean result = gameState1.equals(gameState2);

        assertEquals(true, result);
    }
    
}

