package command;


import i18n.Messenger;
import ticket.Ticket;

import java.util.ArrayList;

public class Clear extends Command {

    public Clear(ArrayList<Ticket> tickets, Messenger messenger) {
        super(tickets, messenger);
    }

    @Override
    public String execute(String ignore) {
        getTickets().clear();
        return getFormatter().formatBooleanOperation(true, getMessenger());
    }

    @Override
    public String toString() {
        return super.toString() + ": " + getMessenger().getMessage("infoClear");
    }
}
