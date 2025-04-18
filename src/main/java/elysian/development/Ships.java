package elysian.development;

import java.util.Random;
import java.util.Scanner;

public class Ships {
    private int length;
    private boolean vertical; // true = vertical, false = horizontal
    private int startX;
    private int startY;
    private boolean[] hitSegments;
    private String name;

    public Ships(String name, int length, boolean vertical, int startX, int startY) {
        this.name = name;
        this.length = length;
        this.vertical = vertical;
        this.startX = startX;
        this.startY = startY;
        this.hitSegments = new boolean[length];
    }

    public boolean isSunk() {
        for (boolean hit : hitSegments) {
            if(!hit) return false;
        }
        return true;
    }

    public void hit(int index) {
        hitSegments[index] = true;
    }

    public boolean occupies(int row, int col) {
        for (int i = 0; i < length; i++) {
            int r = startX + (vertical ? i : 0);
            int c = startY + (vertical ? 0 : i);
            if (r == row && c == col) return true;
        }
        return false;
    }

    public int getIndex(int row, int col) {
        for (int i = 0; i < length; i++) {
            int r = startX + (vertical ? i : 0);
            int c = startY + (vertical ? 0 : i);
            if (r == row && c == col) return i;
        }
        return -1;
    }


    public static void placePlayerFleet(Scanner scanner, Board board, PlayerClass playerClass) {
        int[] shipSizes = playerClass.getFleet();

        for (int length : shipSizes) {
            System.out.println("Place ship of length " + length);
            board.printBoard(); // Optional for feedbackf
            Ships.placePlayerShip(scanner, board, length);

        }
    }

    public static void placePlayerShip(Scanner userInput, Board board, int length) {
        boolean placed = false;

        while (!placed) {
            System.out.print("Enter starting row (1–" + board.getHeight() + "): ");
            int row = Utils.getValidChoice(Utils.makeRange(1, board.getHeight()), userInput) - 1;

            System.out.print("Enter starting column (1–" + board.getWidth() + "): ");
            int col = Utils.getValidChoice(Utils.makeRange(1, board.getWidth()), userInput) - 1;

            System.out.print("Vertical? (true/false): ");
            boolean vertical = userInput.nextBoolean();

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

    public String getName() { return name; }
    public int getLength() { return length; }
    public boolean isVertical() { return vertical; }
    public int getStartX() { return startX; }
    public int getStartY() { return startY; }
}