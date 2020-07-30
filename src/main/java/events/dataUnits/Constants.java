package events.dataUnits;

/**
 * This class stores all necessary constants that are used
 * for this regarding game play. E.g. rarity, probability, etc..
 */
public class Constants {
    // rarity
    public static final String[] RARITY = {"Normal", "Rare", "Epic", "Unique", "Legendary"};

    // probability stored as an int array,
    // let's say if a prob = n -> prob of getting that rarity = n / 1000
    // RARITY[i] has a PROBABILITY[i] to get
    // SUM OF ALL PROBABILITY = 1000
    public static final int[] PROBABILITY = {1000, 0, 0, 0, 0};

    // path that cards' pictures are stored at
    // to use it append /(rarity)/(name).jpg to get the card
    public static final String PHOTO_PATH = "data/cards/photo";

    // path that players' data is stored at
    // access a particular player's data, append /(playerID)/(thing to look for)
    public static final String USER_DATA_PATH = "";

}
