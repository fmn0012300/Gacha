package dataUnitsTests;
import events.dataUnits.Cards;
import events.dataUnits.Deck;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;
public class DeckTest {
    //test Deck class
    /**
     * Test add and remove
     */
    @Test
    public void testAddAndRemove() {
        Deck test1 = new Deck(new HashMap<>());
        Cards card = new Cards("123", 123, "123");
        test1.addCard(card, 10);
        assertEquals(10, (int) (test1.getDeck()).get(card));
        test1.removeCard(card, 5);
        assertEquals(5, (int) (test1.getDeck().get(card)));
    }
}
