package ar.edu.unrc.dc.models.commandstest;

import ar.edu.unrc.dc.models.GameState;
import ar.edu.unrc.dc.models.commands.PassCommand;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

public class PassCommandTest {

    @Test
    void passCommandGameTest() {
        // Arrange input
        int row = 10;
        int col = 10;
        GameState input = new GameState(row, col);
    
        // Arrange expected
        GameState expectedState = input.clone();
        PassCommand passCommand = new PassCommand();

        // Act
        GameState result = passCommand.execute(input);

        // Assert
        assertEquals(expectedState.getBoard(), result.getBoard());

    }


}
