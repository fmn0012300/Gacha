package events.dataUnits;

import java.util.Objects;

//an immutable class represent a card, directly created from database
public class Cards {
    private int id;
    private String name;
    private String addr;

    public Cards(String addr, int id, String name) {
        this.addr = addr;
        this.id = id;
        this.name = name;
    }

    //get fields
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddr() {
        return addr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cards)) return false;
        Cards cards = (Cards) o;
        return id == cards.id &&
                name.equals(cards.name) &&
                addr.equals(cards.addr);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, addr);
    }
}
