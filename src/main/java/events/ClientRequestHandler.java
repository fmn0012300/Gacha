package events;

import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

//entrance class, react to all client requests
//handles client requests and call respective functions
//preliminary checks on user input
public class ClientRequestHandler extends ListenerAdapter {
    private EventExecutioner handler;

    //handle requests, i.e. switch statements
    public ClientRequestHandler() {
        super();
        handler = new EventExecutioner();
    }

    /**
     * Handle commands requested
     * @param event event to be handled
     */
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        String command = event.getMessage().getContentRaw();

        if (!command.startsWith("!")) {  // check on msg, see if it's a command to the bot
            return;
        }
        User user = event.getAuthor();
        String[] breakup = command.split(" ");
        String statement;  // for return statement
        handler.registerPlayer(user.getId());
        switch (breakup[0]) {
            case "!draw":
                try {
                    int num = Integer.parseInt(breakup[1]);
                    if (num <= 0) {
                        throw new NumberFormatException();
                    }
                    statement = handler.draw(user.getId(), num);
                } catch (NumberFormatException e) {
                    statement = "!draw (A POSITIVE INTEGER)";
                }
                break;
            case "!single" :
                statement = handler.draw1(user.getId());
                break;
            case "!ten" :
                statement = handler.draw10(user.getId());
                break;
            default:
                statement = handler.view();
                break;
        }

        // send msg back
        event.getChannel().sendMessage(statement).queue();
    }
}
