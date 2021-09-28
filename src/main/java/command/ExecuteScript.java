package command;

import command.manager.CommandManager;
import i18n.Messenger;
import input.FileInput;
import input.Input;
import lombok.Getter;
import lombok.Setter;
import ticket.Ticket;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

@Getter @Setter
public class ExecuteScript extends Command {

    private Input userInput;
    private CommandManager manager;

    public ExecuteScript(ArrayList<Ticket> tickets, Messenger messenger) {
        super(tickets, messenger);
    }


    @Override
    public String execute(String fileName) throws Exception {
        return execute_script(fileName);
    }

    public String execute_script(String fileName) throws Exception {

        this.userInput = new FileInput(new Scanner(new File(fileName)), getMessenger());

        if(!new File(fileName).exists()) throw new IOException(getMessenger().getMessage("fileNotFound"));

        try(Scanner scanner = new Scanner(new File(fileName))) {

            // Перед выполнением скрипта меняем источник ввода объектов на файловый
            StringBuilder result = new StringBuilder();

            String response;
            while (scanner.hasNextLine()) {

                response = scanner.nextLine();

                // Обязательно нужно проверить условие зацикливания, чтобы скрипт не вызвал сам себя
                if(response.equals("executescript " + fileName)) {
                    result.append(getMessenger().getMessage("recursiveCallScript")).append("\n");
                } else {
                    result.append(manager.executeCommand(response)).append("\n");
                }
            }

            return result.toString();

        } catch (IOException e) {
            throw new Exception(e.getMessage());
        } catch (Exception e) {
            throw new Exception(getMessenger().getMessage("scriptNotValid"));
        }
    }

    public void setCommandManager(CommandManager manager) {
        this.manager = manager;
    }


    @Override
    public String toString() {
        return super.toString() + ": " + getMessenger().getMessage("infoExecuteScript");
    }
}
