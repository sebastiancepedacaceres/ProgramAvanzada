package ar.edu.unrc.dc.models.equalstest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import ar.edu.unrc.dc.models.Board;

public class EqualsBoardsTests {
    @Test
    void emptyBoardsWithSameDimensionsTest() {
        Board b1 = new Board(3, 3);
        Board b2 = new Board(3, 3);


        boolean result = b2.equals(b1);

        assertEquals( true, result);
    }

    @Test
    void boardsWithDifferentContent() {
        Board b1 = new Board(3, 3);
        b1.placeEntity(2,2,'*');
        Board b2 = new Board(3, 3);

        boolean result = b2.equals(b1);

        assertEquals(false, result);

    }
 

    @Test
    void compareBoardAndNull() {
        Board b1 = new Board(3, 3);
       
        boolean result = b1.equals(null);

        assertEquals(false, result);

    }

      @Test
    void compareDifferentObjects() {
        Board board1 = new Board(3, 3);
        String string = "testingString";

        boolean result = board1.equals(string);

        assertEquals(false, result);

    }
}
