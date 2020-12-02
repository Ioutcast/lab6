package org.example.Commands;


import lombok.Getter;
import lombok.Setter;
import org.example.Worker.Worker;

import java.io.*;
import java.util.*;

public abstract class CommandInfo {
    @Getter
    protected static LinkedHashMap<Integer, Worker> mapCollection = new LinkedHashMap<>();
    protected static String ScriptFileName = "";
    @Getter
    protected static BufferedReader bufferedReader;
    @Getter
    protected static BufferedWriter bufferedWriter;
    private static File xml = new File("xml.xml");
    private static File newXml = new File("newXml.xml");
    @Setter
    protected Worker worker = new Worker();

    public static void stopClose() throws IOException {
        try {
            getBufferedWriter().close();
            getBufferedReader().close();
        } catch (NullPointerException e) {
        }
    }

    protected static void setBufferedReader() throws IOException {
        if (!getNewXml().exists()) {
            getNewXml().createNewFile();
        }
        bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(getNewXml())));
    }

    protected static void setBufferedWriter() throws IOException {
        bufferedWriter = new BufferedWriter(new FileWriter(getNewXml()));
    }

    protected static File getXml() {
        return xml;
    }

    public static void setXml(File xml) {
        CommandInfo.xml = xml;
    }

    protected static File getNewXml() {
        return newXml;
    }

    protected static void setNewXml(File newXml) {
        CommandInfo.newXml = newXml;
    }

    public static void stop() throws IOException {
        try {
            getBufferedWriter().close();
            getBufferedReader().close();
        } catch (NullPointerException e) {
        }
    }

    public LinkedHashMap getCollection() {
        return CommandInfo.getMapCollection();
    }

    public static void setCollection(LinkedHashMap<Integer, Worker> collection) {
        mapCollection = collection;
    }

    public boolean CheckAndSetWorker(Object o) {
        try {
            Worker worker1 = (Worker) o;
            this.worker = worker1;
            return true;
        } catch (ClassFormatError e) {
            return false;
        }
    }
    protected static LinkedHashMap<Integer, Worker> SortCollection(LinkedHashMap<Integer, Worker> collection ) {
        Comparator<Worker> workerComparator = new Comparator<Worker>() {
            @Override
            public int compare(Worker s, Worker s1) {
                return s.getName().compareTo(s1.getName());
            }
        };
        List<Worker> workers = new ArrayList<>();
        for (Map.Entry<Integer, Worker> workerEntry : collection.entrySet()) {
            workers.add(workerEntry.getValue());
        }
        LinkedHashMap<Integer, Worker> linkedHashMap = new LinkedHashMap<Integer, Worker>();
        Collections.sort(workers, workerComparator);
        for(Worker worker : workers){
            linkedHashMap.put(worker.getId(), worker);
        }
        collection = linkedHashMap;

        return collection;
    }

}
