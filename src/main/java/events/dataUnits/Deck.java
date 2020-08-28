package events.dataUnits;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

//
/**
 * <b>Deck</b> is a immutable data structure that stores cards and their amount
 *
 * <p>Specification fields:
 * @spec.specfield deck: Set of Cards mapping to their number
 * </p>
 *
 * <p>Abstract Invariant:
 * Cards must map to an integer > 0. Anything < 0 will be removed
 * </p>
 */
public class Deck {
    //fields and class constant
    private Map<Cards, Integer> deck;

    /**
     * Creates a new deck with the given cards and their number
     * @param deck a given cards mapping to their number
     */
    public Deck(Map<Cards, Integer> deck) {
        for (Cards card: deck.keySet()) {
            if (deck.get(card) > 0) {
                deck.put(card, deck.get(card));
            }
        }
    }

    /**
     * Creates a new empty deck
     */
    public Deck() {deck = new HashMap<>(); }

    /**
     * Copy constructor
     */
    public Deck(Deck other) {deck = new HashMap<>(other.deck); }

    /**
     * Get a copy of the deck
     * @return a copy of this
     */
    public Map<Cards, Integer> getDeck() {
        return new HashMap<>(deck);
    }

    /**
     * Add a card to the deck with given number
     * @param card card to be added
     * @param num how many of the card to be added
     * @throws IllegalArgumentException iff num <= 0
     * @spec.requires card != null
     * @spec.modifies deck
     * @spec.effects If deck = A, deck_post = A U {card->num}
     */
    public void addCard(Cards card, int num) {
        if (num<=0){
            throw new IllegalArgumentException();
        }
        if (deck.containsKey(card)){
            deck.put(card, deck.get(card)+num);
        }else {
            deck.put(card, num);
        }

    }

    /**
     * Remove a card(s) from the deck
     * @param card the card type to be removed
     * @param num number of the card to be removed
     * @throws IllegalArgumentException iff num <= 0 and num > deck.get(card)
     * @spec.requires card != null
     * @spec.modifies deck
     * @spec.effects If deck = A, deck_post = A / {card->num}
     */
    public void removeCard(Cards card, int num) {
        if (num<=0){
            throw new IllegalArgumentException();
        }
        if (!deck.containsKey(card)){
            throw new IllegalArgumentException();
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {return true;}
        if (!(o instanceof Deck)) {return false;}
        Deck deck1 = (Deck) o;
        return deck.equals(deck1.deck);
    }

    @Override
    public String toString(){
        String result="";
        for (Map.Entry<Cards, Integer> entry: deck.entrySet()){
            result+=entry.getKey().getName()+" x"+entry.getValue()+"\n";
        }
        return result;
    }

    @Override
    public int hashCode() {
        return Objects.hash(deck);
    }
}
