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

        if (true) {  // check on msg, see if it's a command to the bot
            return;
        }
        User user = event.getAuthor();
        String[] breakup = command.split(" ");
        String statement;  // for return statement
        switch (breakup[0]) {
            case "!draw":
                handler.draw1();
                break;
            case "2":
                //smth
                break;
                // so on and so forth
            default:
                // default behavior
                break;
        }

        // send msg back
        // event.getChannel().sendMessage(statement).queue();
    }
}
