package command;

import i18n.Messenger;
import input.ConsoleInput;
import input.Input;
import lombok.Getter;
import lombok.Setter;
import ticket.Ticket;

import javax.validation.ConstraintViolation;
import java.util.ArrayList;
import java.util.Set;


@Getter @Setter
public class UpdateId extends Command {

    private Input userInput;

    public UpdateId(ArrayList<Ticket> tickets, Messenger messenger) {
        super(tickets, messenger);

        userInput = new ConsoleInput(messenger);
    }

    @Override
    public String execute(String id) {

        for (Ticket o: getTickets()) {
            if(o.getId() == Long.parseLong(id)) {
                try {
                    Ticket ticket = userInput.enterElement();
                    Set<ConstraintViolation<Ticket>> violations = getValidator().validate(ticket);

                    if(violations.isEmpty()) {
                        o = ticket;
                        return getFormatter().formatBooleanOperation(true, getMessenger());
                    }
                    violations.forEach(v -> System.err.println(v.getMessage()));

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return getFormatter().formatBooleanOperation(false, getMessenger());
    }

    @Override
    public String toString() {
        return super.toString() + ": " + getMessenger().getMessage("infoUpdateId");
    }
}
