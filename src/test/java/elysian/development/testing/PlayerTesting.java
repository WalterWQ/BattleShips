package elysian.development.testing;

import elysian.development.PlayerClass;
import elysian.development.Weapons;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTesting {

    @Test
    void testStealthProperties() {
        PlayerClass pc = new PlayerClass(1);
        assertTrue(pc.hasStealth());
        assertEquals(1, pc.getFleet()[0]);
        assertEquals("Torpedo", pc.getWeapons().get(0).getName());
    }

    @Test
    void testHeavyDutyWeapons() {
        PlayerClass pc = new PlayerClass(2);
        assertEquals(2, pc.getWeapons().size());
        Weapons w = pc.getWeapons().get(1);
        assertEquals("Missile", w.getName());
    }
}
