package events;

//this class generates message accordingly
public class MessageGenerator {
    /**
     * Generate a text to tell the result of draws
     * @param result text of result of draws
     * @param success Draw successfully or not
     * @return result
     */
    public static String drawnResult(String id, String result, Boolean success) {
        if (success){
            return result;
        }
        return "<@"+id+"> You don't have enough draws";
    }

    /**
     * text to tell if it's a success trade
     * @param success success or not
     * @return result text
     */
    public static String trade(String id1, String id2, boolean success) {
        if (success){
            return "<@"+id1+"> <@"+id2+"> trade completed.";
        }else {
            return "<@"+id1+"> <@"+id2+"> trade failed, you do not own the card(s) you are trading away.";
        }
    }

    /**
     * prompt the menu of the bot, the functions it has with explaination
     * @return the menu
     */
    public static String menu(String id) {
        String menu = "<@"+id+">\n"+
                "Functions: \n"+
                "!draw x -x can be any positive integer\n"+
                "!single -draw a card\n"+
                "!ten -draw ten cards with a rare or higher card guaranteed\n"+
                "!menu -prompt the functions the bot has\n"+
                "!cards -view the cards you own\n"+
                "!cards rarity -rarity can be any existing rarity, shows all the cards of that rarity\n"+
                "!trade -trading with other players"; //TBD
        return menu;
    }

    /**
     * return warning or error message
     * @return error message string
     */
    public static String warnings(String id) {
        return "<@"+id+"> error occurred, please contact admins for more info.";
    }

    /**
     * text to tell if player is added
     * @param success success or not
     * @return result text
     */
    public static String addPlayer(String id, boolean success) {
        if (success){
            return "<@"+id+"> player added successfully.";
        }
        return "<@"+id+"> player already existed";
    }

}
