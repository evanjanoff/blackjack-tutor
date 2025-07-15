import java.util.ArrayList;
import java.util.Scanner;

public class BlackjackTutor {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Prompt player to select program mode
        // Prompt will repeat until a valid entry is made
        while (true) {
            System.out.println("\nChoose an option");
            System.out.println("1: Analyze single hand\n2: Random hand quiz\n3: Quit");
            System.out.print("Selection: ");
            String selection = scanner.nextLine();

            // Exits the program
            if (selection.equals("3")) {
                break;
            }

            // Prompts the user to enter a starting hand
            // A valid starting hand consists of one dealer card and 2 player cards
            else if (selection.equals("1")) {
                // Variables for dealer and player cards
                String dealerCard;
                ArrayList<String> playerCards = new ArrayList<>();
                // List of valid card entries to check player inputs
                ArrayList<String> validEntries = HandAnalyzer.getCards();
                // Boolean to control the while loops in the logic to come
                Boolean validEntry = false;

                // Lists the valid entries
                System.out.println("\nValid selections: " + String.join(", ", validEntries));

                // Prompt to enter dealer's card until a valid entry is made
                while (validEntry == false) {
                    System.out.print("What card is the dealer showing? ");
                    selection = scanner.nextLine().toUpperCase(); 
                    if (validEntries.contains(selection)) {
                        validEntry = true;
                    } else {
                        System.out.println("Invalid entry.");
                    }
                }
                dealerCard = selection;
                validEntry = false;

                // Prompt the player to enter 2 cards for hand
                while (validEntry == false) {
                    // Prompt player for 1st card, will repeat until valid entry made
                    do {
                        System.out.print("Enter your first card: ");
                        selection = scanner.nextLine().toUpperCase();
                        if (validEntries.contains(selection)) {
                            playerCards.add(selection);
                        } else {
                            System.out.println("Invalid entry.");
                        }
                    }
                    while (!validEntries.contains(selection));
                    // Prompt player for 2nd card, will repeat until valid entry made
                    do {
                        System.out.print("Enter your second card: ");
                        selection = scanner.nextLine().toUpperCase();
                        if (validEntries.contains(selection)) {
                            playerCards.add(selection);
                            validEntry = true;
                        } else {
                            System.out.println("Invalid entry.");
                        }
                    }
                    while (!validEntries.contains(selection));
                }
                Hand thisHand = new Hand(dealerCard, playerCards);
                System.out.println("\nThe dealer has: " + dealerCard + "\nYou have: " + String.join(", ", playerCards));
                System.out.println("You should: " + HandAnalyzer.analyze(thisHand));
            }
        }
        scanner.close();
    }
}
