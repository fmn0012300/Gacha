package dataUnitsTests;

import events.dataUnits.Cards;
import events.dataUnits.Deck;
import events.dataUnits.Person;
import events.dataUnits.PlayerCurr;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PlayerCurrTests {
    // test PlayerCurr class
    /**
     * Test trade
     */
    @Test
    public void testTrade() {
        Person p1 = new Person(1, 123);
        Person p2 = new Person(2, 1234);
        Person[] people = new Person[1];
        people[0] = p1;
        PlayerCurr test = new PlayerCurr(people);

        assertTrue(test.addPlayer(p2));
        assertFalse(test.addPlayer(p1));

        assertFalse(test.trade(p1, p2, null, null));

        Cards card1 = new Cards("123", 123, "123");
        Cards card2 = new Cards("456", 456, "456");

        Deck deck1 = new Deck();
        deck1.addCard(card1, 10);
        p1.addCards(deck1);
        Deck deck2 = new Deck();
        deck2.addCard(card2, 10);
        p2.addCards(deck2);

        assertTrue(test.trade(p1, p2, deck1, deck2));
        
    }
}
