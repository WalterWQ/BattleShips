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

    public static boolean isValidGridCoord(int row, int col, char[][] grid) {
        return row >= 0 && row < grid.length && col >= 0 && col < grid[0].length;
    }
}
