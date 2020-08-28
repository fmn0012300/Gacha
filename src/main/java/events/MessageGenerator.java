package events;

import events.dataUnits.Deck;

//this class generates message accordingly
public class MessageGenerator {
    /**
     * Generate a text to tell the result of draws
     * @param deck the deck
     * @return result
     */
    public static String drawnResult(Deck deck) {
        return deck.toString();
    }

    /**
     * text to tell if it's a success trade
     * @param success success or not
     * @return result text
     */
    public static String trade(boolean success) {
        if (success){
            return "trade completed.";
        }else {
            return "trade failed, you do not own the card(s) you are trading away.";
        }
    }

    /**
     * prompt for view, to get card's photo
     * @return the prompt
     */
    public static String view() {
        return null;
    }

    /**
     * return warning or error message
     * @return error message string
     */
    public static String warnings() {
        return "error occurred, please contact admins for more info.";
    }

    /**
     * text to tell if player is added
     * @param success success or not
     * @return result text
     */
    public static String addPlayer(boolean success) {
        if (success){
            return "player added successfully.";
        }
        return MessageGenerator.warnings();
    }
}
