package elysian.development.testing;

import elysian.development.Weapons;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class WeaponTesting {

    @Test
    void testCooldownLogic() {
        Weapons w = new Weapons("Sniper", 2, false, false, 2);
        assertFalse(w.inOnCooldown());
        w.increaseCooldown(2);
        assertTrue(w.inOnCooldown());

        w.reduceCooldown();
        assertEquals(1, w.currentcooldown());

        w.reduceCooldown();
        assertFalse(w.inOnCooldown());
    }

    @Test
    void testWeaponProperties() {
        Weapons w = new Weapons("Missile", 3, true, false, 2);
        assertEquals(3, w.getRadius());
        assertTrue(w.isSplashDamage());
    }
}
