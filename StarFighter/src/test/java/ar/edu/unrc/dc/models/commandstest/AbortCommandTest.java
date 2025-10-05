package ar.edu.unrc.dc.models.commandstest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import ar.edu.unrc.dc.models.GameState;
import ar.edu.unrc.dc.models.commands.AbortCommand;

public class AbortCommandTest {

    @Test
    void abosrtCommandGameTest() {
        // Arrange input
        int row = 10;
        int col = 10;
        GameState input = new GameState(row, col);
        GameState expectedState = input.clone();
        expectedState.setGameOver(true);
        AbortCommand abortCommand = new AbortCommand();

        // Act
        GameState result = abortCommand.execute(input);

        // Assert
        assertEquals(expectedState.getBoard(), result.getBoard());

    }


}