package org.example.Commands.CommandClasses;

import org.example.Worker.Worker;

import java.util.LinkedHashMap;

public class Exit extends AbstractCommand {

    public Exit() {
        command = "exit";
        helpText = ": Завершение работы программы";

        NeedAnStr = false;
    }

    @Override
    public String execute(LinkedHashMap<Integer, Worker> collection, String arg) {
        System.exit(-1);
        return null;
    }

}