package elysian.development;

import java.util.Scanner;

public class Game {

    Scanner userInput = new Scanner(System.in);

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

        Utils.validationChecker(userInput);

    }
}
