import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class HandAnalyzer {
    // Map of the cards and their associated point values
    // Card suit is ignored because it isn't relevant
    private static final Map<String, Integer> CARD_VALUES;

    static {
        Map<String, Integer> tempMap = new LinkedHashMap<>();
        tempMap.put("A", 1);
        tempMap.put("2", 2);
        tempMap.put("3", 3);
        tempMap.put("4", 4);
        tempMap.put("5", 5);
        tempMap.put("6", 6);
        tempMap.put("7", 7);
        tempMap.put("8", 8);
        tempMap.put("9", 9);
        tempMap.put("10", 10);
        tempMap.put("J", 10);
        tempMap.put("Q", 10);
        tempMap.put("K", 10);
        CARD_VALUES = Collections.unmodifiableMap(tempMap);
    }

    // Get List of cards 
    public static ArrayList<String> getKeys() {
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
        // Table of boolean elements for when to split pair, all elements are 'false' by default
        // Table will be accessed by converting card values to match indices of table
        boolean[][] splitTable = new boolean[10][10];
        // Convert relevant elements to 'true'

        for (int row = 0; row < splitTable.length; row++) {
            for (int col = 0; col < splitTable[row].length; col++) {
                System.out.print(splitTable[row][col] + "\t"); // Print with tab spacing
            }
            System.out.println();
        }
        return false;
    }
}
