package ar.edu.unrc.dc;

import ar.edu.unrc.dc.models.Board;
import ar.edu.unrc.dc.models.GameState;
import ar.edu.unrc.dc.models.entities.Starfighter;
import ar.edu.unrc.dc.view.ConsoleRenderer;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ViewTest {

    @Test
    void testRenderBoard() {
        Board board = new Board(3, 3);
        board.placeEntity(1, 1, 'S');
        ConsoleRenderer renderer = new ConsoleRenderer();
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        renderer.renderBoard(board);
        String output = outContent.toString();
        assertTrue(output.contains("S"));
        assertTrue(output.contains("0 1 2"));
        assertTrue(output.contains("S"));
    }

    @Test
    void testRenderProjectileMovement() {
        GameState state = new GameState(3, 3);
        state.setProjectileMovements(java.util.List.of(
                new ar.edu.unrc.dc.models.ProjectileMovement(0, 0, 0, 1, false),
                new ar.edu.unrc.dc.models.ProjectileMovement(1, 1, 1, 2, true)));
        ConsoleRenderer renderer = new ConsoleRenderer();
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        renderer.renderProjectileMovement(state);
        String output = outContent.toString();
        assertTrue(output.contains("A projectile moves: (0,0) -> (0,1)"));
        assertTrue(output.contains("A projectile moves and collides with the Starfighter: (1,1) -> (1,2)"));
    }

    @Test
    void testRenderError() {
        ConsoleRenderer renderer = new ConsoleRenderer();
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        renderer.renderError("Test error");
        String output = outContent.toString();
        assertTrue(output.contains("Error: Test error"));
    }

    @Test
    void testRenderMessage() {
        ConsoleRenderer renderer = new ConsoleRenderer();
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        renderer.renderMessage("Test message");
        String output = outContent.toString();
        assertTrue(output.contains("Error: Test message"));
    }

    @Test
    void testRenderGameStateFireCommand() {
        Starfighter sf = new Starfighter(2, 2);
        Board board = new Board(5, 5);
        board.placeEntity(2, 2, 'S');
        GameState state = new GameState(sf, board, new java.util.ArrayList<>(), false, new java.util.ArrayList<>());
        ConsoleRenderer renderer = new ConsoleRenderer();
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        renderer.render(state, "fire", "ok");

        String output = outContent.toString();
        assertTrue(output.contains("Command issued: fire (ok)"));
        assertTrue(output.contains("S"));
        assertTrue(output.contains("0 1 2 3 4"));
    }

    @Test
    void testRenderGameStateMoveCommand() {
        Starfighter sf = new Starfighter(1, 1);
        Board board = new Board(3, 3);
        board.placeEntity(1, 1, 'S');
        GameState state = new GameState(sf, board, new java.util.ArrayList<>(), false, new java.util.ArrayList<>());
        ConsoleRenderer renderer = new ConsoleRenderer();
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        renderer.render(state, "move", "ok");

        String output = outContent.toString();
        assertTrue(output.contains("Command issued: move (ok)"));
        assertTrue(output.contains("S"));
        assertTrue(output.contains("0 1 2"));
    }

    @Test
    void testRenderGameStatePassCommand() {
        Starfighter sf = new Starfighter(0, 0);
        Board board = new Board(2, 2);
        board.placeEntity(0, 0, 'S');
        GameState state = new GameState(sf, board, new java.util.ArrayList<>(), false, new java.util.ArrayList<>());
        ConsoleRenderer renderer = new ConsoleRenderer();
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        renderer.render(state, "pass", "ok");

        String output = outContent.toString();
        assertTrue(output.contains("Command issued: pass (ok)"));
        assertTrue(output.contains("S"));
        assertTrue(output.contains("0 1"));
    }

    @Test
    void testRenderGameStateAbortCommand() {
        Starfighter sf = new Starfighter(0, 0);
        Board board = new Board(2, 2);
        board.placeEntity(0, 0, 'S');
        GameState state = new GameState(sf, board, new java.util.ArrayList<>(), true, new java.util.ArrayList<>());
        ConsoleRenderer renderer = new ConsoleRenderer();
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        renderer.render(state, "abort", "ok");

        String output = outContent.toString();
        assertTrue(output.contains("Command issued: abort (ok)"));
        assertTrue(output.contains("Game over."));
    }

    @Test
    void testRenderGameStatePhase1AndMoveCommand() {
        Starfighter sf = new Starfighter(0, 0);
        Board board = new Board(2, 2);
        board.placeEntity(0, 0, 'S');
        GameState state = new GameState(sf, board, new java.util.ArrayList<>(), false, new java.util.ArrayList<>());
        ConsoleRenderer renderer = new ConsoleRenderer();
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Simula fase 1 
        state.setProjectileMovements(java.util.List.of(
            new ar.edu.unrc.dc.models.ProjectileMovement(0, 0, 0, 1, false)
        ));
        renderer.renderProjectileMovement(state);

        // Simula comando move
        renderer.render(state, "move", "ok");

        String output = outContent.toString();
        System.out.print(output); // Imprime la salida capturada para inspección manual
        assertTrue(output.contains("A projectile moves: (0,0) -> (0,1)"));
        assertTrue(output.contains("Command issued: move (ok)"));
        assertTrue(output.contains("S"));
        assertTrue(output.contains("0 1"));
    }

}
