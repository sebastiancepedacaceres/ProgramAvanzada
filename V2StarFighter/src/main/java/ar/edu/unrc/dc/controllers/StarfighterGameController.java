package ar.edu.unrc.dc.controllers;

import ar.edu.unrc.dc.entities.character.Starfighter;
import ar.edu.unrc.dc.models.Board;
import ar.edu.unrc.dc.models.GameState;
import ar.edu.unrc.dc.models.commands.*;

import ar.edu.unrc.dc.models.commands.sfcommands.*;
import ar.edu.unrc.dc.view.ConsoleRenderer;
import java.util.ArrayList;
import ar.edu.unrc.dc.entities.Position;

/**
 * Controller for the MVC architecture.
 * Controls the flow of the game.
 */

public class StarfighterGameController {
    private GameState gameState;
    private ConsoleRenderer view;
    private boolean isActiveGame;

    /**
     * Initializes the game controller with a console renderer.
     */
    public StarfighterGameController(ConsoleRenderer view) {
        this.view = view;
        this.isActiveGame = false;
    }

    /**
     * Initializes a new game with the given parameters.
     *
     * @param rows Total rows in the grid (3 <= rows <= 10)
     * @param cols Total columns in the grid (5 <= cols <= 30)
     */
    public void play(int rows, int cols) {

        if (isActiveGame && !gameState.isGameOver()) {
            throw new IllegalStateException("No valid game in progress.");
        }

        validateGridSize(rows, cols);
        int startRow = rows / 2;
        if (rows % 2 == 0) {
            startRow = (rows / 2) - 1;
        }
        gameState = new GameState(startRow, 0);

        // Character Set up Phase
        Command setupCommand = new SetUp();
        this.gameState = setupCommand.execute(gameState);
        this.isActiveGame = true;

        // view.render(gameState, "Game Initialized", "ok");
    }

    /**
     * Runs the main game loop, processing phases and user commands until the game
     * is over.
     */
    public void runGameLoop() {
        if (!isActiveGame) {
            throw new IllegalStateException("No active game to run.");
        }

        while (!gameState.isGameOver()) {
            executeGamePhases();
        }

        isActiveGame = false;
    }

    /**
     * Executes all automatic game phases in order.
     */
    private void executeGamePhases() {
        // Phase 1: Friendly Projectiles Act
        executeCommand("Phase 1: Friendly Projectiles Act", new FriendlyProjectilesMove());

        if (gameState.isGameOver())
            return;

        // Phase 2: Enemy Projectiles Act
        executeCommand("Phase 2: Enemy Projectiles Act", new EnemiesProjectileMove());

        if (gameState.isGameOver())
            return;

        // Phase 3: Starfighter Act
        String userInput = view.readUserCommand();
        processUserCommand(userInput);

        if (gameState.isGameOver())
            return;
        // Phase 4: Enemies Act
        executeCommand("Phase 4: Enemies Act", new EnemiesAct());

        if (gameState.isGameOver())
            return;

        // Phase 5: Enemy Vision Update
        executeCommand("Phase 5: Vision Actualization", new VisionActualization());

        if (gameState.isGameOver())
            return;

        // Phase 6: Enemy Spawn
        executeCommand("Phase 6: Spawn Enemies", new SpawnEnemies());
    }

    /**
     * Processes user commands (Phase 3: Starfighter Act).
     */
    private void processUserCommand(String commandInput) {
        Command command;
        String commandName;

        switch (commandInput.toLowerCase()) {
            case "fire":
                executeCommand("fire", new FireCommand());
                break;
            case "move":
                Position position = CheckPosition(); // Checkear que la posicion sea valida??

                executeCommand("move", new MoveCommand(position));
                break;
            case "pass":
                executeCommand("pass", new PassCommand());
                break;
            case "abort":
                executeCommand("abort", new AbortCommand());
                break;
            default:
                // view.renderError("Invalid command. Valid commands are: fire, move, pass,
                // abort"); }
        }
    }

    private Position CheckPosition() {
        return null;
        // view.readPosition();
        // Logica de validación de la posicioón??

    }


    /**
     * Executes a command and updates the game state.
     */
    private void executeCommand(String commandName, Command command) {
        String commandStatus = (gameState == null || gameState.isGameOver()) ? "No active game" : "ok";
        gameState = command.execute(gameState);
        // view.render(gameState, commandName, commandStatus);
    }

    /**
     * Validates game initialization parameters.
     */
    private void validateGridSize(int rows, int cols) {
        if (rows < 3 || rows > 100) {
            throw new IllegalArgumentException("Invalid rows: must be between 3 and 10");
        }
        if (cols < 5 || cols > 100) {
            throw new IllegalArgumentException("Invalid columns: must be between 5 and 30");
        }

    }

    /**
     * Returns the current game state.
     */
    public GameState getGameState() {
        return gameState;
    }

    /**
     * Checks if there is an active game.
     */
    public boolean isActiveGame() {
        return isActiveGame;
    }

}
