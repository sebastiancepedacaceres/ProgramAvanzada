package ar.edu.unrc.dc.controllers;
import org.junit.jupiter.api.Test;

import ar.edu.unrc.dc.models.GameState;
import ar.edu.unrc.dc.models.StarfighterGameEngine;

import static org.junit.jupiter.api.Assertions.*;

public class StarfighterGameEngineTest {


    @Test
    void playCreatesGameStateTest() {
        // Arrange
        StarfighterGameEngine engine = new StarfighterGameEngine();
        
        // Act
        engine.play(5, 10, 6, 2);
        GameState state = engine.getCurrentGame();
        
        // Assert

        assertNotNull(state);
    }

}
