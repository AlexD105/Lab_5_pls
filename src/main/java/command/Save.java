package command;

import i18n.Messenger;
import service.Converter;
import ticket.Ticket;

import java.util.ArrayList;

public class Save extends Command {

    public Save(ArrayList<Ticket> tickets, Messenger messenger) {
        super(tickets, messenger);
    }

    @Override
    public String execute(String ignore) {

        Converter.write(getTickets(), System.getenv("CSV"));

        return getFormatter().formatBooleanOperation(true, getMessenger());
    }

    @Override
    public String toString() {
        return super.toString() + ": " + getMessenger().getMessage("infoSave");
    }
}
