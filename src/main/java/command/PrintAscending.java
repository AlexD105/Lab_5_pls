package command;

import i18n.Messenger;
import ticket.Ticket;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PrintAscending extends Command {
    public PrintAscending(ArrayList<Ticket> tickets, Messenger messenger) {
        super(tickets, messenger);
    }

    @Override
    public String execute(String args) throws Exception {
        List<Ticket> sorted = new ArrayList<>(getTickets());
        Collections.sort(sorted);

        return getFormatter().formatCollection(sorted);
    }

    @Override
    public String toString() {
        return super.toString() + ": " + getMessenger().getMessage("infoPrintDescending");
    }
}