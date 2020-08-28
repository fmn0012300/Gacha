package events.dataUnits;

import events.DataBaseManager;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
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
