package ar.edu.unrc.dc.models;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;

import ar.edu.unrc.dc.models.entities.Starfighter;

public class EntitieStarfighterTest {

    @Test
    public void createStarfighterTest() {

        // Arrange
        Starfighter s = new Starfighter(3, 5);

        // Act
        int row = s.getRow();
        int col = s.getColumn();
        boolean destroyed = s.isDestroyed();

        // Assert
        assertEquals(3, row);
        assertEquals(5, col);
        assertFalse(destroyed);
    }

    @Test
    public void createStarfighterDefaultConstructorTest() {

        // Arrange
        Starfighter s = new Starfighter();

        // Act
        int row = s.getRow();
        int col = s.getColumn();
        boolean destroyed = s.isDestroyed();

        // Assert
        assertEquals(0, row);
        assertEquals(0, col);
        assertFalse(destroyed);
    }

    @Test
    public void changePositionStarfighterTest() {

        // Arrange
        Starfighter s = new Starfighter(2, 2);

        // Act
        s.move(4, 4);
        int row = s.getRow();
        int col = s.getColumn();

        // Assert
        assertEquals(4, row);
        assertEquals(4, col);
    }

    @Test
    public void setPositionStarfighterTest() {

        // Arrange
        Starfighter s = new Starfighter(2, 2);

        // Act
        s.setPosition(6, 8);
        int row = s.getRow();
        int col = s.getColumn();

        // Assert
        assertEquals(6, row);
        assertEquals(8, col);
    }

    @Test
    public void destroyStarfighterTest() {

        // Arrange
        Starfighter s = new Starfighter(3, 3);

        // Act
        s.destroy();
        boolean destroyed = s.isDestroyed();

        // Assert
        assertTrue(destroyed);
    }

}