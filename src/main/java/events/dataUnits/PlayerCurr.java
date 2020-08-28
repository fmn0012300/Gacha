package events.dataUnits;

import events.DataBaseManager;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Random;
//
/**
 * <b>PlayerCurr</b> is a mutable data structure that acts like a cache storing
 * data of at most N number of players (most recently played)
 *
 * <p>Specification fields
 * @spec.specfield: Queue: a queue having the least recently played player at the front
 * ready to be removed
 * </p>
 *
 * <p>Abstract Invariant:
 *   Will always keep the most recently played player
 * </p>
 */
public class PlayerCurr {
    private static final int N = 20;
    private PriorityQueue<Person> recentPlayers;
    private Map<Integer, Person> players;
    private int priority;
    private DataBaseManager conn;
    private CardPool cp;
    //Representation Invariant:
    //  players.size() == recentPlayers.size() for all time
    //  recentPlayers.pop() must be the most recently played player
    //  players and recentPlayers must have exactly the same content but order may be different

    //Abstraction Functions
    //  players = all players stored in the cache
    //  recentPlayers keeps track of the most recently player player




    /**
     * @spec.effects Constructs a new PlayerCurr cache
     */
    public PlayerCurr() {
        recentPlayers= new PriorityQueue<>();
        players = new HashMap<>();
        priority = 0;
        this.conn = new DataBaseManager();
        cp = new CardPool(conn.getCards());
    }

    //check number of players registered right now
    //return true if it reaches max capacity
    private boolean checkPlayerNumber() {
        return players.size() > N;
    }

    //remove the least recently played player from cache
    //and update their data in database, if they are new, add in a new tuple
    private void remove() {
        Person toBeRemoved = recentPlayers.remove();
        players.remove(toBeRemoved.getId());
        conn.updatePlayer(toBeRemoved);
    }

    /**
     * Add a player into the cache
     * @param playerId player id to be added
     * @return true iff they are added successfully
     *         false if they are already in the list
     * @spec.requires player != null
     * @spec.modifies Queue
     * @spec.effects if Queue = A, Queue_post = A U {playerId}
     */
    public boolean addPlayer(int playerId) {
        if (checkPlayerNumber()) {
            remove();
        }
        if (players.containsKey(playerId)) {
            return false;
        }
        // check if player exists
        if (!conn.ifPlayerExists(playerId)) {
            conn.createPlayer(playerId);
        }
        Person player = conn.getPlayer(playerId, priority);
        priority++;
        recentPlayers.add(player);
        players.put(playerId, player);
        return true;
    }

    /**
     * Load Person 1 and 2 into the queue (if they are not) and trade
     * the cards given
     *
     * @param one person 1 id
     * @param two person 2 id
     * @param ones person 1's cards
     * @param twos person 2's cards
     * @param onesAmount amount of ones
     * @param twosAmount number of twos
     * @return true if trade is successful, false if either one or two does not
     *         have the cards being traded
     * @spec.requires one, two, ones, twos != null
     * @spec.modifies Queue
     * @spec.effects 1. If 1 and 2 are not in the cache, they will be added
     *               2. If 1 and 2 are in the cache, they will be pushed to the top
     *                  of the Queue
     *               Person(one).deck and Person(two).deck will be updated accordingly
     */
    public boolean trade(int one, int two, String ones, String twos, int onesAmount, int twosAmount) {
        Deck first = new Deck();
        Deck second = new Deck();
        first.addCard(cp.getSpecificCard(ones), onesAmount);
        second.addCard(cp.getSpecificCard(twos), twosAmount);
        Person a = this.get(one);
        Person b = this.get(two);
        if (!a.removeCards(first)) {
            return false;
        }
        if (!b.removeCards(second)) {
            a.addCards(first);
            return false;
        }
        a.addCards(second);
        b.addCards(first);
        return true;
    }

    /**
     * Get the picture of the card with the given name
     * if the player doesnt own the card, return an error msg
     * @param id player discord id
     * @param name name of the card
     * @return the address of card (Change to picture smh)
     */
    public String getCard(int id, String name){
        if (!players.get(id).getOwned().getDeck().containsKey(cp.getSpecificCard(name))){
            return "You don't own this card";
        }
        return cp.getSpecificCard(name).getAddr();
    }

    /**
     * show the cards
     * @param id player discord id
     * @param rarity rarity of cards to be shown
     * @return display the card
     */
    public String show(int id, String rarity){
        return players.get(id).show(rarity);
    }

    /**
     * check if the person has enough draw left
     * @param id player's discord id
     * @param num number of draws requested
     * @return whether or not enough of draws
     */
    public boolean getDraw(int id, int num){
        return num<=players.get(id).getDraws();
    }
    /**
     * draw the number of cards player requested
     * @param id person's id
     * @param num number of cards draw
     * @return result of draws
     * @spec.require id, num != null
     * @spec.modifies person's number of draw left
     * @spec.effects add cards to person's deck
     */
    public String draw(int id, int num){
        String rarity = "";
        int numProb = Constants.PROBABILITY.length;
        boolean sr = false;
        Map<Cards, Integer> map = new HashMap<>();
        Random generator = new Random();
        int prob;
        for (int i=0; i<num; i++) {
            if (num == 9 && !sr) {
                prob = generator.nextInt(Constants.PROBABILITY[0]) + Constants.PROBABILITY[numProb - 1]
                        - Constants.PROBABILITY[0];
            } else {
                prob = generator.nextInt(Constants.PROBABILITY[numProb - 1]);
            }
            for (int j = 0; j < numProb; j++) {
                if (prob < Constants.PROBABILITY[j]) {
                    rarity = Constants.RARITY[j];
                    break;
                }
            }
            if (!rarity.equals(Constants.RARITY[0])) {
                sr = true;
            }
            Cards card = cp.getCard(rarity);
            if (map.containsKey(card)){
                map.put(card, map.get(card)+1);
            }else {
                map.put(card, 1);
            }
        }
        Deck deck = new Deck(map);
        players.get(id).addCards(deck);
        players.get(id).drawn(num);
        return deck.toString();
    }

    /**
     * Get the Person with the given player id
     * @param playerId player id of the person requested
     * @return Person with the player id
     * @spec.modifes Queue
     * @spec.effects 1. If the player id does not appear in Queue it will be added
     *               2. Queue will have playerId to be the most recently played
     */
    public Person get(int playerId) {
        if (!players.containsKey(playerId)) {
            this.addPlayer(playerId);
        }
        return players.get(playerId);
    }

    /**
     * Save all data it's holding to database
     */
    public void save() {
        for (int id : players.keySet()) {
            Person save = players.get(id);
            conn.updatePlayer(save);
        }
    }
}
