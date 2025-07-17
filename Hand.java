import java.util.ArrayList;

public class Hand {
    private String dealerCard;
    private ArrayList<String> playerCards;

    // Constructor to generate random hand
    public Hand() {
        dealerCard = ReferenceTables.getRandomCard();
        playerCards = new ArrayList<>();
        playerCards.add(ReferenceTables.getRandomCard());
        playerCards.add(ReferenceTables.getRandomCard());
    }

    // Constructor for user defined hand
    public Hand(String dealerCard, ArrayList<String> playerCards) {
        this.dealerCard = dealerCard;
        this.playerCards = playerCards;
    }

    public String getDealerCard() {
        return dealerCard;
    }

    public ArrayList<String> getPlayerCards() {
        return playerCards;
    }

}
