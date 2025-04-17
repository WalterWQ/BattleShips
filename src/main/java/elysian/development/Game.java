package elysian.development;

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

        int[] gridSize = Utils.getGridSize(selectedDifficulty);
        System.out.println("Grid Size: " + gridSize[0] + "x" + gridSize[1]);

        Board playerBoard = new Board(selectedDifficulty);
        Board enemyBoard = new Board(selectedDifficulty);

        PlayerClass playerClass = new PlayerClass(selectedClass);

        playerBoard.generateBoard(selectedDifficulty);
        enemyBoard.generateBoard(selectedDifficulty);

        Ships.placePlayerFleet(userInput, playerBoard);
        Ships.placeAIFleet(enemyBoard, selectedDifficulty);


        startGame(playerBoard, enemyBoard, playerClass);


    }

    public static void startGame(Board playerBoard, Board enemyBoard, PlayerClass playerClass) {
        playerBoard.printBoard(); // Optional, for sanity

    }
}
