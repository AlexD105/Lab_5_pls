package command;

import i18n.Messenger;
import ticket.Ticket;

import java.util.ArrayList;

public class Head extends Command {

    public Head(ArrayList<Ticket> tickets, Messenger messenger) {
        super(tickets, messenger);
    }

    @Override
    public String execute(String args) throws Exception {
        if(getTickets().size() != 0) {
            return getTickets().get(0).toString();
        } else {
            return getFormatter().formatBooleanOperation(false, getMessenger());
        }
    }

    @Override
    public String toString() {
        return super.toString() + ":" + getMessenger().getMessage("infoHead");
    }
}
