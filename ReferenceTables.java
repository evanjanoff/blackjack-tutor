import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

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

    // Method to copy map of cards
    public static Map<String, Integer> getCardValues() {
        return CARD_VALUES;
    }

    // Table of boolean values for determining when to split cards
    private static boolean[][] splitTable = new boolean[10][10];
    static {
        for (int i = 0; i < 9; i++) {
            int playerIndex = i;
            switch (playerIndex) {
                case 0 -> {
                    for (int dealerIndex = 0; dealerIndex < 10; dealerIndex++) {
                        splitTable[playerIndex][dealerIndex] = true;
                    }
                }
                case 1 -> {
                    for (int dealerIndex = 1; dealerIndex < 7; dealerIndex++) {
                        splitTable[playerIndex][dealerIndex] = true;
                    }
                }
                case 2 -> {
                    for (int dealerIndex = 1; dealerIndex < 7; dealerIndex++) {
                        splitTable[playerIndex][dealerIndex] = true;
                    }
                }            
                case 3 -> {
                    for (int dealerIndex = 4; dealerIndex < 6; dealerIndex++) {
                        splitTable[playerIndex][dealerIndex] = true;
                    }
                }
                case 4 -> {}
                case 5 -> {
                    for (int dealerIndex = 1; dealerIndex < 6; dealerIndex++) {
                        splitTable[playerIndex][dealerIndex] = true;
                    }
                }  
                case 6 -> {
                    for (int dealerIndex = 1; dealerIndex < 7; dealerIndex++) {
                        splitTable[playerIndex][dealerIndex] = true;
                    }
                }
                case 7 -> {
                    for (int dealerIndex = 0; dealerIndex < 10; dealerIndex++) {
                        splitTable[playerIndex][dealerIndex] = true;
                    }
                }
                case 8 -> {
                    for (int dealerIndex = 1; dealerIndex < 6; dealerIndex++) {
                        splitTable[playerIndex][dealerIndex] = true;
                    }
                    for (int dealerIndex = 7; dealerIndex < 9; dealerIndex++) {
                        splitTable[playerIndex][dealerIndex] = true;
                    }
                }
            }
        }
    }

    public static boolean[][] getSplitTable() {
        return splitTable;
    }
 
}
