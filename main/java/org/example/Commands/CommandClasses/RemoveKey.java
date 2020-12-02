package org.example.Commands.CommandClasses;


import org.example.Worker.Worker;

import java.util.LinkedHashMap;
import java.util.Map;

public class RemoveKey extends AbstractCommand {
    public RemoveKey() {
        command = "remove_key";
        helpText = "удалить элемент из коллекции по его ключу";
        NeedAnStr = true;
    }

    @Override
    public String execute(LinkedHashMap<Integer, Worker> collection, String arg) {
        if (collection.isEmpty()) return "Коллекция пуста";
        boolean bool = false;
        Integer key = Integer.parseInt(string);
        for (Map.Entry<Integer, Worker> WorkerEntry : collection.entrySet()) {
            if (!WorkerEntry.getKey().equals(key)) {
                bool = true;
            }
        }
        if (bool) {
            String msg = collection.get(key).toString();
            collection.remove(key);
            return ("Рабочий " + msg + " под номером " + key + " был удален.");

        } else {
            return ("Такого рабочего не существует.");
        }
    }


}
