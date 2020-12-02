package org.example.Commands.CommandClasses;


import org.example.Worker.Worker;

import java.util.LinkedHashMap;

import static org.example.Server.dateCollection;


public class Info extends AbstractCommand {
    public Info() {
        command = "info";
        helpText = "вывести в стандартный поток вывода информацию о коллекции";
        NeedAnStr = false;
        NeedAnObject = false;
    }


    protected String info(LinkedHashMap collection) {
        String type = "Тип коллекции: " + collection.getClass().getSimpleName();
        String date = "Дата инициализации: " + dateCollection.toString();
        String size = "Количество элементов: " + collection.size();
        return type + "\n" + date + "\n" + size;
    }

    @Override
    public String execute(LinkedHashMap<Integer, Worker> collection, String arg) {
        return info(collection);
    }

}
