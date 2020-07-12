package events.dataUnits;

import java.util.Map;
import java.util.PriorityQueue;

//Act as a cache to store curr players
//Max 20 players at a time before kicking the least recently played
public class PlayerCurr {
    private PriorityQueue<Person> players;

    public PlayerCurr(PriorityQueue<Person> players) {
        this.players = players;
    }

    //check number of players registered right now
    //return true if it reaches max capacity
    private boolean checkPlayerNumber() {
        return players.size()>20;
    }

    //remove the least recently played player and update his data in database
    private void remove() {
        players.poll();
    }

    /**
     * Add a player into the cache
     * @param player player to be added
     * @return true iff he is added successfully
     */
    public boolean addPlayer(Person player) {
        players.add(player);
        return true;
    }

    /**
     * trade between 2 players
     * @param one person 1
     * @param two person 2
     * @param ones person 1's cards
     * @param twos person 2's cards
     * @return true if trade is successful
     */
    public boolean trade(Person one, Person two, Deck ones, Deck twos) {
        for (Map.Entry<Cards, Integer> entry: twos.getDeck().entrySet()){
            one.owned.addCard(entry.getKey(), entry.getValue());
        }
        for (Map.Entry<Cards, Integer> entry: ones.getDeck().entrySet()){
            two.owned.addCard(entry.getKey(), entry.getValue());
        }
        return true;
    }
}
