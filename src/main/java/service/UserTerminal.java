package service;

import command.Exit;
import command.manager.CommandManager;
import i18n.Messenger;
import lombok.Getter;
import lombok.Setter;
import print.api.Formatter;
import print.api.Printer;

import java.util.Scanner;

@Getter @Setter
public class UserTerminal {

    private CommandManager manager;
    private Formatter formatter;
    private Printer printer;
    private Messenger messenger;

    public UserTerminal(CommandManager manager, Formatter formatter, Printer printer, Messenger messenger) {
        this.manager = manager;
        this.formatter = formatter;
        this.printer = printer;
        this.messenger = messenger;
    }

    public void start() {

        System.out.println("Вас приветствует терминал управления списком пользователей.\n" +
                "Введите одну из команд для продолжения (help - список доступных команд)");

        Scanner sc = new Scanner(System.in);
        String response;
        while (!(response = sc.nextLine()).equals("exit")) {
            try {

                if(response.equals("")) {
                    System.out.println(getManager().getMessenger().getMessage("enterValidData"));
                } else {
                    manager.executeUserCommand(response);
                }

            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }

        new Exit(null, null).execute(null);
    }

}
