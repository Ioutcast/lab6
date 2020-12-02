package org.example.Commands.CommandClasses;

import org.example.Worker.Worker;

import java.util.LinkedHashMap;

public class Clear extends AbstractCommand {
    public Clear() {
        command = "clear";
        helpText = "очистить коллекцию";
        NeedAnStr = false;
    }

    @Override
    public String execute(LinkedHashMap<Integer, Worker> collection, String arg) {

        collection.clear();
        return "Коллекция " + collection + " была очищена.";
    }
}
