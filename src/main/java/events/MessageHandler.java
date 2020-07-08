package events;

import events.dataUnits.Cards;
import events.dataUnits.Person;

import java.util.Map;

//This class handles messages from client.
public class MessageHandler {
    private static final double CHANCE = 0.2;

    /**
     * draw one times and update database accordingly
     * @param player player's id
     * @return card drawn
     */
    public Cards drawOne(Person player) {
        return null;
    }

    /**
     * draw ten times with 1 guaranteed SR
     * @param player player's id
     * @return Array of cards drawn
     */
    public Cards[] drawTen(Person player) {
        return null;
    }

    /**
     * trade cards between players
     * @param player1 player 1 id
     * @param player2 player 2 id
     * @param card1 player 1's card's id (can be nothing (0))
     * @param card2 player 2's card's id (can be nothing (0))
     * @return true if the trade is successful
     */
    public boolean trade(Person player1, Person player2, Cards card1, Cards card2) {
        return false;
    }

    /**
     * show all cards that the player owns
     * @param player player's id
     * @return A map with card's id as key and number of the card owned as value
     */
    public Map<Cards, Integer> view(Person player) {
        return null;
    }

    //more?

}
