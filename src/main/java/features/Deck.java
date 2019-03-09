package features;

import java.util.ArrayList;

public class Deck {
    private ArrayList<Card> deck;

    public Deck() {
        deck = new ArrayList<Card>();
    }

    public void add(Card card) {
        deck.add(card);
    }

    public void remove(Card card) {
        deck.remove(card);
    }

    /**
     * Sums the total score of the deck.
     * @return the card score.
     */
    public int totalScore() {
        int score = 0;
        for (Card card : deck) {
            score = score + card.getValue();
        }
        return score;
    }

    /**
     * Create string representation.
     * @return the string representation of the deck.
     */
    public String toString() {
        String string = "cards:" + "\n";
        for (Card card : deck) {
            string = string + card.getName() + "\n";
        }
        return string = string + "\n" + "total points: " + totalScore() + "\n";


    }

}
