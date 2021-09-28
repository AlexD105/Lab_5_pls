package input;

import i18n.Messenger;
import ticket.Event;
import ticket.Ticket;

import java.util.Scanner;

public class FileInput extends Input {

    public FileInput(Scanner in, Messenger messenger) {
        super(in, messenger);
    }

    @Override
    public Ticket enterElement() throws Exception {

        EventReader reader = new FileEventReader();

        return new Ticket(readName(),
                readCoordinates(),
                readPrice(),
                readComment(),
                readRefundable(),
                readTicketType(),
                new Event(
                        reader.readName(),
                        reader.readMinAge(),
                        reader.readTicketsCount(),
                        reader.readDescription()
                )
        );
    }

    public EventReader getEventReader() {
        return new FileEventReader();
    }

    public class FileEventReader extends EventReader {
        @Override
        public Event enterElement() throws Exception {
            return new Event(readName(), readMinAge(), readTicketsCount(), readDescription());
        }
    }
}