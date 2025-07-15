import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

public class HandAnalyzer {
    // Copy card value map from reference
    private static final Map<String, Integer> CARD_VALUES = ReferenceTables.getCardValues();
    // Copy table for splitting cards from reference
    private static final boolean[][] SPLIT_TABLE = ReferenceTables.getSplitTable();

    // Get List of cards 
    public static ArrayList<String> getCards() {
        Set<String> keySet = CARD_VALUES.keySet();
        return new ArrayList<>(keySet);
    }

    public static String analyze(Hand toAnalyze) {
        // Get the value of the dealer's up card
        String dealerCard = toAnalyze.getDealerCard();
        int dealerCardValue = CARD_VALUES.get(dealerCard);
        // Get the total value of the player's cards
        ArrayList<String> playerCards = toAnalyze.getPlayerCards();
        int playerCardTotal = 0;
        for (String card: playerCards) {
            playerCardTotal += CARD_VALUES.get(card);
        }

        // Surrender and split are actions that can only happen when the player has 2 cards
        // Check that the player has 2 cards
        if (playerCards.size() == 2) {
            // The first decision is if the player should surrender
            // The player should not surrender if they hold a pair of the same card
            // Check that the player does not hold a pair
            if (!playerCards.get(0).equals(playerCards.get(1))) {
                // Method to decide if player should surrender
                if (checkForSurrender(dealerCardValue, playerCardTotal))
                    return "surrender";
            }
            // This code block will only run if the player holds a pair
            else {
                // Splitting is to seperate a pair into 2 seperate hands
                // Method to decide if the player should split
                if (checkForSplit(dealerCardValue, playerCardTotal)) {
                    return "split";
                }
            }
        }

        return "something";
    }

    // Checks card values and returns true if the player should surrender
    public static boolean checkForSurrender(int dealerCardValue, int playerCardTotal) {
        if (playerCardTotal == 15 && dealerCardValue == 10) {
                return true;
            }
        else if (playerCardTotal == 16 &&
            (dealerCardValue == 1 || dealerCardValue == 9 || dealerCardValue == 10)) {
            return true;
        } else {
            return false;
        }
    }

    // Checks card values and returns true if the player should split
    public static boolean checkForSplit(int dealerCardValue, int playerCardTotal) {
        // Convert point values to related index on the SPLIT_TABLE
        int playerTotalAsIndex = playerCardTotal / 2 - 1;
        int dealerCardAsIndex = dealerCardValue - 1;

        // DEBUG: print split table
        for (int row = 0; row < SPLIT_TABLE.length; row++) {
            for (int col = 0; col < SPLIT_TABLE[row].length; col++) {
                System.out.print(SPLIT_TABLE[row][col] + "\t"); // Print with tab spacing
            }
            System.out.println();
        }
        return SPLIT_TABLE[playerTotalAsIndex][dealerCardAsIndex];
    }
}
