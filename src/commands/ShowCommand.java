package commands;

import app.Application;
import app.StudyGroup;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Scanner;

/**
 * команда выводит все элементы коллекции на консоль
 */
public class ShowCommand extends Command {
    private Application application;

    @Override
    public void execute(Application application, String argument, Scanner scanner) {
        this.application = application;
        if (!getCollection().isEmpty()) {
            for (StudyGroup studyGroup : getCollection()) System.out.println(studyGroup);
        } else System.out.println("Коллекция пуста!!!");
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
        return "show : выводит все элементы коллекции в строковом представлении";
    }

    @Override
    public String toString() {
        return "show";
    }

    @Override
    public boolean withArgument() {
        return false;
    }
}
