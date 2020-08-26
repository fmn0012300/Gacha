package dataunitstests;

import events.dataUnits.Cards;
import events.dataUnits.Deck;
import events.dataUnits.PlayerCurr;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerCurrTests {
    // test PlayerCurr class
    /**
     * Test addPlayer
     */
    @Test
    public void testAdd() {
        PlayerCurr test = new PlayerCurr();
        assertTrue(test.addPlayer(1234));
        assertFalse(test.addPlayer(1234));
    }

    /**
     * Test get Person
     */
    @Test
    public void testGet() {
        PlayerCurr test = new PlayerCurr();
        test.addPlayer(0);
        assertEquals(0, test.get(0).getId());
    }

    /**
     * Test trade (To be perfected when database is implemented
     */
    @Test
    public void testTrade() {
        PlayerCurr test = new PlayerCurr();
        test.addPlayer(0);
        test.addPlayer(1);
        Deck deck1 = new Deck();
        deck1.addCard(new Cards(123, "123", "rare"), 10);
        Deck deck2 = new Deck();
        // assertTrue(test.trade(0, 1, deck1, deck2));
        assertEquals(0,
                (int) test.get(0).getOwned().getDeck().get(new Cards(123, "123", "rare")));
        assertEquals(10,
                (int) test.get(1).getOwned().getDeck().get(new Cards(123, "123", "rare")));
    }
}
