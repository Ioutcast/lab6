package org.example.Commands.CommandClasses;

import org.example.Commands.CommandManager;
import org.example.Worker.Worker;

import java.util.LinkedHashMap;
import java.util.Map;

public class Help extends AbstractCommand {

    public Help() {
        command = "help";
        helpText = "вывести справку по доступным командам ";
        NeedAnStr = false;

    }

    @Override
    public String execute(LinkedHashMap<Integer, Worker> collection, String s) {
        String str = "";

        for (Map.Entry<String, AbstractCommand> abs : CommandManager.getAvailableCommands().entrySet()) {
            str = str + abs.getValue().toPrint() + "\n";
        }

        return str;
    }
}
