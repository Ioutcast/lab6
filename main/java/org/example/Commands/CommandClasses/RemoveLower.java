package org.example.Commands.CommandClasses;

import org.example.Worker.Worker;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;


public class RemoveLower extends AbstractCommand {
    public RemoveLower() {
        command = "remove_lower";
        helpText = "удалить из коллекции все элементы, меньшие чем заданный";
        NeedAnStr = false;
        NeedAnObject = true;
        NeedWorker = true;
    }

    @Override
    public String execute(LinkedHashMap<Integer, Worker> collection, String arg) {
        String s = "Никто не постарал";
        if (collection.size() != 0) {
            mapCollection.entrySet().removeIf(entry -> entry.getValue().compareTo(getWorker()) < 0);
            s = "удаление прошло успешно";
        } else s = "Размер коллекции == 0";
        return s;
    }

}
