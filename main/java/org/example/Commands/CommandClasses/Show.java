package org.example.Commands.CommandClasses;

import org.example.Worker.Worker;

import java.util.LinkedHashMap;
import java.util.Map;

public class Show extends AbstractCommand {
    public Show() {
        command = "show";
        helpText = "вывести в стандартный поток вывода все элементы коллекции в строковом представлении";
        NeedAnStr = false;

    }

    @Override
    public String execute(LinkedHashMap<Integer, Worker> collection, String arg) {
        return show(collection);
    }

    private String show(LinkedHashMap<Integer, Worker> collection) {
        SortCollection(collection);
        String s = "";
        if (collection.size() != 0) {
            s = s + "Вывод колекции:" + "\n";
            for (Map.Entry<Integer, Worker> entry : collection.entrySet()) {
                s = s + "Ключ " + entry.getKey() + " Элемент коллекции " + entry.getValue().toString() + "\n";
            }
        } else s = "Размер коллекции == 0";
        return s;
    }
}
