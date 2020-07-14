package events.dataUnits;

import java.util.Objects;

/**
 * <b>Cards</b> is an immutable class that store all the details of a card
 */
public class Cards {
    private int id;
    private String name;

    public Cards(int id, String name) {
        this.id = id;
        this.name = name;
    }

    //get fields

    /**
     * get the id of the card
     * @return id of the card
     */
    public int getId() {
        return id;
    }

    /**
     * get the name of the card
     * @return name of the card
     */
    public String getName() {
        return name;
    }

    /**
     * Get the address of card's photo stored
     * @return the address of the card's photo
     */
    public String getAddr() {
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cards)) return false;
        Cards cards = (Cards) o;
        return id == cards.id &&
                name.equals(cards.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
