package ar.edu.unrc.dc.models;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import ar.edu.unrc.dc.models.entities.Entity;
import ar.edu.unrc.dc.models.entities.Projectile;
import ar.edu.unrc.dc.models.entities.Starfighter;

public class GameStateTest {

    @Test
    public void cloneGameState() {
        //Arrange
        Projectile projectile1 = new Projectile(1, 1);
        Projectile projectile2 = new Projectile(1, 2);
        Projectile projectile3 = new Projectile(1, 5);
        Projectile projectile4 = new Projectile(1, 3);

        Starfighter starfighter1 = new Starfighter(3, 3);

        ArrayList<Entity> projectiles = new ArrayList<>();
        projectiles.add(projectile1);
        projectiles.add(projectile2);
        projectiles.add(projectile3);
        projectiles.add(projectile4);

        GameState gameState1 = new GameState(starfighter1, new Board(9, 9), projectiles, false, new ArrayList<>());

        //Act
        GameState result = gameState1.clone();

        //Assert 

        assertEquals(gameState1, result);

    }

    @Test
    public void checkColisionTest(){
        //Arrange
        Starfighter starfighter1 = new Starfighter(3, 3);
        GameState gameState1 = new GameState(starfighter1, new Board(9, 9), new ArrayList<>(), false, new ArrayList<>());
        gameState1.getBoard().placeEntity(3, 4, 'X');

        //Act
        boolean result = gameState1.checkColision();

        //Assert 
        assertEquals(true, result);
    }

}
