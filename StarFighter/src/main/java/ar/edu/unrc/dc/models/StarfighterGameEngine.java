package ar.edu.unrc.dc.models;

import java.util.ArrayList;

import ar.edu.unrc.dc.models.commands.AbortCommand;
import ar.edu.unrc.dc.models.commands.Command;
import ar.edu.unrc.dc.models.commands.DisplaceProjectilesCommand;
import ar.edu.unrc.dc.models.commands.FireCommand;
import ar.edu.unrc.dc.models.commands.MoveCommand;
import ar.edu.unrc.dc.models.commands.PassCommand;
import ar.edu.unrc.dc.models.entities.Starfighter;
import ar.edu.unrc.dc.view.ConsoleRenderer;

/**
 * The core engine for the Starfighter turn-based terminal game.
 * Manages the game state, including the grid, entities, and turn execution.
 */
public class StarfighterGameEngine {

    private GameState currentGame;
    private int maxMove;
    private int projectileSpeed;
    private final ConsoleRenderer renderer = new ConsoleRenderer();

    /**
     * Initializes a new game with the given parameters. This is the crucial
     * starting command.
     *
     * @param r  Total rows in the grid (3 <= r <= 10)
     * @param c  Total columns in the grid (5 <= c <= 30)
     * @param m1 Max sum of vertical/horizontal moves for the Starfighter (1 <= m1
     *           <= 40)
     * @param m2 Number of horizontal moves for a projectile (1 <= m2 <= 5)
     */
    public void play(int r, int c, int m1, int m2) {

        if (this.currentGame != null && !this.currentGame.isGameOver()) {
        throw new IllegalStateException("Cannot start a game. There is an active game.");
    }

        validateParameters(r, c, m1, m2);

        Board board = new Board(r, c);
        int startRow = r / 2;
        if (r % 2 == 0) {
            startRow = (r / 2) - 1;
        } else {
            startRow = r / 2;
        }
        int startCol = 0;
        Starfighter starfighter = new Starfighter(startRow, startCol);
        board.placeEntity(startRow, startCol, Board.STARFIGHTER);

        currentGame = new GameState(starfighter, board, new ArrayList<>(), false, new ArrayList<>());
        maxMove = m1;
        projectileSpeed = m2;

        renderer.renderBoard(board);
    }

    public GameState getCurrentGame() {
        return currentGame;
    }

    /**
     * Command to move the Starfighter. Moves vertically first, then horizontally.
     * Checks for collisions along the entire path.
     *
     * @param verticalMove   Number of spaces to move vertically (negative is up,
     *                       positive is down)
     * @param horizontalMove Number of spaces to move horizontally (negative is
     *                       left, positive is right)
     */
    public void move(int verticalMove, int horizontalMove) {
        executeCommand("move", new MoveCommand(verticalMove, horizontalMove, maxMove));
    }

    /**
     * Command for the Starfighter to fire a new projectile.
     */
    public void fire() {
        executeCommand("fire", new FireCommand());
    }

    /**
     * Command for the Starfighter to do nothing for this turn.
     */
    public void pass() {
        executeCommand("pass", new PassCommand());
    }

    /**
     * Aborts the current game. Can only be used when a game is active.
     */
    public void abort() {

        executeCommand("abort", new AbortCommand());
        
    }

    public void ProjectilesAdvance() {

        executeCommand("Projectiles advance", new DisplaceProjectilesCommand(projectileSpeed));

    }

    private void executeCommand(String commandName, Command command) {
        String commandStatus = (currentGame == null || currentGame.isGameOver()) ? "No active game"  : "ok";

        currentGame = command.execute(currentGame);
        renderer.render(currentGame, commandName, commandStatus);
    }


    private void validateParameters(int r, int c, int m1, int m2) {
        if (r < 3 || r > 10) {
            throw new IllegalArgumentException("Invalid rows: must be between 3 and 10");
        }
        if (c < 5 || c > 30) {
            throw new IllegalArgumentException("Invalid columns: must be between 5 and 30");
        }
        if (m1 < 1 || m1 > 40) {
            throw new IllegalArgumentException("Invalid m1: must be between 1 and 40");
        }
        if (m2 < 1 || m2 > 5) {
            throw new IllegalArgumentException("Invalid m2: must be between 1 and 5");
        }
    }

}