package events.dataUnits;

import java.util.Map;
import java.util.NoSuchElementException;

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
        if (deck.containsKey(card)){
            deck.put(card, deck.get(card)+num);
        }else {
            deck.put(card, num);
        }
    }

    public void removeCard(Cards card, int num) {
        if (!deck.containsKey(card)){
            throw new NoSuchElementException();
        }
        int value=deck.get(card)-num;
        if (value<0){
            throw new IllegalArgumentException();
        }else if (value==0){
            deck.remove(card);
        }else {
            deck.put(card, value);
        }
    }
}
