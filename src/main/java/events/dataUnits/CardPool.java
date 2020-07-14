package events.dataUnits;

import java.util.Map;
import java.util.Set;

/**
 * <b>CardPool</b> is a immutable class which stores and arranges cards based on their
 * rarity
 *
 * <p>Each rarity will have a pool of cards associated to it</p>
 *
 * <p>Specification fields:
 * @spec.specfield rarity: the rarity of a card. //normal, rare, epic, unique, legendary
 * @spec.specfield cards: a pool of cards associated to each rarity.
 * </p>
 *
 * <p>Abstract Invariant:
 * No one can adjust the pool other than changing the data base.
 * </p>
 */
public class CardPool {
    Map<String, Set<Cards>> pool;

    //Representation Invariant:
    // Each card should only be present in one rarity.
    // i.e. All values in pool.get(rarity1) != Any values in pool.get(rarity2)
    //      for any rarity1 != rarity2

    //Abstraction Functions
    // pool = pool of cards divided by rarity
    // pool.(rarity) will have all cards of the same rarity

    /**
     * @spec.effects Construct a new CardPool
     */
    public CardPool() { //directly created from database

    }

    /**
     * get random card of the rarity given
     *
     * @param rarity rarity of the card to be returned
     * @return a card of the rarity
     * @spec.throws IllegalArgumentException if rarity is not defined
     * @spec.requires rarity != null
     */
    public Cards getCard(String rarity) {return null; }

    /**
     * Get all cards of a particular rarity
     *
     * @param rarity rarity of the cards to be returned
     * @return all cards of the rarity
     * @spec.throws IllegalArgumentException if rarity is not defined
     * @spec.requires rarity != null
     */
    public Set<Cards> getRarity(String rarity) {return null; }

    /**
     * Get a specific card with name given
     *
     * @param name name of the card to be returned
     * @return Card with the name given, null if no such card exists
     * @spec.requires name != null
     */
    public Cards getSpecificCard(String name) {return null; }
}
