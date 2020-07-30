package events;

import events.dataUnits.CardPool;
import events.dataUnits.PlayerCurr;

/**
 * This class handles requests that are translated to function understandable
 * terms. Second layer to ClientRequestHandler.
 * After requests are registered by ClientRequestHandler, this will be called to
 * update, load and read DataBase, player base, return necessary statements, etc
 */
public class EventExecutioner{
    private CardPool pool;
    private PlayerCurr players;

    /**
     * Initialize card pool based on data base
     * Open up the player cache
     */
    public EventExecutioner() {
        pool = new CardPool();
        players = new PlayerCurr();
    }

    /**
     * Register a player into cache
     * @param id id of the player, (discord id) get from library
     * @return statement that tells if it's completed successfully
     *         (use message generator class)
     */
    public String registerPlayer(int id) {
        return null;
    }

    /**
     * Draw the number of cards for player requested
     * if cost > credit they currently have, draw the maximum possible
     * number
     * @param id id of player (discord id)
     * @param num number of cards to be drawn
     * @return someway to send all the cards drawn TBD
     */
    public String draw(int id, int num) {
        return null;
    }

    /**
     * Draw one card
     * @param id discord id of the player
     * @return TBD
     */
    public String draw1(int id) {
        return draw(id, 1);
    }

    /**
     * Draw ten cards at once
     * @param id id discord id of the player
     * @return TBD
     */
    public String draw10(int id) {
        return draw(id, 10);
    }

    /**
     * Exchange cards between two players
     * @param id1 player1's id
     * @param id2 player2's id
     * @param card1 card1's name
     * @param card2 card2's name
     * @return statement to tell success
     */
    public String trade(int id1, int id2, String card1, String card2) {
        return null;
    }

    /**
     * Show all cards of the rarity the player currently has
     * If rarity = "all", show all cards they own
     * @param id player discord id
     * @param rarity rarity of the cards to be shown
     * @return someway to display the cards, tbd
     */
    public String show(int id, String rarity) {
        return null;
    }

    /**
     * Get the picture of the card with the given name
     * if the player doesnt own the card, return an error msg
     * @param id player discord id
     * @param name name of the card
     * @return TBD
     */
    public String getCard(int id, String name) {
        return null;
    }
}
