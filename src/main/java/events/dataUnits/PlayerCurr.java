package events.dataUnits;

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
        return false;
    }

    //remove the least recently played player and update his data in database
    private void remove() {
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
        return false;
    }
}
