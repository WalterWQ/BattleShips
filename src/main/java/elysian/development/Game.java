package elysian.development;

import jdk.jshell.execution.Util;

import java.util.List;
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

        userInput.close();


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
            playerTurn(userInput, enemyBoard, playerClass, playerBoard);

            if (allShipsSunk(enemyBoard, 'E')) {
                System.out.println("YOU WIN!");
                break;
            }

            System.out.println("AI TURN");
            aiTurn(playerBoard, enemyBoard);

            if (allShipsSunk(playerBoard, 'S')) {
                System.out.println("AI WIN!");
                break;
            }
        }
    }

    public static void playerTurn(Scanner userInput, Board targetBoard, PlayerClass playerClass, Board attackerBoard) {

        List<Weapons> weapons = playerClass.getWeapons();

        System.out.println("Choose A weapon!");
        for (int i = 0; i < weapons.size(); i++) {
            System.out.println((i + 1) + "; " + weapons.get(i));
        }

        int selectedWeapon = Utils.getValidChoice(Utils.makeRange(1, weapons.size()), userInput);
        Weapons selected = weapons.get(selectedWeapon - 1);

        System.out.println("Enter attack row: ");
        int row = Utils.getValidChoice(Utils.makeRange(1, attackerBoard.getHeight()), userInput) - 1;
        System.out.println("Enter attack column: ");
        int col = Utils.getValidChoice(Utils.makeRange(1, attackerBoard.getWidth()), userInput) - 1;

        useWeapon(targetBoard, attackerBoard, selected, row, col);

        if (selected.isRapidFire()) {
            System.out.print("Firing second shot! Enter row: ");
            int r2 = userInput.nextInt() - 1;
            System.out.print("Enter column: ");
            int c2 = userInput.nextInt() - 1;
            useWeapon(targetBoard, attackerBoard, selected, r2, c2);
        }

        if (targetBoard.getGrid()[row][col] == 'E') {
            System.out.println("HIT!");
            targetBoard.getGrid()[row][col] = 'X';
            attackerBoard.getGrid()[row][col] = 'X';
        } else {
            System.out.println("MISS!");
            targetBoard.getGrid()[row][col] = 'O';
            attackerBoard.getGrid()[row][col] = 'O';
            if (selected.isRapidFire()) {
                System.out.print("Firing second shot! Enter row: ");
                int r2 = userInput.nextInt() - 1;
                System.out.print("Enter column: ");
                int c2 = userInput.nextInt() - 1;
                useWeapon(targetBoard,attackerBoard, selected, r2, c2);
            }
        }
    }

    public static void aiTurn(Board targetBoard, Board attackerBoard) {
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
            attackerBoard.getGrid()[row][col] = 'X';
        } else {
            System.out.println("MISS!");
            targetBoard.getGrid()[row][col] = 'O';
            attackerBoard.getGrid()[row][col] = 'O';
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

    public static void useWeapon(Board board, Board attacker, Weapons weapon, int row, int col) {
        int radius = weapon.getRadius();

        for (int r = row - radius; r <= row + radius; r++) {
            for (int c = col - radius; c <= col + radius; c++) {
                if (r >= 0 && r < board.getHeight() && c >= 0 && c < board.getWidth()) {
                    int distance = Math.abs(r - row) + Math.abs(c - col);
                    if (distance <= radius) {
                        char tile = board.getGrid()[r][c];

                        if (tile == 'E') {
                            board.getGrid()[r][c] = 'X';
                            attacker.getGrid()[r][c] = 'X';
                            System.out.println("Hit at (" + (r + 1) + "," + (c + 1) + ")");
                        } else if (tile == '~') {
                            board.getGrid()[r][c] = 'O';
                            attacker.getGrid()[r][c] = 'O';

                        }
                    }
                }
            }
        }

        if (weapon.getName().equalsIgnoreCase("Sonar")) {
            System.out.println("📡 Scanning area:");
            for (int r = row - radius; r <= row + radius; r++) {
                for (int c = col - radius; c <= col + radius; c++) {
                    if (r >= 0 && r < board.getHeight() && c >= 0 && c < board.getWidth()) {
                        char tile = board.getGrid()[r][c];
                        if (tile == 'E') {
                            System.out.println("Enemy detected at (" + (r + 1) + "," + (c + 1) + ")");
                        }
                    }
                }
            }
        }
    }
}
