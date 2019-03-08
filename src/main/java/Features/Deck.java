package Features;

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

    public int totalScore() {
        int s = 0;
        for (Card card : deck) {
            s = s + card.getValue();
        }
        return s;
    }

    public String toString() {
        String string = "cards:" + "\n";
        for (Card card : deck) {
            string = string + card.getName() + "\n";
        }
        return string = string + "\n" + "total points: " + totalScore() + "\n";


    }

}
