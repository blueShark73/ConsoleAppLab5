package commands;

import app.Application;
import app.StudyGroup;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Scanner;

/**
 * выводит последние 9 команд
 */
public class HistoryCommand extends Command {
    private Application application;

    @Override
    public void execute(Application application, String argument, Scanner scanner) {
        this.application = application;
        application.getCommandHistory().printHistory();
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
        return "history : выведет последние 9 команд (без их аргументов)";
    }

    @Override
    public String toString() {
        return "history";
    }

    @Override
    public boolean withArgument() {
        return false;
    }
}
