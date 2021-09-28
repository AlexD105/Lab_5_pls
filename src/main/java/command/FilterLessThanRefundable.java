package command;

import i18n.Messenger;
import input.ConsoleInput;
import input.Input;
import ticket.Event;
import ticket.Ticket;

import java.util.ArrayList;

public class FilterLessThanRefundable extends Command {

    private final Input input;

    public FilterLessThanRefundable(ArrayList<Ticket> tickets, Messenger messenger) {
        super(tickets, messenger);

        this.input = new ConsoleInput(getMessenger());
    }

    @Override
    public String execute(String args) throws Exception {

        Event event = input.getEventReader().enterElement();

        ArrayList<Ticket> filter = new ArrayList<>();
        for (Ticket v: getTickets()) {
            if(v.getEvent().compareTo(event) > 0) filter.add(v);
        }

        return getFormatter().formatCollection(filter);
    }

    @Override
    public String toString() {
        return super.toString() + ":" + getMessenger().getMessage("infoFilterGreaterThanOfficialAddress");
    }
}