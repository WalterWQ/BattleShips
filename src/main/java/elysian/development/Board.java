package elysian.development;

import java.util.Random;

public class Board {
    private char[][] grid;
    private int width;
    private int height;
    private int colPadding = String.valueOf(width).length();


    public Board(int difficulty) {
        int[] size = Utils.getGridSize(difficulty);
        this.width = size[0];
        this.height = size[1];
        this.grid = new char[height][width];
        generateBoard(difficulty);
    }

    private void generateBoard(int difficulty) {
        // Step 1: Fill the board with water
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                grid[row][col] = '~'; // Ocean water
            }
        }

        // Step 2: Place obstacles based on difficulty
        Random rand = new Random();
        double obstacleDensity;

        switch (difficulty) {
            case 1: obstacleDensity = 0.01; break; // Easy
            case 2: obstacleDensity = 0.03; break; // Medium
            case 3: obstacleDensity = 0.06; break; // Hard
            case 4: obstacleDensity = 0.10; break; // Insane
            default: obstacleDensity = 0.03;
        }

        int obstacleCount = (int)(width * height * obstacleDensity);

        for (int i = 0; i < obstacleCount; i++) {
            int x = rand.nextInt(height);
            int y = rand.nextInt(width);

            if (grid[x][y] == '~') {
                grid[x][y] = '#'; // Place rock only on water
            } else {
                i--; // Retry if already occupied by rock
            }
        }
    }

    public void printBoard() {
        int colPadding = String.valueOf(width).length(); // Handles any width, even 100x100 fancy i know

        System.out.print(" ".repeat(colPadding + 2)); // Padding for the row numbers
        for (int col = 1; col <= width; col++) {
            System.out.printf("%" + (colPadding + 1) + "d", col);
        }
        System.out.println();

        for (int row = 0; row < height; row++) {
            System.out.printf("%" + (colPadding + 1) + "d ", row + 1);
            for (int col = 0; col < width; col++) {
                System.out.printf("%" + (colPadding + 1) + "s", grid[row][col]);
            }
            System.out.println();
        }
    }

    public int getWidth() { return width; }
    public int getHeight() { return height; }
    public char[][] getGrid() { return grid; }
}