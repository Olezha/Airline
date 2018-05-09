package ua.olezha.airline.model.aircraft;

import org.junit.Test;

import static org.junit.Assert.*;

public class AircraftTest {

    @Test
    public void equalsTest() {
        assertTrue(new WideBodyAirliner().equals(new WideBodyAirliner()));
    }

    @Test
    public void hashCodeTest() {
        Aircraft aircraft = new Helicopter();
        assertEquals(aircraft.hashCode(), aircraft.hashCode());
    }

    @Test
    public void toStringTest() {
        assertNotNull(new Commuterliner().toString());
    }
}
