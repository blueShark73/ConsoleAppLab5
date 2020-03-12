package app;

import commands.*;
import exceptions.InputFormatException;

import java.util.HashMap;
import java.util.Scanner;

/**
 * обработчик команд, ищет совпадение среди данных ему команд в HashMap
 */
public class Handler {
    private HashMap<String, Command> commands;
    private boolean exitCommand = false;
    private Application application;

    /**
     * связывает обработчик и приложение, которое будет исполнять обработанные команды
     */
    public Handler() {
        commands = new HashMap<>();
    }

    /**
     * добавляет новую команду в обработчик
     *
     * @param string  - ключ
     * @param command - значение
     */
    public void addCommand(String string, Command command) {
        commands.put(string, command);
    }

    /**
     * флаг выхода из консольного приложения
     */
    public void setExitCommand() {
        exitCommand = true;
    }

    /**
     * запускает обработчик
     *
     * @param scanner - сканер, с которого считываем команды
     */
    public void run(Scanner scanner) {
        while (!exitCommand && scanner.hasNext()) {
            try {
                //ищем в следующей строке команду, вдруг она не в начале строки
                String[] nextLine = scanner.nextLine().split(" ");
                int positionOfCommand = 0;
                while (nextLine[positionOfCommand].equals("") || nextLine[positionOfCommand].equals("\n"))
                    positionOfCommand++;
                String nextCommand = nextLine[positionOfCommand];
                String argument = null;
                if (nextLine.length > positionOfCommand + 1) argument = nextLine[positionOfCommand + 1];
                Command command = commands.get(nextCommand);
                if (command == null) throw new InputFormatException();
                if ((!command.withArgument() && argument != null) || nextLine.length > positionOfCommand + 2)
                    throw new IndexOutOfBoundsException("Слишком много аргументов у команды " + command.toString() + " !!!");
                application.executeCommand(command, argument, scanner);
            } catch (InputFormatException | StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException e) {
                System.out.println("Ошибка ввода!!! Такой команды нет. help - показать доступные команды");
                scanner = new Scanner(System.in); //иначе при вводе большого кол-ва пустых строк будет выведено много предупреждений
            } catch (IndexOutOfBoundsException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * обязательный метод для привязки приложения к обрабочику
     *
     * @param application - привязываемое приложение
     */
    public void setApplication(Application application) {
        this.application = application;
    }
}
