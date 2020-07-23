package dataUnitsTests;
import events.dataUnits.Cards;
import events.dataUnits.Deck;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;
public class DeckTest {
    //test Deck class
    /**
     * Test get and remove
     */
    @Test
    public void testAddAndRemove() {
        Deck test1 = new Deck();
        Cards card = new Cards(123, "123", "rare");
        test1.addCard(card, 10);
        assertEquals(10, (int) (test1.getDeck()).get(card));
        test1.removeCard(card, 5);
        assertEquals(5, (int) (test1.getDeck().get(card)));
    }

    /**
     * Test addAndRemove throws
     */
    @Test(expected = IllegalArgumentException.class)
    public void testExcepAdd() {
        Deck test1 = new Deck();
        Cards card = new Cards(123, "123", "rare");
        test1.addCard(card, 0);
        test1.addCard(card, -1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testExcepRemove() {
        Deck test1 = new Deck();
        Cards card = new Cards(123, "123", "rare");
        test1.addCard(card, 10);
        test1.removeCard(card, 12);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testExcepRemove1() {
        Deck test1 = new Deck();
        Cards card = new Cards(123, "123", "rare");
        test1.addCard(card, 10);
        test1.removeCard(card, -1);
    }

    /**
     * Test getDeck
     */
    @Test
    public void testGetDeck() {
        Deck test1 = new Deck();
        Map<Cards, Integer> deck = test1.getDeck();
        assertEquals(0, deck.size());
        deck.put(null, 1);
        assertNotEquals(test1.getDeck(), deck);
    }
}
