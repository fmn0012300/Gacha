package dataunitstests;
import events.dataUnits.Cards;
import events.dataUnits.Deck;
import events.dataUnits.Person;
import org.junit.Test;

import static org.junit.Assert.*;


public class PersonTests {
    // This class tests Person class
    /**
     * Test constructors and gets
     */
    @Test
    public void testGetsAndConstructor() {
        Person test1 = new Person(0, 123456);
        assertEquals(0, test1.getPriority());
        assertEquals(123456, test1.getId());
        //leave space for future test on getOwned
    }

    /**
     * Test addCards function
     */
    @Test
    public void testAddCards() {
        Person test1 = new Person(0);
        Cards card = new Cards(23, "123", "abc");
        Deck deck = new Deck();
        deck.addCard(card, 10);
        assertTrue(test1.addCards(deck));
        assertEquals(deck, test1.getOwned());
    }

    /**
     * Test removeCards
     */
    @Test
    public void testRemoveCards() {
        Person test1 = new Person(0);
        Cards card = new Cards(23, "123", "abc");
        Deck deck = new Deck();
        deck.addCard(card, 10);
        assertFalse(test1.removeCards(deck));
        test1.addCards(deck);
        assertTrue(test1.removeCards(deck));
        assertFalse(test1.removeCards(deck));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testExcepRemove() {
        Person test = new Person(0);
        test.removeCards(null);
    }

    /**
     * Test drawn
     */
    @Test
    public void testDrawn() {
        Person test1 = new Person(0);
        int draw = test1.getDraws();
        test1.drawn(10);
        assertEquals(draw - 10, test1.getDraws());
    }
}
