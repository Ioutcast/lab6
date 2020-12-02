package org.example.Commands.CommandClasses;

import lombok.Getter;
import lombok.Setter;
import org.example.Commands.CommandInfo;
import org.example.OwnExeptions.InvalidCountOfArgumentException;
import org.example.Utils.XmlWriteToFile;
import org.example.Worker.Organization;
import org.example.Worker.Worker;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public abstract class AbstractCommand extends CommandInfo implements Serializable {


    @Getter
    protected String command;
    @Getter
    protected String helpText;
    protected boolean NeedAnStr = false;
    protected boolean NeedAnObject = false;
    protected boolean NeedWorker = false;
    protected boolean NeedOrganization = false;
    protected Worker worker = new Worker();
    @Setter
    @Getter
    protected Organization organization = null;
    @Getter
    @Setter
    protected String string = "";

    public Worker getWorker() {
        return worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }

    public boolean isNeedWorker() {
        return NeedWorker;
    }

    public boolean isNeedOrganization() {
        return NeedOrganization;
    }

    public boolean isNeedAnStr() {
        return NeedAnStr;
    }

    public boolean CheckAndSetWorker(Object o) {
        try {
            this.worker = (Worker) o;
            return true;
        } catch (ClassFormatError e) {
            return false;
        }
    }

    public boolean getObjectExecute() {
        return NeedAnObject;
    }

    public String execute(LinkedHashMap<Integer, Worker> collection, String arg) throws IOException {
        return arg;
    }

    public String execute(String s) {
        return "команды не существует";
    }
   
    public String toPrint() {
        return command + " " + helpText;
    }

    @Override
    public String toString() {
        return command;
    }

}
