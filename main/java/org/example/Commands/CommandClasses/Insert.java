package org.example.Commands.CommandClasses;

import org.example.Worker.Worker;

import java.util.LinkedHashMap;
import java.util.Map;

public class Insert extends AbstractCommand {
    public Insert() {
        command = "insert";
        helpText = "добавить новый элемент с задданным ключом";
        NeedAnStr = true;
        NeedAnObject = true;
        NeedWorker = true;
    }


    @Override
    public String execute(LinkedHashMap<Integer, Worker> collection, String arg) {
        String s = "";

        for (Map.Entry<Integer, Worker> workerEntry : collection.entrySet()) {
            if (workerEntry.getKey().equals(Integer.parseInt(arg))) {
                return "Такой элемент уже есть";
            }
        }
        collection.put(Integer.parseInt(arg), worker);
        return "Добавлен новый элемент";
    }

}
