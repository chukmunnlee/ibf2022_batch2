package cards;

import java.security.SecureRandom;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Deck {

    private List<Card> deck;

    public Deck() {
        this.deck = new LinkedList<>();
        for (int i = 0; i < Constants.SUITS.length; i++) {
            String suit = Constants.SUITS[i];
            for (int j = 0; j < Constants.VALUES.length; j++) {
                Integer value = Constants.VALUES[j];
                String name = Constants.NAMES[j];
                Card card = new Card(suit, value, name);
                deck.add(card);
            }
        }
    }

    public void shuffle() {

        Random rand = new SecureRandom();

        for (int curr = 0; curr < deck.size(); curr++) {
            int toSwap = rand.nextInt(deck.size());
            Card c = deck.get(curr);
            Card d = deck.get(toSwap);
            deck.set(curr, d);
            deck.set(toSwap, c);
        }
    }

    @Override
    public String toString() {
        return "Deck [deck=" + deck + "]";
    }
    
}
