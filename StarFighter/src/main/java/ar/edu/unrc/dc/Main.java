package ar.edu.unrc.dc;

import ar.edu.unrc.dc.controllers.StarfighterGameController;


/**
 * Main class to run the Starfighter game engine.
 */
public class Main {

    public static void main(String[] args) {

        StarfighterGameController controller = new StarfighterGameController();

        controller.startGame(3, 5, 4, 1);

        controller.runGameLoop();

    }

   
}