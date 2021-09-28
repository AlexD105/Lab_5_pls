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
public class Add extends Command {

    private Input consoleUserInput;

    public Add(ArrayList<Ticket> tickets, Messenger messenger) {
        super(tickets, messenger);

        consoleUserInput = new ConsoleInput(messenger);

    }

    @Override
    public String execute(String ignore) throws Exception {

        Ticket ticket = consoleUserInput.enterElement();

        Set<ConstraintViolation<Ticket>> violations = getValidator().validate(ticket);

        if(violations.isEmpty()) {
            getTickets().add(ticket);
            return getFormatter().formatBooleanOperation(true, getMessenger());
        }

        violations.forEach(v -> System.err.println(v.getMessage()));
        return getFormatter().formatBooleanOperation(false, getMessenger());
    }

    @Override
    public String toString() {
        return super.toString() + ": " + getMessenger().getMessage("infoAdd");
    }
}
