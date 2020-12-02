package org.example.Commands.CommandClasses;

import org.example.UpdateWorker.SetOrganizationDefault;
import org.example.Worker.Organization;
import org.example.Worker.Worker;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class FilterGreaterThanOrganization extends AbstractCommand {
    public FilterGreaterThanOrganization() {
        command = "filter_greater_than_organization";
        helpText = "вывести элементы значение поля organization которых больше заданного";
        NeedAnStr = false;
        NeedAnObject = true;
        NeedOrganization = true;
    }


    @Override
    public String execute(LinkedHashMap<Integer, Worker> collection, String arg) {
        String s = "";
        if (collection.size() != 0) {
            if (organization == null) {
                s = "null organization не сравниваем, введите команду занов";
            } else {
                System.out.println("элементы, значение поля organization которых больше заданного:");
                for (Map.Entry<Integer, Worker> entry : collection.entrySet()) {
                    if (entry.getValue().getOrganization().compareTo(organization) > 0) {
                        s = s + "Ключ " + entry.getKey() + " Элемент коллекции " + entry.getValue().toString() + "\n";
                    }
                }
            }
        } else s = "Размер коллекции == 0";
        return s;
    }


}
