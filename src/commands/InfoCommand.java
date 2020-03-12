package commands;

import app.Application;
import app.StudyGroup;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Scanner;

/**
 * команда выводит информацию о коллекции
 */
public class InfoCommand extends Command {
    private Application application;

    @Override
    public void execute(Application application, String argument, Scanner scanner) {
        this.application = application;
        System.out.println("Информация о коллекции:");
        System.out.println("Дата создания: " + application.getInitializationDate());
        System.out.println("Кол-во элементов: " + getCollection().size());
        if (!getCollection().isEmpty()) {
            System.out.println("Тип данных, хранимых в коллекции: " + getCollection().iterator().next().getClass());
            System.out.println("Первый добавленный в коллекцию элемент: " + getCollection().toArray()[0]);
        }
    }

    @Override
    public LinkedHashSet<StudyGroup> getCollection() {
        return application.getCollection();
    }

    @Override
    public HashSet<Long> getIdList() {
        return application.getIdList();
    }

    @Override
    String getCommandInfo() {
        return "info : выводит информацию о коллекции";
    }

    @Override
    public String toString() {
        return "info";
    }

    @Override
    public boolean withArgument() {
        return false;
    }
}
