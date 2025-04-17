package elysian.development;

import java.util.Random;
import java.util.Scanner;

public class Ships {
    private int length;
    private boolean vertical; // true = vertical, false = horizontal
    private int startX;
    private int startY;

    public Ships(int length, boolean vertical, int startX, int startY) {
        this.length = length;
        this.vertical = vertical;
        this.startX = startX;
        this.startY = startY;
    }


    public static void placePlayerFleet(Scanner scanner, Board board, PlayerClass playerClass) {
        int[] shipSizes = playerClass.getFleet();

        for (int length : shipSizes) {
            System.out.println("Place ship of length " + length);
            board.printBoard(); // Optional for feedbackf
            Ships.placePlayerShip(scanner, board, length);

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
                board.placeShip(row, col, length, vertical, 'S');
                placed = true;
                board.printBoard(); // Optional: show after placing
            } else {
                System.out.println("Invalid position! That ship overlaps or is out of bounds. Try again.");
            }
        }
    }

    public static void placeAIFleet(Board board, int difficulty) {
        int aiClassId = new Random().nextInt(4) + 1;
        PlayerClass aiClass = new PlayerClass(aiClassId);

        System.out.println("AI has chosen class: " + aiClass.getType());

        int[] shipSizes = aiClass.getFleet();

        for (int length : shipSizes) {
            Ships.placeAIShip(board, length, difficulty);
        }
    }

    public static void placeAIShip(Board board, int length, int difficulty) {
        Random rand = new Random();
        boolean placed = false;
        int attempts = 0;

        while (!placed && attempts < 500) {
            boolean vertical = rand.nextBoolean();
            int row = rand.nextInt(board.getHeight());
            int col = rand.nextInt(board.getWidth());

            if (board.canPlaceShip(row, col, length, vertical)) {
                board.placeShip(row, col, length, vertical, 'E');
                placed = true;
            } else {
                attempts++;
            }
        }

        if (!placed) {
            System.out.println("AI failed to place ship of length " + length);
        }
    }

    public int getLength() { return length; }
    public boolean isVertical() { return vertical; }
    public int getStartX() { return startX; }
    public int getStartY() { return startY; }
}