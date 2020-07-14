package events.dataUnits;

import org.jetbrains.annotations.NotNull;

import java.util.*;

/**
 * <b>Person</b> is a mutable class that keeps track of a player's status while playing
 */
public class Person implements Comparable<Person> {
    Deck owned; //number of each unique cards that he owns
    private int id; //player id
    private int draws; //number of draws left for today
    private int priority;

    //Representation Invariant:
    //  draws must always be >= 0
    //  priority must always >= 0
    //  owned can be empty but not null
    //  id != 0

    //for testing only
    public Person(int priority) {}

    /**
     * @spec.effect create a new Person with given id and priority (draw out other data from database)
     * @param priority relative to compare how recent he played
     * @param id id of the player
     */
    public Person(int priority, int id) {

    }

    /**
     * Get a copy of all cards they own
     * @return a copy of his deck
     */
    public Deck getOwned() {
        return null;
    }

    /**
     * Get their id
     * @return id of the person
     */
    public int getId() {
        return id;
    }

    /**
     * Get the number of draws he left today
     * @return number of draws left
     */
    public int getDraws() {
        return draws;
    }

    /**
     * Get the person's priority
     * @return their priority
     */
    public int getPriority() {return priority; }

    /**
     * add (a) card(s) to his deck
     * @param deck deck to be added
     * @return true if added correctly, false otherwise
     * @throws IllegalArgumentException if deck == null
     * @spec.modifies this
     * @spec.effects this.deck_post = this.deck_pre U {deck}
     */
    public boolean addCards(Deck deck) {
        return false;
    } // change to deck

    /**
     * remove a card(s) of a type from his deck
     * @param deck deck to be added
     * @return true if removed successfully, false if there's no enough cards to be removed (deck will not be changed)
     * @throws IllegalArgumentException if deck == null
     * @spec.modifies this
     * @spec.effects this.deck_post = this.deck_pre / {deck}
     */
    public boolean removeCards(Deck deck) {
        return false;
    }

    /**
     * update number of draws he left
     * @param drawn number of cards drawn
     * @throws IllegalArgumentException if drawn <= 0
     * @spec.modifies this
     * @spec.effects this.draw_post = this.draw_pre - drawn
     */
    public void drawn(int drawn) {

    }

    /**
     * update database for this player
     * @return true if updated successfully, false if any database related problem occurred
     */
    public boolean updateDataBase() {
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Person person = (Person) o;
        return id == person.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public int compareTo(@NotNull Person person) {
        return this.priority - person.priority;
    }
}
