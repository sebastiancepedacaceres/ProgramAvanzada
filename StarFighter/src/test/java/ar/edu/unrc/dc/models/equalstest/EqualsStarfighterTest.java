package ar.edu.unrc.dc.models.equalstest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import ar.edu.unrc.dc.models.entities.Starfighter;

public class EqualsStarfighterTest {

     @Test
    void starfighterInSamePositionTest() {
        Starfighter starfighter1 = new Starfighter(3, 3);
        Starfighter starfighter2 = new Starfighter(3, 3);


        boolean result = starfighter1.equals(starfighter2);

        assertEquals( true, result);
    }

    @Test
    void starfightersOneDestroyed() {
        Starfighter starfighter1 = new Starfighter(3, 3);
        
        Starfighter starfighter2 = new Starfighter(3, 3);
        starfighter2.destroy();


        boolean result = starfighter1.equals(starfighter2);

        assertEquals( false, result);
    }





}
