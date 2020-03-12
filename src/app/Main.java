package app;

import commands.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.util.LinkedHashSet;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * маршаллизация входных данных, создание обработчика команд и установка команд для него, создание приложения и его запуск
 */
public class Main {
    public static void main(String[] args) {
        Application application = new Application(new LinkedHashSet<>());
        Handler handler = new Handler();
        handler.addCommand(new AddCommand().toString(), new AddCommand());
        handler.addCommand(new AddIfMaxCommand().toString(), new AddIfMaxCommand());
        handler.addCommand(new AddIfMinCommand().toString(), new AddIfMinCommand());
        handler.addCommand(new ClearCommand().toString(), new ClearCommand());
        handler.addCommand(new ExecuteScriptCommand().toString(), new ExecuteScriptCommand());
        handler.addCommand(new ExitCommand().toString(), new ExitCommand());
        handler.addCommand(new FilterGreaterThanStudentsCountCommand().toString(), new FilterGreaterThanStudentsCountCommand());
        handler.addCommand(new FilterStartsWithNameCommand().toString(), new FilterStartsWithNameCommand());
        handler.addCommand(new HelpCommand().toString(), new HelpCommand());
        handler.addCommand(new HistoryCommand().toString(), new HistoryCommand());
        handler.addCommand(new InfoCommand().toString(), new InfoCommand());
        handler.addCommand(new RemoveCommand().toString(), new RemoveCommand());
        handler.addCommand(new SaveCommand().toString(), new SaveCommand());
        handler.addCommand(new ShowCommand().toString(), new ShowCommand());
        handler.addCommand(new SumOfStudentsCountCommand().toString(), new SumOfStudentsCountCommand());
        handler.addCommand(new UpdateCommand().toString(), new UpdateCommand());
        try {
            File fileInput = new File(System.getenv("INPUT_PATH"));
            JAXBContext jaxbContext = JAXBContext.newInstance(Collection.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(fileInput));
            Collection collection = (Collection) unmarshaller.unmarshal(inputStreamReader);
            application = new Application(collection.get());
        } catch (JAXBException e) {
            System.out.println("Входной файл не корректен или вообще пуст!!!");
        } catch (FileNotFoundException e) {
            System.out.println("Входной файл не существует или для его чтения не хватает прав доступа!!!");
        } catch (NullPointerException e) {
            System.out.println("Переменная окружения не найдена!!!");
        }
        application.setHandler(handler);
        handler.setApplication(application);
        System.out.println("Консольное приложение для управления коллекицей элементов");
        System.out.println("Автор: Дьяконов Михаил, группа P3111");
        System.out.println("Для просмотра доступных комманд введите help");
        //внутри запуск приложения и отлов ввода незаконченных элементов
        while (true) {
            try {
                handler.run(new Scanner(System.in));
                break;
            } catch (NoSuchElementException e) {
                System.out.println("Ошибка!!! Нельзя выходить из приложения или скрипта, не закончив ввод полей, изменения не сохранены, " +
                        "вернуться в приложение(No - выйти)? Yes/No:");
                if (new Scanner(System.in).nextLine().equals("Yes")) {
                    System.out.println("Выход из приложения...");
                    break;
                }
            }
        }
    }
}
