package ar.edu.unrc.dc.models.commandstest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import ar.edu.unrc.dc.models.Board;
import ar.edu.unrc.dc.models.GameState;
import ar.edu.unrc.dc.models.commands.MoveCommand;
import ar.edu.unrc.dc.models.entities.Projectile;
import ar.edu.unrc.dc.models.entities.Entity;
import ar.edu.unrc.dc.models.entities.Starfighter;

public class MoveCommandTest {
    
    @Test
    void moveStarfighterChangesPositionTest() {
        // Arrange input
        Starfighter sf = new Starfighter(2, 2);
    ArrayList<Entity> projectiles = new ArrayList<>();
        Board startingBoard = new Board(5, 5);
        startingBoard.placeEntity(2, 2, 'S');
        GameState input = new GameState(sf, startingBoard, projectiles, false, new ArrayList<>());
        
        MoveCommand moveCommand = new MoveCommand(1, 1, 5); 
        
        GameState expectedState = input.clone();
        expectedState.setStarfighter(new Starfighter(3, 3));
        expectedState.getBoard().clearCell(2, 2);
        expectedState.getBoard().placeEntity(3, 3, 'S');
        
        // Act
        GameState result = moveCommand.execute(input);
        
        // Assert
        assertEquals(3, result.getStarfighter().getRow());
        assertEquals(3, result.getStarfighter().getColumn());
        assertEquals(expectedState.getBoard(), result.getBoard());
    }

    @Test
    void moveStarfighterCollidesWithProjectileDuringVerticalMovementTest() {
        
        // Arrange input - proyectil en el camino vertical
        Starfighter sf = new Starfighter(1, 2);
        ArrayList<Entity> projectiles = new ArrayList<>();
        projectiles.add(new Projectile(3, 2)); 
        
        Board startingBoard = new Board(5, 5);
        startingBoard.placeEntity(1, 2, 'S');
        startingBoard.placeEntity(3, 2, '*');
        
        GameState input = new GameState(sf, startingBoard, projectiles, false, new ArrayList<>());
        
        MoveCommand moveCommand = new MoveCommand(3, 1, 5); 
        
        GameState expectedState = input.clone();
        expectedState.getStarfighter().destroy();
        expectedState.getStarfighter().setPosition(3, 2);
        expectedState.getBoard().clearCell(1, 2);
        expectedState.getBoard().placeEntity(3, 2, 'X');
        expectedState.setGameOver(true);
        
        // Act
        GameState result = moveCommand.execute(input);
        
        // Assert
        assertTrue(result.getStarfighter().isDestroyed());
        assertEquals(3, result.getStarfighter().getRow());
        assertEquals(2, result.getStarfighter().getColumn());
        assertTrue(result.isGameOver());
        assertEquals('X', result.getBoard().getCellContent(3, 2));
    }

    @Test
    void moveExceedsMaxMoveLimitTest() {
        // Arrange
        Starfighter sf = new Starfighter(2, 2);
        Board board = new Board(5, 5);
        board.placeEntity(2, 2, Board.STARFIGHTER);
        GameState state = new GameState(sf, board, new ArrayList<>(), false, new ArrayList<>());
        // Act
        MoveCommand cmd = new MoveCommand(3, 4, 6); 
        Exception ex = assertThrows(IllegalArgumentException.class, () -> cmd.execute(state));
        
        // Assert
        assertTrue(ex.getMessage().contains("exceeds maximum"));
    }

    @Test
    void moveDoesNotExceedTopBoundaryTest() {
        // Arrange
        Starfighter sf = new Starfighter(1, 2);
        Board board = new Board(5, 5);
        board.placeEntity(1, 2, Board.STARFIGHTER);
        GameState state = new GameState(sf, board, new ArrayList<>(), false, new ArrayList<>());
        
         // Act
        MoveCommand mvcmd = new MoveCommand(-5, 0, 10); 
        GameState result = mvcmd.execute(state);
        
        // Assert
        assertEquals(0, result.getStarfighter().getRow()); 
        assertEquals(2, result.getStarfighter().getColumn());
        assertEquals(Board.STARFIGHTER, result.getBoard().getCellContent(0, 2));
    }

        @Test
    void moveDoesNotExceedBottomBoundaryTest() {
        // Arrange input
        Starfighter sf = new Starfighter(3, 2);
        ArrayList<Entity> projectiles = new ArrayList<>();
        Board startingBoard = new Board(5, 5);
        startingBoard.placeEntity(3, 2, Board.STARFIGHTER);
        GameState input = new GameState(sf, startingBoard, projectiles, false, new ArrayList<>());
        
        MoveCommand moveCommand = new MoveCommand(5, 0, 10);
        
        GameState expectedState = input.clone();
        expectedState.setStarfighter(new Starfighter(4, 2));
        expectedState.getBoard().clearCell(3, 2);
        expectedState.getBoard().placeEntity(4, 2, Board.STARFIGHTER);
        
        // Act
        GameState result = moveCommand.execute(input);
        
        // Assert
        assertEquals(4, result.getStarfighter().getRow());
        assertEquals(2, result.getStarfighter().getColumn());
        assertEquals(expectedState.getBoard(), result.getBoard());
    }

    @Test
    void moveDoesNotExceedLeftBoundaryTest() {
        // Arrange input
        Starfighter sf = new Starfighter(2, 2);
        ArrayList<Entity> projectiles = new ArrayList<>();
        Board startingBoard = new Board(5, 5);
        startingBoard.placeEntity(2, 2, Board.STARFIGHTER);
        GameState input = new GameState(sf, startingBoard, projectiles, false, new ArrayList<>());
        
        MoveCommand moveCommand = new MoveCommand(0, -5, 10);
        
        GameState expectedState = input.clone();
        expectedState.setStarfighter(new Starfighter(2, 0));
        expectedState.getBoard().clearCell(2, 2);
        expectedState.getBoard().placeEntity(2, 0, Board.STARFIGHTER);
        
        // Act
        GameState result = moveCommand.execute(input);
        
        // Assert
        assertEquals(2, result.getStarfighter().getRow());
        assertEquals(0, result.getStarfighter().getColumn());
        assertEquals(expectedState.getBoard(), result.getBoard());
    }

    @Test
    void moveDoesNotExceedRightBoundaryTest() {
        // Arrange input
        Starfighter sf = new Starfighter(2, 2);
        ArrayList<Entity> projectiles = new ArrayList<>();
        Board startingBoard = new Board(5, 5);
        startingBoard.placeEntity(2, 2, Board.STARFIGHTER);
        GameState input = new GameState(sf, startingBoard, projectiles, false, new ArrayList<>());
        
        MoveCommand moveCommand = new MoveCommand(0, 5, 10);
        
        GameState expectedState = input.clone();
        expectedState.setStarfighter(new Starfighter(2, 4));
        expectedState.getBoard().clearCell(2, 2);
        expectedState.getBoard().placeEntity(2, 4, Board.STARFIGHTER);
        
        // Act
        GameState result = moveCommand.execute(input);
        
        // Assert
        assertEquals(2, result.getStarfighter().getRow());
        assertEquals(4, result.getStarfighter().getColumn());
        assertEquals(expectedState.getBoard(), result.getBoard());
    }

}
