package org.example.Commands.CommandClasses;

import org.example.Worker.Worker;

import java.util.*;

public class RemoveLowerKey extends AbstractCommand {
    public RemoveLowerKey() {
        command = "remove_lower_key";
        helpText = "удалить из коллекции все элементы , ключ которых мешьне, чем заданный ";
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
                if (value.compareTo(Integer.valueOf(arg)) < 0) {
                    s = "removeing : " + entry;
                    iterator.remove();
                }
            }
        } else s = "Размер коллекции == 0";
        return s;
    }


}
