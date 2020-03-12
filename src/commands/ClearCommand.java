package commands;

import app.Application;
import app.StudyGroup;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Scanner;

/**
 * команда очищает коллекцию
 */
public class ClearCommand extends Command {
    @Override
    public void execute(Application application, String argument, Scanner scanner) {
        application.setInitializationDate();
    }

    public HashSet<Long> getIdList() {
        return new HashSet<>();
    }

    public LinkedHashSet<StudyGroup> getCollection() {
        return new LinkedHashSet<>();
    }

    @Override
    String getCommandInfo() {
        return "clear : очистит коллекцию";
    }

    @Override
    public String toString() {
        return "clear";
    }

    @Override
    public boolean withArgument() {
        return false;
    }
}
