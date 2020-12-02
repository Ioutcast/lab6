package org.example;

import lombok.extern.log4j.Log4j2;
import org.example.Commands.CommandClasses.AbstractCommand;
import org.example.Commands.CommandClasses.Save;
import org.example.Commands.CommandManager;
import org.example.OwnExeptions.InvalidCountOfArgumentException;
import org.example.Utils.CommandFinally;
import org.example.Utils.Serialization;
import org.example.Utils.XmlFileReader;
import org.example.Worker.Organization;
import org.example.Worker.Worker;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.DatagramChannel;
import java.time.LocalDate;

@Log4j2
public class Server implements Runnable {
    public static LocalDate dateCollection;
    private static final int port = 50000;
    private static XmlFileReader fileReader = new XmlFileReader();
    CommandManager commandManager = new CommandManager();
    String s = null;
    Object o = null;
    boolean exit = false;
    private DatagramChannel datagramChannel;
    private SocketAddress socketAddress;
    private AbstractCommand command = null;
    int b = 0;
    public Server() throws TransformerException, ParserConfigurationException {
    }

    public static void main(String[] args) {
        try {
            XmlFileReader.setXml(new File(args[0]));

        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Вы не ввели файл для считывания. \nЗавершение программы.");
            System.exit(-1);
        }

        System.out.println("Был запущен сервер.");
        fileReader.run();
        dateCollection = LocalDate.now();
        try {
            Server server = new Server();
            server.run();
        } catch (IllegalStateException e) {
            System.exit(-1);
        } catch (TransformerException | ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            socketAddress = new InetSocketAddress(port);
            datagramChannel = DatagramChannel.open();
            datagramChannel.bind(new InetSocketAddress(port));
            datagramChannel.configureBlocking(false);

            while (true) {
                try {
                    if (exit) System.exit(-1);
                    receive();
                } catch (TransformerException | ParserConfigurationException e) {
                    e.printStackTrace();
                } catch (InvalidCountOfArgumentException e) {
                    e.printStackTrace();
                }
            }

        } catch (ClosedChannelException | SocketException e) {
            System.out.println("Что не так с сокетом или с каналом");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("Сервер умер");
            Save saveClass = new Save();
            try {
                saveClass.execute(commandManager.getCollection(), saveClass.getString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void receive() throws IOException, TransformerException, ParserConfigurationException, InvalidCountOfArgumentException {

        ByteBuffer byteBuffer = ByteBuffer.allocate(65536);
        byteBuffer.clear();
        socketAddress = datagramChannel.receive(byteBuffer);
        byteBuffer.flip();
        DatagramChannel datagramC = datagramChannel;
        boolean a = false;
        if (socketAddress != null) {

            o = new Serialization().DeserializeObject(byteBuffer.array());
            s = o.toString();

            commandManager.setServerDatagramChannel(datagramC);
            commandManager.setSocketAddress(socketAddress);
            System.out.println("Полученно [" + s + "] от " + socketAddress);

            if (!commandManager.Check(s)) {
                datagramChannel.send(ByteBuffer.wrap(("Команда [" + s + "] не найдена или имеент неверное количество аргументов. Для просмотра доступных команд введите help").getBytes()), socketAddress);
                datagramChannel.send(ByteBuffer.wrap("Something goes wrong".getBytes()), socketAddress);
            }

            if (o == null) {
                datagramChannel.send(ByteBuffer.wrap(("Ошибка" + "Команда [" + s + "] не найдена или имеент неверное количество аргументов. Для просмотра доступных команд введите help").getBytes()), socketAddress);
                datagramChannel.send(ByteBuffer.wrap("Something goes wrong".getBytes()), socketAddress);
            }
            log.debug("до");
            if (commandManager.Check(s)) {
                byteBuffer.clear(); log.debug("дада clear");
                socketAddress = datagramChannel.receive(byteBuffer);
                byteBuffer.flip();
                log.debug("после");
                if (socketAddress != null) {
                    command = (AbstractCommand) o;
                    System.out.println("Получено [" + o + "] ОТ " + socketAddress);
                    a = true;
                    log.debug("после1");
                }
                command = (AbstractCommand) o;

                log.debug("после2");
                System.out.println(command.toPrint());
                if (command.getObjectExecute() && a) {
                    log.debug("после3.5");
                    if (command.isNeedWorker()) {
                        log.debug("после3.6");
                        Worker worker = (Worker) new Serialization().DeserializeObject(byteBuffer.array());
                        log.debug(worker.toString());
                        command.setWorker(worker);
                    }
                    if (command.isNeedOrganization()) {
                        Organization organization = (Organization) new Serialization().DeserializeObject(byteBuffer.array());
                        command.setOrganization(organization);
                    }
                }

                if (!a && !command.getObjectExecute())
                    if (command.isNeedAnStr()) {
                        log.debug("после4");
                        o = new Serialization().DeserializeObject(byteBuffer.array());
                        String str = (String) o;
                        command.setString(str);
                    }

                //выполнение команды и отправка результата
                if (!command.getCommand().equals("exit")) {
                    log.debug("после5");
                    commandManager.printToClient(new CommandFinally(commandManager.getCollection()).sendResult(command, commandManager.getCollection()));

                } else {
                    exit = true;
                    Save saveClass = new Save();
                    saveClass.execute(commandManager.getCollection(), "");
                }

            }
        }
    }
}
