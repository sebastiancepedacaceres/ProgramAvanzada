package ar.edu.unrc.dc.models;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import ar.edu.unrc.dc.models.entities.Projectile;



public class EntitieProjectilTest {

    @Test
    public void createProjectilTest() {
        // Arrange
        Projectile p = new Projectile(2,2);

        // Act
        int row = p.getRow();
        int col = p.getColumn();
        
        // Assert
        assertEquals(2, row);
        assertEquals(2, col);
    }

    public void changePositionProjectilTest() {
        // Arrange
        Projectile p = new Projectile(2,2);

        // Act
        p.move(4, 4);
        int row = p.getRow();
        int col = p.getColumn();
        
        
        // Assert
        assertEquals(4, row);
        assertEquals(4, col);
    }
}