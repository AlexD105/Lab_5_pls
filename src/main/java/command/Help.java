package command;

import i18n.Messenger;
import org.reflections.Reflections;
import ticket.Ticket;

import java.lang.reflect.Constructor;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Set;

public class Help extends Command {

    public Help(ArrayList<Ticket> tickets, Messenger messenger) {
        super(tickets, messenger);
    }

    @Override
    public String execute(String ignore) {

        Reflections reflections = new Reflections("command");
        Set<Class<? extends Command>> classes = reflections.getSubTypesOf(Command.class);

        StringBuilder result = new StringBuilder("");

        classes.forEach(c -> {

            try {
                Constructor<? extends Command> constructor = c.getConstructor(ArrayList.class, Messenger.class);
                Command command = constructor.newInstance(getTickets(), getMessenger());
                result.append(command.toString()).append("\n");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        return result.toString();
    }

    @Override
    public String toString() {
        return super.toString() + ": " + getMessenger().getMessage("infoHelp");
    }
}
