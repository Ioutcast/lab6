package org.example.Commands.CommandClasses;

import org.example.Worker.Worker;

import java.util.*;

public class RemoveGreaterKey extends AbstractCommand {
    public RemoveGreaterKey() {
        command = "remove_greater_key";
        helpText = "удалить из коллекции все элементы , ключ которых превышает заданный";
        NeedAnStr = true;

    }

    @Override
    public String execute(LinkedHashMap<Integer, Worker> collection, String arg) {
        String s = "";
        if (collection.size() != 0) {
            Set<Map.Entry<Integer, Worker>> setOfEntries = collection.entrySet();
            Iterator<Map.Entry<Integer, Worker>> iterator = setOfEntries.iterator();
            while (iterator.hasNext()) {
                Map.Entry<Integer, Worker> entry = iterator.next();
                Integer value = entry.getKey();
                if (value.compareTo(Integer.valueOf(arg)) > 0) {
                    s = "removeing : " + entry;
                    iterator.remove();
                }
            }
        } else s = "Размер коллекции == 0";
        return s;
    }


}
