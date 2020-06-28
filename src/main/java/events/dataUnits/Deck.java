package events.dataUnits;

import java.util.Map;

//this is a mutable class represents a deck of cards
public class Deck {
    private Map<Cards, Integer> deck;

    public Deck(Map<Cards, Integer> deck) {
        this.deck = deck;
    }

    public Map<Cards, Integer> getDeck() {
        return deck;
    }

    public void addCard(Cards card, int num) {

    }

    public void removeCard(Cards card, int num) {

    }
}
