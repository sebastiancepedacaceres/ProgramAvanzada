package ar.edu.unrc.dc.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class BoardTest {

    @Test
    void cloneBoardTest() {
        Board board1 = new Board(3, 3);


        Board result = board1.clone();

        assertEquals( board1, result);
    }

}
