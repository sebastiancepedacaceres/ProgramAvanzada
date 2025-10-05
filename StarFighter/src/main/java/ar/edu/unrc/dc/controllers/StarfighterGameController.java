package ar.edu.unrc.dc.controllers;

import ar.edu.unrc.dc.models.StarfighterGameEngine;
import ar.edu.unrc.dc.view.ConsoleRenderer;

public class StarfighterGameController {
    private StarfighterGameEngine engine;
    private ConsoleRenderer view;

    public StarfighterGameController() {
        engine = new StarfighterGameEngine();
        view = new ConsoleRenderer();
    }

    public void startGame(int rows, int cols, int maxMove, int projectileSpeed) {
        engine.play(rows, cols, maxMove, projectileSpeed);
    }

    public void runGameLoop() {
        while (!engine.getCurrentGame().isGameOver()) {

            engine.ProjectilesAdvance(); // Phase 1

            if (!engine.getCurrentGame().isGameOver()) {

                String command = view.readUserCommand(); // Phase 2
                switch (command) {
                    case "fire":
                        engine.fire();
                        break;
                    case "move":
                        int verticalMoves = view.readInteger("Vertical moves (negative=up, positive=down): ");
                        int horizontalMoves = view.readInteger("Horizontal moves (negative=left, positive=right): ");
                        engine.move(verticalMoves, horizontalMoves);
                        break;
                    case "pass":
                        engine.pass();
                        break;
                    case "abort":
                        engine.abort();
                        break;
                    default:
                        view.renderError("Invalid command. Valid commands are: fire, move, pass, abort");                }
            }
            
        }
    }

}
