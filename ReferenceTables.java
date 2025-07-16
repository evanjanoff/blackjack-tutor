import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;

// Class to hold all of the reference tables for the program logic
// Seperated out to minimize clutter in other classes
public class ReferenceTables {
    // Map of the cards and their associated point values
    // Card suit is ignored because it isn't relevant for this program
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

    // Method to copy map of cards and their point values
    public static Map<String, Integer> getCardValues() {
        return CARD_VALUES;
    }

    // Get List of cards 
    public static ArrayList<String> getCards() {
        Set<String> keySet = CARD_VALUES.keySet();
        return new ArrayList<>(keySet);
    }

    // Get one random card
    public static String getRandomCard() {
        Random random = new Random();
        ArrayList<String> tmpList = getCards();
        int randomIndex = random.nextInt(tmpList.size());
        return tmpList.get(randomIndex);
    }

    // Table of boolean values for determining when to split cards
    private static boolean[][] splitTable = new boolean[10][10];
    static {
        for (int i = 0; i < 9; i++) {
            int playerIndex = i;
            switch (playerIndex) {
                // Player holds 2 A's
                case 0 -> {
                    for (int dealerIndex = 0; dealerIndex < 10; dealerIndex++) {
                        splitTable[playerIndex][dealerIndex] = true;
                    }
                }
                // Player holds 2 2's
                case 1 -> {
                    for (int dealerIndex = 1; dealerIndex < 7; dealerIndex++) {
                        splitTable[playerIndex][dealerIndex] = true;
                    }
                }
                // Player holds 2 3's
                case 2 -> {
                    for (int dealerIndex = 1; dealerIndex < 7; dealerIndex++) {
                        splitTable[playerIndex][dealerIndex] = true;
                    }
                } 
                // Player holds 2 4's           
                case 3 -> {
                    for (int dealerIndex = 4; dealerIndex < 6; dealerIndex++) {
                        splitTable[playerIndex][dealerIndex] = true;
                    }
                }
                // Player holds 2 5's
                case 4 -> {}
                // Player holds 2 6's
                case 5 -> {
                    for (int dealerIndex = 1; dealerIndex < 6; dealerIndex++) {
                        splitTable[playerIndex][dealerIndex] = true;
                    }
                }
                // Player holds 2 7's  
                case 6 -> {
                    for (int dealerIndex = 1; dealerIndex < 7; dealerIndex++) {
                        splitTable[playerIndex][dealerIndex] = true;
                    }
                }
                // Player holds 2 8's
                case 7 -> {
                    for (int dealerIndex = 0; dealerIndex < 10; dealerIndex++) {
                        splitTable[playerIndex][dealerIndex] = true;
                    }
                }
                // Player holds 2 9's
                case 8 -> {
                    for (int dealerIndex = 1; dealerIndex < 6; dealerIndex++) {
                        splitTable[playerIndex][dealerIndex] = true;
                    }
                    for (int dealerIndex = 7; dealerIndex < 9; dealerIndex++) {
                        splitTable[playerIndex][dealerIndex] = true;
                    }
                }
                // Player holds 2 10's, J's, Q's or K's, default is false
            }
        }
    }

    public static boolean[][] getSplitTable() {
        return splitTable;
    }

    // Table of string values for determining optimal action if the player holds an ace
    private static String[][] softTotals = new String[8][10];
    static {
        for (int i = 0; i < 8; i++) {
            int playerIndex = i;
            switch (playerIndex) {
                // Player holds A, 2 or A, 3
                case 0, 1 -> {
                    for (int dealerIndex = 0; dealerIndex < 4; dealerIndex++) {
                        softTotals[playerIndex][dealerIndex] = "hit";
                    }
                    for (int dealerIndex = 4; dealerIndex < 6; dealerIndex++) {
                        softTotals[playerIndex][dealerIndex] = "double";
                    }
                    for (int dealerIndex = 6; dealerIndex < 10; dealerIndex++) {
                        softTotals[playerIndex][dealerIndex] = "hit";
                    }
                }
                // Player holds A, 4 or A, 5
                case 2, 3 -> {
                    for (int dealerIndex = 0; dealerIndex < 3; dealerIndex++) {
                        softTotals[playerIndex][dealerIndex] = "hit";
                    }
                    for (int dealerIndex = 3; dealerIndex < 6; dealerIndex++) {
                        softTotals[playerIndex][dealerIndex] = "double";
                    }
                    for (int dealerIndex = 6; dealerIndex < 10; dealerIndex++) {
                        softTotals[playerIndex][dealerIndex] = "hit";
                    }
                }
                // Player holds A, 6
                case 4 -> {
                    for (int dealerIndex = 0; dealerIndex < 2; dealerIndex++) {
                        softTotals[playerIndex][dealerIndex] = "hit";
                    }
                    for (int dealerIndex = 2; dealerIndex < 6; dealerIndex++) {
                        softTotals[playerIndex][dealerIndex] = "double";
                    }
                    for (int dealerIndex = 6; dealerIndex < 10; dealerIndex++) {
                        softTotals[playerIndex][dealerIndex] = "hit";
                    }
                }
                // Player holds A, 7
                case 5 -> {
                    for (int dealerIndex = 0; dealerIndex < 1; dealerIndex++) {
                        softTotals[playerIndex][dealerIndex] = "hit";
                    }
                    for (int dealerIndex = 1; dealerIndex < 6; dealerIndex++) {
                        softTotals[playerIndex][dealerIndex] = "double";
                    }
                    for (int dealerIndex = 6; dealerIndex < 8; dealerIndex++) {
                        softTotals[playerIndex][dealerIndex] = "stand";
                    }
                    for (int dealerIndex = 8; dealerIndex < 10; dealerIndex++) {
                        softTotals[playerIndex][dealerIndex] = "hit";
                    }
                }
                // Player holds A, 8
                case 6 -> {
                    for (int dealerIndex = 0; dealerIndex < 5; dealerIndex++) {
                        softTotals[playerIndex][dealerIndex] = "stand";
                    }
                    for (int dealerIndex = 5; dealerIndex < 6; dealerIndex++) {
                        softTotals[playerIndex][dealerIndex] = "double";
                    }
                    for (int dealerIndex = 6; dealerIndex < 10; dealerIndex++) {
                        softTotals[playerIndex][dealerIndex] = "stand";
                    }
                }
                // Player holds A, 9
                case 7 -> {
                    for (int dealerIndex = 0; dealerIndex < 10; dealerIndex++) {
                        softTotals[playerIndex][dealerIndex] = "stand";
                    }
                }
            }
        }
    }

    public static String[][] getSoftTotals() {
        return softTotals;
    }
 
    // Table of string values for determining optimal action if the player does not hold an ace
    private static String[][] hardTotals = new String[8][10];
    static {
        for (int i = 0; i < 8; i++) {
            int playerIndex = i;
            switch (playerIndex) {
                // Player total = 9
                case 0 -> {
                    for (int dealerIndex = 0; dealerIndex < 2; dealerIndex++) {
                        hardTotals[playerIndex][dealerIndex] = "hit";
                    }
                    for (int dealerIndex = 2; dealerIndex < 6; dealerIndex++) {
                        hardTotals[playerIndex][dealerIndex] = "double";
                    }
                    for (int dealerIndex = 6; dealerIndex < 10; dealerIndex++) {
                        hardTotals[playerIndex][dealerIndex] = "hit";
                    }
                }
                // Player total = 10
                case 1 -> {
                    for (int dealerIndex = 0; dealerIndex < 1; dealerIndex++) {
                        hardTotals[playerIndex][dealerIndex] = "hit";
                    }
                    for (int dealerIndex = 1; dealerIndex < 9; dealerIndex++) {
                        hardTotals[playerIndex][dealerIndex] = "double";
                    }
                    for (int dealerIndex = 9; dealerIndex < 10; dealerIndex++) {
                        hardTotals[playerIndex][dealerIndex] = "hit";
                    }
                }
                // Player total = 11
                case 2 -> {
                    for (int dealerIndex = 0; dealerIndex < 10; dealerIndex++) {
                        hardTotals[playerIndex][dealerIndex] = "double";
                    }
                }
                // Player total = 12
                case 3 -> {
                    for (int dealerIndex = 0; dealerIndex < 3; dealerIndex++) {
                        hardTotals[playerIndex][dealerIndex] = "hit";
                    }
                    for (int dealerIndex = 3; dealerIndex < 6; dealerIndex++) {
                        hardTotals[playerIndex][dealerIndex] = "stand";
                    }
                    for (int dealerIndex = 6; dealerIndex < 10; dealerIndex++) {
                        hardTotals[playerIndex][dealerIndex] = "hit";
                    }
                }
                // Player total = 13, 14, 15, 16
                case 4, 5, 6, 7 -> {
                    for (int dealerIndex = 0; dealerIndex < 1; dealerIndex++) {
                        hardTotals[playerIndex][dealerIndex] = "hit";
                    }
                    for (int dealerIndex = 1; dealerIndex < 6; dealerIndex++) {
                        hardTotals[playerIndex][dealerIndex] = "stand";
                    }
                    for (int dealerIndex = 6; dealerIndex < 10; dealerIndex++) {
                        hardTotals[playerIndex][dealerIndex] = "hit";
                    }
                }
            }
        }
    }

    public static String[][] getHardTotals() {
        return hardTotals;
    }
}
