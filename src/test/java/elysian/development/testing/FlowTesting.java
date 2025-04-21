package elysian.development.testing;

import elysian.development.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FlowTesting {

    private Board playerBoard;
    private Board enemyBoard;
    private PlayerClass playerClass;

    @BeforeEach
    void setup() {
        playerBoard = new Board(1); // Easy
        enemyBoard = new Board(1);
        clearBoard(playerBoard);
        clearBoard(enemyBoard);

        playerClass = new PlayerClass(2); // Heavy Duty
    }

    @Test
    void testWeaponHitAndMiss() {
        // Place enemy ship at (2,2)
        enemyBoard.placeShip(2, 2, 1, false, 'E');

        Weapons missile = playerClass.getWeapons().get(1); // Missile
        Game.useWeapon(enemyBoard, playerBoard, missile, 2, 2); // direct hit

        assertEquals('X', enemyBoard.getGrid()[2][2]);
        assertEquals('X', playerBoard.getGrid()[2][2]);
    }

    @Test
    void testWinCondition() {
        enemyBoard.placeShip(0, 0, 1, false, 'E');
        assertFalse(Game.allShipsSunk(enemyBoard, 'E'));

        enemyBoard.getGrid()[0][0] = 'X';
        assertTrue(Game.allShipsSunk(enemyBoard, 'E'));

        System.out.println("Win condition test passed.");
    }

    @Test
    void testCooldown() {
        Weapons w = playerClass.getWeapons().get(1); // Missile
        assertFalse(w.inOnCooldown());

        w.increaseCooldown(2);
        assertTrue(w.inOnCooldown());

        w.reduceCooldown();
        assertEquals(1, w.currentcooldown());

        System.out.println("Cooldown test passed.");
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
