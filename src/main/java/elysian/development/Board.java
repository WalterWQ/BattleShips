package elysian.development;

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
        generateBoard();
    }

    private void generateBoard() {
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                grid[row][col] = '~'; // ocean water
            }
        }

    }

    public void printBoard() {
        int colPadding = String.valueOf(width).length(); // Handles any width, even 100x100

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