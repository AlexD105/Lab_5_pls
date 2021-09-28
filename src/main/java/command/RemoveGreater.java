package command;

import i18n.Messenger;
import input.ConsoleInput;
import input.Input;
import ticket.Ticket;

import java.util.ArrayList;

public class RemoveGreater extends Command {

    private Input input;

    public RemoveGreater(ArrayList<Ticket> tickets, Messenger messenger) {
        super(tickets, messenger);

        this.input = new ConsoleInput(getMessenger());
    }

    @Override
    public String execute(String args) throws Exception {

        Ticket ticket = input.enterElement();

        return getFormatter().formatBooleanOperation(
                getTickets().removeIf(o -> o.compareTo(ticket) > 0),
                getMessenger()
        );
    }

    @Override
    public String toString() {
        return super.toString() + ": " + getMessenger().getMessage("infoRemoveGreater");
    }
}
