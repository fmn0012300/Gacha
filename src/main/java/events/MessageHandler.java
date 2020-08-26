package events;

import events.dataUnits.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;

//This class handles messages from client.
public class MessageHandler {
    private static final double CHANCE = 0.2;
    private static final DataBaseManager DATA = new DataBaseManager();
    private Map<String, Set<Cards>> pool;
    /**
     * draw one times and update database accordingly
     * @param player player's id
     * @return card drawn
     */
    public Cards drawOne(Person player) {
        pool=DATA.getCards();
        Random generator = new Random();
        Object[] values = pool.values().toArray();
        Cards card =(Cards) values[generator.nextInt(values.length)];
        Map<Cards, Integer> map = new HashMap<>();
        map.put(card, 1);
        Deck deck = new Deck(map);
        player.addCards(deck);
        DATA.updatePlayer(player);
        return card;
    }

    /**
     * draw ten times with 1 guaranteed SR
     * @param player player's id
     * @return Array of cards drawn
     */
    public Cards[] drawTen(Person player) {
        pool=DATA.getCards();
        Random generator = new Random();
        Object[] values = pool.values().toArray();
        boolean sr = false;
        Map<Cards, Integer> map = new HashMap<>();
        Cards[] cards = new Cards[10];
        for (int i=0; i<9; i++){
            Cards card =(Cards) values[generator.nextInt(values.length)];
            cards[i]=card;
            if (card.getRarity()!= Constants.RARITY[0]){
                sr=true;
            }
            if (map.containsKey(card)){
                map.put(card, map.get(card)+1);
            }else {
                map.put(card, 1);
            }
        }

        Cards card =(Cards) values[generator.nextInt(values.length)];

        while (!sr){
            card =(Cards) values[generator.nextInt(values.length)];
            if (card.getRarity()!= Constants.RARITY[0]){
                sr=true;
            }
        }
        cards[9]=card;
        if (map.containsKey(card)){
            map.put(card, map.get(card)+1);
        }else {
            map.put(card, 1);
        }

        Deck deck = new Deck(map);
        player.addCards(deck);
        DATA.updatePlayer(player);

        return cards;
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
        return true;
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
