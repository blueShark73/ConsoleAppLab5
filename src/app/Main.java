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
        Collection collection = FileWorker.getCollection("INPUT_PATH", "input.xml");
        if (collection != null) application = new Application(collection.get());
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
                System.out.println("Ошибка!!! Нельзя выходить из приложения или скрипта, не закончив ввод полей, измененённая коллекция не будет сохранена");
                System.out.println("Вернуться в приложение(Yes - выйти, No - продолжить работу с приложением)? Yes/No:");
                if (new Scanner(System.in).nextLine().equals("Yes")) {
                    System.out.println("Выход из приложения...");
                    break;
                }
            }
        }
    }
}
