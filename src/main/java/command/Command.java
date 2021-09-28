package command;

import i18n.Messenger;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import print.api.Formatter;
import print.implementation.FormatterImpl;
import ticket.Ticket;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;

@Getter(AccessLevel.PROTECTED)
@Setter(AccessLevel.PROTECTED)
public abstract class Command {

    private ArrayList<Ticket> tickets;

    private Formatter formatter;

    private Messenger messenger;

    private Validator validator;

    public Command(ArrayList<Ticket> tickets, Messenger messenger) {
        this.tickets = tickets;
        this.messenger = messenger;
        this.formatter = new FormatterImpl();

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    public abstract String execute(String args) throws Exception;

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
