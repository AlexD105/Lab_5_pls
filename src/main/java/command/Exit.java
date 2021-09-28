package command;


import i18n.Messenger;
import ticket.Ticket;

import java.util.ArrayList;

public class Exit extends Command {

    public Exit(ArrayList<Ticket> tickets, Messenger messenger) {
        super(tickets, messenger);
    }

    @Override
    public String execute(String ignore) {
        System.exit(0);
        return null;
    }

    @Override
    public String toString() {
        return super.toString() + ": " + getMessenger().getMessage("infoExit");
    }
}
