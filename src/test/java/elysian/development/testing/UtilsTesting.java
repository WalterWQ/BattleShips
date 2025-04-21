package elysian.development.testing;

import elysian.development.Utils;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UtilsTesting {

    @Test
    void testGridSizeEasy() {
        int[] size = Utils.getGridSize(1);
        assertArrayEquals(new int[]{8, 8}, size);
    }

    @Test
    void testGridSizeMedium() {
        int[] size = Utils.getGridSize(2);
        assertArrayEquals(new int[]{10, 10}, size);
    }

    @Test
    void testMakeRange() {
        int[] expected = {1, 2, 3, 4, 5};
        assertArrayEquals(expected, Utils.makeRange(1, 5));
    }
}
