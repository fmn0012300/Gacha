package events.dataUnits;

import java.util.Map;
import java.util.Set;

//collections of all cards present in data base
public class CardPool {
    Map<String, Set<Cards>> pool; //rank -> Cards, split cards according to their rarity

    public CardPool() { //directly created from database

    }

    /**
     * get random card of the rarity given
     * @param rarity rarity of the card to be returned
     * @return a card of the rarity
     */
    public Cards getCard(String rarity) {return null; }
}
