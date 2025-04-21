package elysian.development.testing;

import elysian.development.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ShipTesting {

    Board board;

    @BeforeEach
    void init() {
        board = new Board(1); // Easy
        clearBoard(board);
    }

    @Test
    void testValidPlacement() {
        assertTrue(board.canPlaceShip(2, 2, 3, true));
        board.placeShip(2, 2, 3, true, 'S');
        assertEquals('S', board.getGrid()[2][2]);
    }

    @Test
    void testInvalidOverlapPlacement() {
        board.placeShip(0, 0, 3, false, 'S');
        assertFalse(board.canPlaceShip(0, 0, 3, false));
    }

    private void clearBoard(Board board) {
        char[][] grid = board.getGrid();
        for (int i = 0; i < board.getHeight(); i++) {
            for (int j = 0; j < board.getWidth(); j++) {
                grid[i][j] = '~';
            }
        }
    }
}
