package command;

import i18n.Messenger;
import ticket.Ticket;

import java.util.ArrayList;

public class RemoveById extends Command {

    public RemoveById(ArrayList<Ticket> tickets, Messenger messenger) {
        super(tickets, messenger);
    }

    @Override
    public String execute(String id) {
        return getFormatter().formatBooleanOperation(
                getTickets().removeIf(d -> d.getId() == Long.parseLong(id)),
                getMessenger()
        );
    }

    @Override
    public String toString() {
        return super.toString() + ": " + getMessenger().getMessage("infoRemoveById");
    }
}
