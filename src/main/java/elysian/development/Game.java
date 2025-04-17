package elysian.development;

import java.util.Scanner;

public class Game {

    Scanner userInput = new Scanner(System.in);
    static int selectedDifficulty = 0;
    static int selectedClass = 0;

    public static void StartMenu(){
        Scanner userInput = new Scanner(System.in);
        System.out.println("-=-=-=BATTLESHIPS-=-=-=");
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=");
        System.out.println("-=-=-=SELECT A DIFFICULTY-=-=-=");
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=");
        System.out.println("-=-=-=TYPE 1 FOR EASY-=-=-=");
        System.out.println("-=-=-=TYPE 2 FOR MEDIUM-=-=-=");
        System.out.println("-=-=-=TYPE 3 FOR HARD-=-=-=");
        System.out.println("-=-=-=TYPE 4 FOR EXTREME-=-=-=");
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=");
        userInput.next();

        selectedDifficulty = Utils.getValidChoice(new int[]{1, 2, 3, 4}, userInput);

        System.out.println("-=-=-=SELECT A CLASS-=-=-=");
        System.out.println("-=-=-=** CLASSES OFFER DIFFERENT SHIPS OR DIFFERENT FEATURES SUCH AS SMALLER BOATS **-=-=-=");
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=");
        System.out.println("-=-=-=TYPE 1 FOR STEALTH BOATS-=-=-=");
        System.out.println("-=-=-=TYPE 2 FOR HEAVY DUTY BOATS-=-=-=");
        System.out.println("-=-=-=TYPE 3 FOR FIGHTER-=-=-=");
        System.out.println("-=-=-=TYPE 4 FOR AIR DEFENSE-=-=-=");
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=");
        userInput.next();

        selectedClass = Utils.getValidChoice(new int[]{1, 2, 3, 4}, userInput);

        System.out.println("-=-=-=LOADING GAME-=-=-=");

        StartGame(selectedDifficulty, selectedClass);


    }
}
