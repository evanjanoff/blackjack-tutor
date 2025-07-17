import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class BlackjackTutor {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Prompt player to select program mode
        // Prompt will repeat until a valid entry is made
        while (true) {
            System.out.println("\nChoose an option");
            System.out.println("\t1: Analyze single hand\n\t2: Random hand quiz\n\t3: Quit");
            System.out.print("Selection: ");
            String selection = scanner.nextLine();

            // Exits the program
            if (selection.equals("3")) {
                break;
            }

            // Prompts the user to enter a starting hand
            // A valid starting hand consists of one dealer card and 2 player cards
            if (selection.equals("1")) {
                // Variables for dealer and player cards
                String dealerCard;
                ArrayList<String> playerCards = new ArrayList<>();
                // List of valid card entries to check player inputs
                ArrayList<String> validEntries = ReferenceTables.getCards();
                // Boolean to control the while loops in the logic to come
                Boolean validEntry = false;

                // Lists the valid entries
                System.out.println("\nValid selections: \n\t" + String.join(", ", validEntries));

                // Prompt to enter dealer's card until a valid entry is made
                while (!validEntry) {
                    System.out.print("\nWhat card is the dealer showing? ");
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
                while (!validEntry) {
                    // Prompt player for 1st card, will repeat until valid entry made
                    do {
                        System.out.print("What is your 1st card? ");
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
                        System.out.print("What is your 2nd card? ");
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

                // Initialize new Hand oblject and display optimal play choice
                Hand thisHand = new Hand(dealerCard, playerCards);
                System.out.println("\nThe dealer has: " + dealerCard + "\nYou have: " + String.join(", ", playerCards));
                if (HandAnalyzer.analyze(thisHand).equals("Blackjack")) {
                    System.out.println("21! You win!");
                } else {
                    System.out.println("You should: " + HandAnalyzer.analyze(thisHand));
                }
                
            }

            // Generate a random hand and quiz the player on best play
            if (selection.equals("2")) {
                String newHand = "y";
                
                do {
                    // Array containing the possible moves for the game
                    String[] gameMoves = {"surrender", "split", "double", "hit", "stand"};

                    //Generate random hand
                    Hand randomHand = new Hand();

                    // Check if the player made 21, if yes, display win message and generate new hand
                    if (HandAnalyzer.analyze(randomHand).equals("Blackjack")) {
                        System.out.println("\nThe dealer has: " + randomHand.getDealerCard());
                        System.out.println("You have: " + String.join(", ", randomHand.getPlayerCards()));
                        System.out.println("21! You win");
                        randomHand = new Hand();
                    }
                    
                    // Code below will repeat until valid entry made
                    boolean validEntry = false;
                    do {
                        // Display the cards of the dealer and the player
                        System.out.println("\nThe dealer has: " + randomHand.getDealerCard());
                        System.out.println("You have: " + String.join(", ", randomHand.getPlayerCards()));
                        System.out.println("\nWhat should you do?");

                        // Print each play option with the 1st letter capitalised
                        for (int i = 0; i < gameMoves.length; i++) {
                            System.out.println("\t" + (i + 1) + ": " + gameMoves[i].substring(0, 1).toUpperCase() + gameMoves[i].substring(1));
                        }

                        // Prompt for player's selection
                        // Player can enter choice by selecting menu number or typing out command
                        System.out.print("Selection: ");
                        selection = scanner.nextLine().toLowerCase();

                        // Check to see if the player entered a valid integer and display results
                        try{
                            int menuNumber = Integer.parseInt(selection);
                            if (Integer.valueOf(menuNumber) >= 1 && Integer.valueOf(menuNumber) <= 5) {
                                System.out.println("\nYou chose: " + gameMoves[menuNumber - 1].substring(0, 1).toUpperCase() + 
                                gameMoves[menuNumber - 1].substring(1) + "\nOptimal play: " + HandAnalyzer.analyze(randomHand));
                                validEntry = true;
                            } else {
                                System.out.println("Invalid entry");
                            }

                        // If the player entered a string instead, check if it's a valid command and display results
                        } catch (NumberFormatException e) {
                            if (Arrays.asList(gameMoves).contains(selection)) {
                                System.out.println("\nYou chose: " + selection.substring(0, 1).toUpperCase() + selection.substring(1) +
                                "\nOptimal play: " + HandAnalyzer.analyze(randomHand));
                                validEntry = true;
                            } else {
                                System.out.println("Invalid entry");
                            }
                        }
                    } while (!validEntry);

                    // Reset validEntry boolean for next loop
                    validEntry = false;
                    while (!validEntry) {
                        // Prompt for another hand and verify input
                        System.out.print("\nAnother hand? Y/N: ");
                        newHand = scanner.nextLine().toLowerCase();
                        if (newHand.equals("y") || newHand.equals("n")) {
                            validEntry = true;
                        }
                        else {
                            System.out.println("Invalid entry");
                        }
                    }
                } while (newHand.equals("y"));
            }
        }
        scanner.close();
    }
}
