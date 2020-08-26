package events.dataUnits;

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
    }

    //check number of players registered right now
    //return true if it reaches max capacity
    private boolean checkPlayerNumber() {
        return players.size()>N;
    }

    //remove the least recently played player from cache
    //and update their data in database, if they are new, add in a new tuple
    private void remove() {
        Person newest = recentPlayers.peek();
        PriorityQueue<Person> current = recentPlayers;
        current.poll();
        Person remove = current.poll();
        players.remove(remove.getId());
        recentPlayers=current;
        recentPlayers.add(newest);

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
        if (players.containsKey(playerId)){
            return false;
        }
        if (checkPlayerNumber()){
            remove();
        }
        players.put(playerId, new Person(0, playerId));
        recentPlayers.add(new Person(0, playerId));
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
     * @return true if trade is successful, false if either one or two does not
     *         have the cards being traded
     * @spec.requires one, two, ones, twos != null
     * @spec.modifies Queue
     * @spec.effects 1. If 1 and 2 are not in the cache, they will be added
     *               2. If 1 and 2 are in the cache, they will be pushed to the top
     *                  of the Queue
     *               Person(one).deck and Person(two).deck will be updated accordingly
     */
    public boolean trade(int one, int two, Deck ones, Deck twos) {
        if (!recentPlayers.contains(get(one))){
            addPlayer(one);
        }
        if (!recentPlayers.contains(get(two))){
            addPlayer(two);
        }
        for (Map.Entry<Cards, Integer> entry: twos.getDeck().entrySet()){
            players.get(one).getOwned().addCard(entry.getKey(), entry.getValue());
            players.get(two).getOwned().removeCard(entry.getKey(), entry.getValue());
        }
        for (Map.Entry<Cards, Integer> entry: ones.getDeck().entrySet()){
            players.get(two).getOwned().addCard(entry.getKey(), entry.getValue());
            players.get(one).getOwned().removeCard(entry.getKey(), entry.getValue());
        }
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
        return players.get(playerId);
    }
}
