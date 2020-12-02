package org.example.Utils;

import org.example.Commands.CommandClasses.AbstractCommand;
import org.example.Commands.CommandManager;
import org.example.OwnExeptions.InvalidCountOfArgumentException;
import org.example.Worker.Worker;

import java.io.IOException;
import java.util.LinkedHashMap;

public class CommandFinally extends CommandManager {
    CommandManager commandManager = new CommandManager();

    public CommandFinally() {
    }

    public CommandFinally(LinkedHashMap<Integer, Worker> collection) {
        CommandManager.setCollection(collection);
    }

    /**
     * Метод на входе получает Абстракт-команду, и на выходе даёт её результат.
     *
     * @param command
     * @return String: результат команды
     */
    public String sendResult(AbstractCommand command, LinkedHashMap<Integer, Worker> collection) throws IOException, InvalidCountOfArgumentException {

        if (command.getCommand().equals("exit")) {
            commandManager.getCommand("save").execute("save");
        }


        if (!command.getObjectExecute() && !command.isNeedAnStr()) {
            return command.execute(commandManager.getCollection(), command.getString());
        }
        //Команды, где нужен только аргумент. (Пример: remove_key_lower {key})
        if (!command.getObjectExecute() && command.isNeedAnStr()) {

            if (command.getString() != null) {
                return command.execute(commandManager.getCollection(), command.getString());
            } else {
                if (command.getString() == null) return "Нет строки";
            }

        }

        //Команды, где нужен объект и аргумент. (Пример: remove_lower {element}})
        if (command.getObjectExecute() && !command.isNeedAnStr()) {

            if (command.getWorker() != null) {
                return command.execute(commandManager.getCollection(), command.getString());
            } else {
                if (command.getWorker() == null) return "Нет рабочего";
            }
        }

        //Команды, где нужен объект и аргумент. (Пример: insert {key} + {element}})
        if (command.getObjectExecute() && command.isNeedAnStr()) {

            if (command.getWorker() != null && command.getString() != null) {
                return command.execute(commandManager.getCollection(), command.getString());
            } else {
                if (command.getWorker() == null && command.getString() == null) return "Нет рабочего и строки";
                if (command.getWorker() == null) return "Нет рабочего";
                if (command.getString() == null) return "Нет строки";
            }
        }
        return "Что-то пошло не так.";
    }

}