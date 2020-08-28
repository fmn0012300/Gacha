package events;

import events.dataUnits.*;


/**
 * This class handles requests that are translated to function understandable
 * terms. Second layer to ClientRequestHandler.
 * After requests are registered by ClientRequestHandler, this will be called to
 * update, load and read DataBase, player base, return necessary statements, etc
 */
public class EventExecutioner{

    private PlayerCurr players;

    /**
     * Initialize card pool based on data base
     * Open up the player cache
     */
    public EventExecutioner() {

        players = new PlayerCurr();
    }

    /**
     * Register a player into cache
     * @param id id of the player, (discord id) get from library
     * @return statement that tells if it's completed successfully
     *         (use message generator class)
     */
    public String registerPlayer(String id) {
        return MessageGenerator.addPlayer(id, players.addPlayer(id.hashCode()));
    }

    /**
     * Draw the number of cards for player requested
     * if cost > credit they currently have, draw the maximum possible
     * number
     * @param id id of player (discord id)
     * @param num number of cards to be drawn
     * @return someway to send all the cards drawn TBD
     */
    public String draw(String id, int num) {
        boolean success=true;
        if (players.getDraw(id.hashCode(), num)){
            success=false;
        }
        String result = players.draw(id.hashCode(), num);
        return MessageGenerator.drawnResult(id, result, success);
    }

    /**
     * Draw one card
     * @param id discord id of the player
     * @return TBD
     */
    public String draw1(String id) {
        return draw(id, 1);
    }

    /**
     * Draw ten cards at once
     * @param id id discord id of the player
     * @return TBD
     */
    public String draw10(String id) {
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
    @SuppressWarnings("checkstyle:LineLength")
    public String trade(String id1, String id2, String card1, String card2, int onesAmount, int twosAmount) {
        return MessageGenerator.trade(id1, id2, players.trade(id1.hashCode(), id2.hashCode(), card1, card2, onesAmount, twosAmount));
    }

    /**
     * Show all cards of the rarity the player currently has
     * If rarity = "all", show all cards they own
     * @param id player discord id
     * @param rarity rarity of the cards to be shown
     * @return someway to display the cards, tbd
     */
    public String show(String id, String rarity) {
        boolean r = false;
        if (!rarity.equals("all")){
            for (int i=0; i<Constants.RARITY.length; i++){
                if (rarity.equals(Constants.RARITY[i])) {
                    r = true;
                    break;
                }
            }
        }else {
            r = true;
        }

        if (!r){
            return "rarity not defined";
        }
        return players.show(id.hashCode(), rarity);
    }

    /**
     *
     * @return the functions the bot has
     */
    public String menu(String id){
        return MessageGenerator.menu(id);
    }

    /**
     * Get the picture of the card with the given name
     * if the player doesnt own the card, return an error msg
     * @param id player discord id
     * @param name name of the card
     * @return TBD
     */
    public String getCard(String id, String name) {
        return players.getCard(id.hashCode(), name);
    }

    /**
     * Save all data in playerCurr to database
     */
    public void save() {
        players.save();
    }
}
