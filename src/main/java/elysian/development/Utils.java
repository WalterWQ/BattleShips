package elysian.development;

import java.util.Arrays;
import java.util.Scanner;

public class Utils {

    static int getValidChoice(int[] validOptions, Scanner scanner) {
        while (true) {
            try {
                System.out.print("Enter your choice: ");
                int input = Integer.parseInt(scanner.next());
                for (int option : validOptions) {
                    if (input == option) {
                        return input;
                    }
                }
                System.out.println("Invalid choice. Please enter one of: " + Arrays.toString(validOptions));
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    public static int[] makeRange(int min, int max) {
        int[] range = new int[max - min + 1];
        for (int i = 0; i < range.length; i++) {
            range[i] = min + i;
        }
        return range;
    }

    public static boolean isValidGridCoord(int row, int col, char[][] grid) {
        return row >= 0 && row < grid.length && col >= 0 && col < grid[0].length;
    }

    public static int[] getGridSize(int difficulty) {
        switch (difficulty) {
            case 1: // Easy
                return new int[]{8, 8};
            case 2: // Medium
                return new int[]{10, 10};
            case 3: // Hard
                return new int[]{12, 12};
            case 4: // Extreme
                return new int[]{15, 15};
            default:
                return new int[]{10, 10}; // fallback
        }
    }

}
