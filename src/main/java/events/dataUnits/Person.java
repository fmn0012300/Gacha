package events.dataUnits;

import org.jetbrains.annotations.NotNull;

import java.util.*;

//this class keeps track of a person/player status while active (accessed recently)
public class Person implements Comparable<Person> {
    Deck owned; //number of each unique cards that he owns
    private int id; //player id
    private int draws; //number of draws left for today
    private int priority;

    public Person(int priority) { //call data base to retrieve data
        this.priority = priority;
    }

    //adding in new player
    public Person(int priority, int id) {

    }

    public Deck getOwned() {
        return null;
    }

    public int getId() {
        return id;
    }

    public int getDraws() {
        return draws;
    }

    /**
     * add (a) card(s) to his deck
     * @param card card to be added
     * @param num number of cards he got of this type
     * @return true if added correctly
     */
    public boolean addCards(Cards card, int num) {
        return false;
    }

    /**
     * remove a card(s) of a type from his deck
     * @param card card to be removed
     * @param num number of cards to be removed
     * @return true if removed successfully
     */
    public boolean removeCards(Cards card, int num) {
        return false;
    }

    /**
     * update number of draws he left
     * @param drawn number of cards drawn
     */
    public void drawn(int drawn) {

    }

    /**
     * update database for this player
     * @return true if updated successfully
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
