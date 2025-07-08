import java.util.ArrayList;

public class Hand {
    private String dealerCard;
    private ArrayList<String> playerCards;

    public Hand(String dealerCard, ArrayList<String> playerCards) {
        this.dealerCard = dealerCard;
        this.playerCards = playerCards;
    }
}
