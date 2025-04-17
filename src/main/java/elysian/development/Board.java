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

    void generateBoard(int difficulty) {
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

    public boolean canPlaceShip(int row, int col, int length, boolean vertical) {
        for (int i = 0; i < length; i++) {
            int r = row + (vertical ? i : 0);
            int c = col + (vertical ? 0 : i);

            if (r >= height || c >= width || grid[r][c] != '~') {
                return false; // out of grid or not water
            }
        }
        return true;
    }

    public void placeShip(int row, int col, int length, boolean vertical, char symbol) {
        for (int i = 0; i < length; i++) {
            int r = row + (vertical ? i : 0);
            int c = col + (vertical ? 0 : i);
            grid[r][c] = symbol;
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

    public int getWidth() { return width; }
    public int getHeight() { return height; }
    public char[][] getGrid() { return grid; }
}