package elysian.development.testing;

import elysian.development.Board;
import elysian.development.Utils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTesting {

    private Board easyBoard;
    private Board mediumBoard;
    private Board hardBoard;
    private Board insaneBoard;

    @BeforeEach
    void setUp() {
        easyBoard = new Board(1); // Easy difficulty
        mediumBoard = new Board(2); // Easy difficulty
        hardBoard = new Board(3); // Easy difficulty
        insaneBoard = new Board(4); // Easy difficulty
    }

    @Test
    void testBoardDimensions() {
        assertEquals(8, easyBoard.getWidth(), "Width should be 8 for easy difficulty");
        assertEquals(8, easyBoard.getHeight(), "Height should be 8 for easy difficulty");
        assertEquals(10, mediumBoard.getWidth(), "Width should be 10 for Medium difficulty");
        assertEquals(10, mediumBoard.getHeight(), "Height should be 10 for Medium difficulty");
        assertEquals(12, hardBoard.getWidth(), "Width should be 12 for Hard difficulty");
        assertEquals(12, hardBoard.getHeight(), "Height should be 12 for Hard difficulty");
        assertEquals(15, insaneBoard.getWidth(), "Width should be 15 for Insane difficulty");
        assertEquals(15, insaneBoard.getHeight(), "Height should be 15 for Insane difficulty");
    }

    @Test
    void testInitialWaterOnly() {
        char[][] grid = easyBoard.getGrid();
        boolean onlyWaterOrObstacles = true;

        for (char[] row : grid) {
            for (char cell : row) {
                if (cell != '~' && cell != '#') {
                    onlyWaterOrObstacles = false;
                }
            }
        }

        assertTrue(onlyWaterOrObstacles, "Board should only contain water or obstacles initially");
    }

    @Test
    void testShipCanBePlaced() {
        Utils.clearBoard(easyBoard);
        Utils.clearBoard(mediumBoard);
        Utils.clearBoard(hardBoard);
        Utils.clearBoard(insaneBoard);

        assertTrue(easyBoard.canPlaceShip(0, 0, 3, true), "Should be able to place a vertical ship at top left");
        assertTrue(easyBoard.canPlaceShip(5, 2, 2, false), "Should be able to place a horizontal ship");
        assertTrue(mediumBoard.canPlaceShip(0, 0, 3, true), "Should be able to place a vertical ship at top left");
        assertTrue(mediumBoard.canPlaceShip(5, 2, 2, false), "Should be able to place a horizontal ship");
        assertTrue(hardBoard.canPlaceShip(0, 0, 3, true), "Should be able to place a vertical ship at top left");
        assertTrue(hardBoard.canPlaceShip(5, 2, 2, false), "Should be able to place a horizontal ship");
        assertTrue(insaneBoard.canPlaceShip(0, 0, 3, true), "Should be able to place a vertical ship at top left");
        assertTrue(insaneBoard.canPlaceShip(5, 2, 2, false), "Should be able to place a horizontal ship");
    }

    @Test
    void testShipPlacementInvalidOnOverlap() {
        Utils.clearBoard(easyBoard);
        Utils.clearBoard(mediumBoard);
        Utils.clearBoard(hardBoard);
        Utils.clearBoard(insaneBoard);

        easyBoard.placeShip(0, 0, 3, true, 'S');
        assertFalse(easyBoard.canPlaceShip(0, 0, 3, true), "Should not allow placing a ship over another ship");
        mediumBoard.placeShip(0, 0, 3, true, 'S');
        assertFalse(mediumBoard.canPlaceShip(0, 0, 3, true), "Should not allow placing a ship over another ship");
        hardBoard.placeShip(0, 0, 3, true, 'S');
        assertFalse(hardBoard.canPlaceShip(0, 0, 3, true), "Should not allow placing a ship over another ship");
        insaneBoard.placeShip(0, 0, 3, true, 'S');
        assertFalse(insaneBoard.canPlaceShip(0, 0, 3, true), "Should not allow placing a ship over another ship");
    }

    @Test
    void testShipPlacementOutOfBounds() {

        Utils.clearBoard(easyBoard);
        Utils.clearBoard(mediumBoard);
        Utils.clearBoard(hardBoard);
        Utils.clearBoard(insaneBoard);

        int heightEasy = easyBoard.getHeight();
        int widthEasy = easyBoard.getWidth();
        assertFalse(easyBoard.canPlaceShip(heightEasy - 1, widthEasy - 1, 4, false), "Should not allow ship out of bounds");
        int heightMedium = mediumBoard.getHeight();
        int widthMedium = mediumBoard.getWidth();
        assertFalse(mediumBoard.canPlaceShip(heightMedium - 1, widthMedium - 1, 4, false), "Should not allow ship out of bounds");
        int heightHard = hardBoard.getHeight();
        int widthHard = hardBoard.getWidth();
        assertFalse(hardBoard.canPlaceShip(heightHard - 1, widthHard - 1, 4, false), "Should not allow ship out of bounds");
        int heightInsane = insaneBoard.getHeight();
        int widthInsane = insaneBoard.getWidth();
        assertFalse(insaneBoard.canPlaceShip(heightInsane - 1, widthInsane - 1, 4, false), "Should not allow ship out of bounds");
    }
}
