package elysian.development;

import java.util.Random;
import java.util.Scanner;

public class Game {

    Scanner userInput = new Scanner(System.in);
    static int selectedDifficulty = 0;
    static int selectedClass = 0;

    public static void StartMenu(){
        Scanner userInput = new Scanner(System.in);
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=");
        System.out.println("-=-=-=BATTLESHIPS-=-=-=");
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=");
        System.out.println("-=-=-=SELECT A DIFFICULTY-=-=-=");
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=");
        System.out.println("-=-=-=TYPE 1 FOR EASY-=-=-=");
        System.out.println("-=-=-=TYPE 2 FOR MEDIUM-=-=-=");
        System.out.println("-=-=-=TYPE 3 FOR HARD-=-=-=");
        System.out.println("-=-=-=TYPE 4 FOR EXTREME-=-=-=");
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=");

        selectedDifficulty = Utils.getValidChoice(new int[]{1, 2, 3, 4}, userInput);

        System.out.println("-=-=-=SELECT A CLASS-=-=-=");
        System.out.println("-=-=-=** CLASSES OFFER DIFFERENT SHIPS OR DIFFERENT FEATURES SUCH AS SMALLER BOATS **-=-=-=");
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=");
        System.out.println("-=-=-=TYPE 1 FOR STEALTH BOATS-=-=-=");
        System.out.println("-=-=-=TYPE 2 FOR HEAVY DUTY BOATS-=-=-=");
        System.out.println("-=-=-=TYPE 3 FOR FIGHTER-=-=-=");
        System.out.println("-=-=-=TYPE 4 FOR AIR DEFENSE-=-=-=");
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=");

        selectedClass = Utils.getValidChoice(new int[]{1, 2, 3, 4}, userInput);

        System.out.println("-=-=-=LOADING GAME-=-=-=");

        // Generate boards based on difficulty
        Board playerBoard = new Board(selectedDifficulty);
        Board enemyBoard = new Board(selectedDifficulty);

        // Create player class
        PlayerClass playerClass = new PlayerClass(selectedClass);

        // Place ships using class-defined fleets and validation
        Ships.placePlayerFleet(userInput, playerBoard, playerClass);
        Ships.placeAIFleet(enemyBoard, selectedDifficulty);

        // Start game
        startGame(playerBoard, enemyBoard, playerClass);


    }

    public static void startGame(Board playerBoard, Board enemyBoard, PlayerClass playerClass) {
        playerBoard.printBoard(); // Optional, for sanity
        fight(playerBoard, enemyBoard, playerClass);


    }

    public static void fight(Board playerBoard, Board enemyBoard, PlayerClass playerClass ) {
       Scanner userInput = new Scanner(System.in);
       boolean gameOver = false;

        while (!gameOver) {
            System.out.println("\nPLAYER TURN");
            playerBoard.printBoard();
            playerTurn(userInput, enemyBoard, playerClass);

            if (allShipsSunk(enemyBoard, 'E')) {
                System.out.println("YOU WIN!");
                break;
            }

            System.out.println("AI TURN");
            aiTurn(playerBoard);

            if (allShipsSunk(playerBoard, 'S')) {
                System.out.println("AI WIN!");
                break;
            }
        }
    }

    public static void playerTurn(Scanner userInput, Board targetBoard, PlayerClass playerClass) {

        System.out.println("Enter attack row: ");
        int row = userInput.nextInt() - 1;
        System.out.println("Enter attack column: ");
        int col = userInput.nextInt() - 1;

        if (targetBoard.getGrid()[row][col] == 'E') {
            System.out.println("HIT!");
            targetBoard.getGrid()[row][col] = 'X';
        } else {
            System.out.println("MISS!");
            targetBoard.getGrid()[row][col] = 'O';
        }
    }

    public static void aiTurn(Board targetBoard) {
        Random rand = new Random();
        int row, col;

        do {
            row = rand.nextInt(targetBoard.getHeight());
            col = rand.nextInt(targetBoard.getWidth());
        } while (targetBoard.getGrid()[row][col] == 'X' || targetBoard.getGrid()[row][col] == 'O');

        System.out.println("AI attacked row " + (row + 1) + ", column " + (col+1));

        if (targetBoard.getGrid()[row][col] == 'S') {
            System.out.println("HIT!");
            targetBoard.getGrid()[row][col] = 'X';
        } else {
            System.out.println("MISS!");
            targetBoard.getGrid()[row][col] = 'O';
        }
    }

    public static boolean allShipsSunk(Board board, char symbol) {
        for(char[] row : board.getGrid()) {
            for (char tile : row) {
                if (tile == symbol) return false;
            }
        }
        return true;
    }
}
