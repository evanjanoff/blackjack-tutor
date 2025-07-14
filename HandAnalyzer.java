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
        // Surrender is limited to the player's first action, ie the player will have 2 cards
        // Check that the player has 2 cards and the values recommend surrender
        if (playerCards.size() == 2) {
            if (playerCardTotal == 15 && dealerCardValue == 10) {
                return "surrender";
            }
            if (playerCardTotal == 16 && !playerCards.get(0).equals(playerCards.get(1)) &&
                (dealerCardValue == 1 || dealerCardValue == 9 || dealerCardValue == 10)) {
                return "surrender";
            }
        }

        // Splitting is limited to the player's first action and requires a pair of the same card
        // Check that the player has 2 cards, they are the same
        if (playerCards.size() == 2 && playerCards.get(0).equals(playerCards.get(1))) {
            if (playerCardTotal == 2 || playerCardTotal == 16) {
                return "split";
            }
        }

        return "something";
    }
}
