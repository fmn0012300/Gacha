package dataUnitsTests;
import events.dataUnits.Cards;
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
        Person test1 = new Person(0, 123456);
        Cards card = new Cards("23", 123, "abc");
        assertTrue(test1.addCards(card, 10));
        assertFalse(test1.addCards(null, 0));
        assertFalse(test1.addCards(card, -1));
    }

    /**
     * Test removeCards
     */
    @Test
    public void testRemoveCards() {
        Person test1 = new Person(0, 123456);
        Cards card = new Cards("23", 123, "abc");
        assertFalse(test1.removeCards(card, 10));
        test1.addCards(card, 10);
        assertTrue(test1.removeCards(card, 5));
        assertFalse(test1.removeCards(card, 10));
        assertFalse(test1.removeCards(null, 10));
        assertFalse(test1.removeCards(card, -1));
    }

    /**
     * Test drawn
     */
    @Test
    public void testDrawn() {
        Person test1 = new Person(0, 123456);
        int draw = test1.getDraws();
        test1.drawn(10);
        assertEquals(draw - 10, test1.getDraws());
    }
}
