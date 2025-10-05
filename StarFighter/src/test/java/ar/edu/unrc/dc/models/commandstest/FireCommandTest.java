package ar.edu.unrc.dc.models.commandstest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import ar.edu.unrc.dc.models.Board;
import ar.edu.unrc.dc.models.GameState;
import ar.edu.unrc.dc.models.commands.FireCommand;
import ar.edu.unrc.dc.models.entities.Entity;
import ar.edu.unrc.dc.models.entities.ProjectileFactory;
import ar.edu.unrc.dc.models.entities.Starfighter;

public class FireCommandTest {

    @Test
    void InitialStateFireChangeBoardTest() {
        // Arrange input
        Starfighter sf = new Starfighter(2, 2);
        ArrayList<Entity> projectiles = new ArrayList<>();
        Board startingBoard = new Board(5, 5);
        startingBoard.placeEntity(2, 2, 'S');
        GameState input = new GameState(sf, startingBoard, projectiles, false, new ArrayList<>());
        FireCommand fireCommand = new FireCommand();
        // Arrange expected
        GameState expectedState = input.clone();
        expectedState.getBoard().placeEntity(2, 3, '*');

        // Act
        GameState result = fireCommand.execute(input);

        // Assert
        assertEquals(expectedState.getBoard(), result.getBoard());

    }

    @Test
    void InitialStateFireCommandIncreaseActiveProjectilesTest() {
        // Arrange input
        Starfighter sf = new Starfighter(2, 2);
        ArrayList<Entity> projectiles = new ArrayList<>();
        Board startingBoard = new Board(5, 5);
        startingBoard.placeEntity(2, 2, 'S');
        GameState input = new GameState(sf, startingBoard, projectiles, false, new ArrayList<>());
        FireCommand fireCommand = new FireCommand();

        GameState expectedState = input.clone();

        expectedState.getBoard().placeEntity(2, 3, '*');
        ProjectileFactory factory = new ProjectileFactory();
        expectedState.getProjectiles().add(factory.createProjectile(2, 3));
        // Act
        GameState result = fireCommand.execute(input);

        // Assert
        assertEquals(0, input.getProjectiles().size());
        assertEquals(1, result.getProjectiles().size());
        assertEquals(expectedState, result);

    }


    @Test
    void starfighterOnRightEdgeTest() {
        // Arrange input
        Starfighter sf = new Starfighter(2, 4);
        ArrayList<Entity> projectiles = new ArrayList<>();
        Board startingBoard = new Board(5, 5);
        startingBoard.placeEntity(2, 4, 'S');
        GameState input = new GameState(sf, startingBoard, projectiles, false, new ArrayList<>());
        FireCommand fireCommand = new FireCommand();

        GameState expectedState = input.clone();

        // Act
        GameState result = fireCommand.execute(input);

        // Assert
        assertEquals(result.getProjectiles().size(), input.getProjectiles().size());
        assertEquals(expectedState, result);

    }
}
