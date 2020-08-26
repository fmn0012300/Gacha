package events.dataUnits;

/**
 * This class stores all necessary constants that are used
 * for this regarding game play. E.g. rarity, probability, etc..
 */
public class Constants {
    // rarity
    public static final String[] RARITY = {"Normal", "Rare", "Epic", "Unique", "Legendary"};

    // probability stored as an int array,
    // the probability of position n is (the value of n minus the value of n-1)/1000
    // SUM OF ALL PROBABILITY = 1000
    public static final int[] PROBABILITY = {500, 850, 950, 999, 1000};

    // path that cards' pictures are stored at
    // to use it append /(rarity)/(name).jpg to get the card
    public static final String PHOTO_PATH = "data/cards/photo";

    // path that players' data is stored at
    // access a particular player's data, append /(playerID)/(thing to look for)
    public static final String USER_DATA_PATH = "";

}
