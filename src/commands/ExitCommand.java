package commands;

import app.Application;
import app.StudyGroup;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Scanner;

/**
 * команда закрытия приложения
 */
public class ExitCommand extends Command {
    private Application application;

    /**
     * установим флаг выхода, остальное условности
     */
    @Override
    public void execute(Application application, String argument, Scanner scanner) {
        application.getHandler().setExitCommand();
        this.application = application;
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
        return "exit : завершит программу (без сохранения в файл)";
    }

    @Override
    public String toString() {
        return "exit";
    }

    @Override
    public boolean withArgument() {
        return false;
    }
}
