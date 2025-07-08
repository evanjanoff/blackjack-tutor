import java.util.ArrayList;
import java.util.Scanner;

public class BlackjackTutor {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Prompt player to select program mode
        // Prompt will repeat until a valid entry is made
        while (true) {
            System.out.println("Select program mode: ");
            System.out.println("1: Analyze single hand");
            System.out.println("2: Random hand quiz");
            System.out.println("3: Quit");
            String selection = scanner.nextLine();

            // Exits the program
            if (selection.equals("3")) {
                break;
            }
            // Prompts the user to enter a starting hand
            // A valid starting hanc consists of one dealer card and 2 player cards
            else if (selection.equals("1")) {
                String dealerCard;
                ArrayList<String> playerCards = new ArrayList<>();
                Boolean validEntry = false;

                // Lists the valid entries, program only needs to consider the card value, not suit
                System.out.println("");
                System.out.println("Valid entries: A, 2, 3, 4, 5, 6, 7, 8, 9, 10, J, Q, K");

                // Prompt to enter dealer's card until a valid entry is made
                while (validEntry == false) {
                    System.out.print("What card is the dealer showing? ");
                    selection = scanner.nextLine().toUpperCase(); 
                    if (AnalyzePlay.getKeys().contains(selection)) {
                        validEntry = true;
                    } else {
                        System.out.println("Invalid entry.");
                    }
                }
                dealerCard = selection;
                validEntry = false;

                // Prompt the player to enter 2 cards for hand
                while (validEntry == false) {
                    do {
                        System.out.print("Enter your first card: ");
                        selection = scanner.nextLine().toUpperCase();
                        if (AnalyzePlay.getKeys().contains(selection)) {
                            playerCards.add(selection);
                        } else {
                            System.out.println("Invalid entry.");
                        }
                    }
                    while (!AnalyzePlay.getKeys().contains(selection));
                    do {
                        System.out.print("Enter your second card: ");
                        selection = scanner.nextLine().toUpperCase();
                        if (AnalyzePlay.getKeys().contains(selection)) {
                            playerCards.add(selection);
                            validEntry = true;
                        } else {
                            System.out.println("Invalid entry.");
                        }
                    }
                    while (!AnalyzePlay.getKeys().contains(selection));
                }
                Hand newHand = new Hand(dealerCard, playerCards);
            }
        }
        scanner.close();
    }
}
