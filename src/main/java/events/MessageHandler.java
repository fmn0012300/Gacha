package events;

import java.util.Map;

//This class handles messages from client.
public class MessageHandler {
    private static final double CHANCE = 0.2;

    /**
     * draw one times and update database accordingly
     * @param id player's id
     * @return id of the card drawn
     */
    public int drawOne(int id) {
        return 0;
    }

    /**
     * draw ten times with 1 guaranteed SR
     * @param id player's id
     * @return id of the cards drawn
     */
    public int[] drawTen(int id) {
        return null;
    }

    /**
     * trade cards between players
     * @param id1 player 1 id
     * @param id2 player 2 id
     * @param cardId1 player 1's card's id (can be nothing (0))
     * @param cardId2 player 2's card's id (can be nothing (0))
     * @return true if the trade is successful
     */
    public boolean trade(int id1, int id2, int cardId1, int cardId2) {
        return false;
    }

    /**
     * show all cards that the player owns
     * @param id player's id
     * @return A map with card's id as key and number of the card owned as value
     */
    public Map<String, Integer> view(int id) {
        return null;
    }

    //more?

}
