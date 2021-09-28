package command;


import i18n.Messenger;
import ticket.Ticket;

import java.util.ArrayList;

public class Show extends Command {

    public Show(ArrayList<Ticket> tickets, Messenger messenger) {
        super(tickets, messenger);
    }

    @Override
    public String execute(String ignore) {
        return getFormatter().formatCollection(getTickets());
    }

    @Override
    public String toString() {
        return super.toString() + ": " + getMessenger().getMessage("infoShow");
    }
}
