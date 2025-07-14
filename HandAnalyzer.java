import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class HandAnalyzer {
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

    public static Set<String> getKeys() {
        return CARD_VALUES.keySet();
    }
}
