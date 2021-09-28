
import command.manager.CommandManager;
import i18n.Messenger;
import i18n.MessengerImpl;
import print.implementation.FormatterImpl;
import print.implementation.PrinterImpl;
import service.Converter;
import service.UserTerminal;

import java.util.ResourceBundle;

public class Main {
    public static void main(String[] args) {

        Messenger messenger = new MessengerImpl(ResourceBundle.getBundle("text", Messenger.initLang()));

        UserTerminal terminal = new UserTerminal(
                new CommandManager(
                        new FormatterImpl(),
                        messenger,
                        new PrinterImpl(),
                        Converter.read(System.getenv("CSV"))
                ),
                new FormatterImpl(),
                new PrinterImpl(),
                messenger
        );

        // Запускаем терминал
        terminal.start();
    }
}
