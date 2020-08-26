package dataUnitsTests;
import events.dataUnits.CardPool;
import events.dataUnits.Cards;
import events.dataUnits.Constants;
import org.junit.Test;

import static org.junit.Assert.*;
/**
 * This class tests implementation of Cardpool class
 */
public class CardPoolTests {
    /**
     * Test constructor
     */
    @Test //important
    public void testConstructor() {
        CardPool test = new CardPool();
        assertNotNull(test);
    }

    /**
     * Test get card method, see if the data is loaded properly
     */
    @Test
    public void testGetCard() {
        String rarity = Constants.RARITY[0];
        CardPool test = new CardPool();
        Cards card = test.getCard(rarity);
        assertTrue(card.getRarity().equals(rarity));
    }

    /**
     * Test get card throws is correct
     */
    @Test(expected = IllegalArgumentException.class)
    public void testGetCardException() {
        String rarity = "";
        CardPool test = new CardPool();
        test.getCard(rarity);
    }

    /**
     * Test get card if null is passed
     */
    @Test(expected = IllegalArgumentException.class)
    public void testGetNull() {
        CardPool test = new CardPool();
        test.getRarity(null);
    }

    /**
     * Test getRarity general
     */
    @Test
    public void testGetRarity() {
        CardPool test = new CardPool();
        String rarity = "";
        assertNotNull(test.getRarity(rarity));
        assertNotEquals(0, test.getRarity(rarity));
    }

    /**
     * Test if exception thrown correctly
     */
    @Test(expected = IllegalArgumentException.class)
    public void testException() {
        CardPool test = new CardPool();
        test.getRarity("");
    }

    /**
     * Test if get specific card work as expected
     */
    @Test
    public void testGetSpecCard() {
        CardPool test = new CardPool();
        assertNull(test.getSpecificCard(""));
        String name = "TBD";
        assertEquals(name, test.getSpecificCard(name).getName());
    }
}
