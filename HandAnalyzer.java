import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

public class HandAnalyzer {
    // Copy card value map from reference
    private static final Map<String, Integer> CARD_VALUES = ReferenceTables.getCardValues();
    // Copy table for splitting cards from reference
    private static final boolean[][] SPLIT_TABLE = ReferenceTables.getSplitTable();
    // Copy table for totals when player holds an A
    private static final String[][] SOFT_TOTALS = ReferenceTables.getSoftTotals();
    // Copy table for other totals
    private static final String[][] HARD_TOTALS = ReferenceTables.getHardTotals();

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
        // Check so this block will only run if the player has exactly 2 cards
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

        // Remaining logic depends on if the player has an ace in their hand
        // Check for ace
        if (toAnalyze.getPlayerCards().contains("A")) {
            return checkSoftTotal(dealerCardValue, playerCardTotal);
        }

        // If none of the previous conditions are met, this section will run
        if (playerCardTotal >= 17) {
            return "stand";
        } 
        else if (playerCardTotal <= 8) {
            return "hit";
        }
        else {
            return checkHardTotal(dealerCardValue, playerCardTotal);
        }
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

        return SPLIT_TABLE[playerTotalAsIndex][dealerCardAsIndex];
    }

    public static String checkSoftTotal(int dealerCardValue, int playerCardTotal) {
        // Convert point values to related index on the SOFT_TOTALS table
        int playerTotalAsIndex = playerCardTotal - 3;
        int dealerCardAsIndex = dealerCardValue - 1;

        return SOFT_TOTALS[playerTotalAsIndex][dealerCardAsIndex];
    }

    public static String checkHardTotal(int dealerCardValue, int playerCardTotal) {
        // Convert point values to related index on the HARD_TOTALS table
        int playerTotalAsIndex = playerCardTotal - 9;
        int dealerCardAsIndex = dealerCardValue - 1;

        return HARD_TOTALS[playerTotalAsIndex][dealerCardAsIndex];
    }
}
