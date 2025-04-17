package elysian.development;

import java.util.Random;
import java.util.Scanner;

public class Ships {
    private int length;
    private boolean vertical; // true = vertical, false = horizontal
    private int startX;
    private int startY;

    int[] shipSizes = {5, 4, 3, 3, 2};

    public Ships(int length, boolean vertical, int startX, int startY) {
        this.length = length;
        this.vertical = vertical;
        this.startX = startX;
        this.startY = startY;
    }

    public static void placePlayerFleet(Scanner scanner, Board board) {
        int[] shipSizes = {5, 4, 3, 3, 2};

        for (int i = 0; i < shipSizes.length; i++) {
            int shipLength = shipSizes[i];
            System.out.println("Place ship of length " + shipLength);
            placePlayerShip(scanner, board, shipLength);
            board.printBoard(); // Optional: show progress
        }
    }

    public static void placePlayerShip(Scanner scanner, Board board, int length) {
        boolean placed = false;

        while (!placed) {
            System.out.print("Enter starting row (1–" + board.getHeight() + "): ");
            int row = scanner.nextInt() - 1;

            System.out.print("Enter starting column (1–" + board.getWidth() + "): ");
            int col = scanner.nextInt() - 1;

            System.out.print("Vertical? (true/false): ");
            boolean vertical = scanner.nextBoolean();

            if (board.canPlaceShip(row, col, length, vertical)) {
                board.placeShip(row, col, length, vertical, 'S'); // 'S' for ship
                placed = true;
            } else {
                System.out.println("Invalid placement. Try again.");
            }
        }
    }

    public static void placeAIFleet(Board board, int difficulty) {
        int[] shipSizes = {5, 4, 3, 3, 2};

        for (int shipLength : shipSizes) {
            placeAIShip(board, shipLength, difficulty);
        }
    }

    public static void placeAIShip(Board board, int length, int difficulty) {
        Random rand = new Random();
        boolean placed = false;
        int tries = 0;

        while (!placed && tries < 500) {
            boolean vertical = rand.nextBoolean();
            int row = rand.nextInt(board.getHeight());
            int col = rand.nextInt(board.getWidth());

            if (difficulty >= 3) {
                // Strategic for Hard+
                // Cluster around center or edge, avoid corners
                row = (int)(board.getHeight() * (0.25 + rand.nextDouble() * 0.5));
                col = (int)(board.getWidth() * (0.25 + rand.nextDouble() * 0.5));
            }

            if (board.canPlaceShip(row, col, length, vertical)) {
                board.placeShip(row, col, length, vertical, 'E'); // 'E' for enemy ship
                placed = true;
            } else {
                tries++;
            }
        }
    }

    public int getLength() { return length; }
    public boolean isVertical() { return vertical; }
    public int getStartX() { return startX; }
    public int getStartY() { return startY; }
}