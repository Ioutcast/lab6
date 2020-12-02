package org.example.Commands.CommandClasses;

import org.example.Worker.Worker;

import java.util.*;

public class RemoveAnyByEndDate extends AbstractCommand {
    public RemoveAnyByEndDate() {
        command = "remove_any_by_end_date";
        helpText = "удалить из коллекции один элемент, значение поля  endDate которого эквивалентно заданному";
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
                String value = String.valueOf(entry.getValue().getEndDate());
                if (value.equals(string)) {
                    s = s + "removing : " + entry;
                    iterator.remove();
                    break;
                }
            }
        } else s = s + "Размер коллекции == 0";

        return s;
    }

}
